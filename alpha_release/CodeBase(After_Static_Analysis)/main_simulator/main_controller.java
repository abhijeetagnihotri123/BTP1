import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class main_controller extends DECODE_INSTRUCTION
{
    static boolean will_branch;
    main_controller(boolean will_branch) // use this constructor when creating you GUI in the required servlet
    {
        this.will_branch = will_branch;
    }
    public static int max_register_number(String register)
    {
        String str = "";
        int n = register.length();
        int i=1;
        for(;i<n;i++)
        {
            str +=register.charAt(i);
        }
        n = Integer.parseInt(str);
        return n;
    }
    public static void execute(String filename)
    {   
        if(filename == null)
        {
            return;
        }
        parse_assembly ob = new parse_assembly(filename);
        ob.parse();
        String p[][] = ob.getProgram();
        int max = 0;
        int n,num_lines,num_actual_lines;
        num_lines = num_actual_lines = 0;
        Map<String,Integer>mp = new HashMap<String,Integer>();
        for(String x[] : p)
        {   
            num_lines++;
            for(String y : x)
            {
                if(y.charAt(0) == 'r')
                {   
                    n = max_register_number(y);
                    if(n > max)
                    {
                        max = n;
                    }
                }
            }
            if(!(x[0].equals(".AA")))
            {
                num_actual_lines++;
            }
            if(x[1].equals(":"))
            {
                mp.put(x[0], num_lines);
            }
        }
        int register [][] = new int[max+1][];
        int i = num_lines-1;
        for(;i>=0;i--)
        {
            int num_allocations;    
            if(p[i][0].equals(".AA"))
            {   
                n = max_register_number(p[i][1]);
                num_allocations = Integer.parseInt(p[i][2]);
                register[n] = new int[num_allocations];
            }
            else
            {
                for(String y : p[i])
                {
                    if(y.charAt(0) == 'r')
                    {
                        n = max_register_number(y);
                        if(register[n] == null)
                        {
                            register[n] = new int[1];
                        }
                    }
                }
            }
        }
        int IP = 0;
        int opcode,state;
        I_CACHE Iob = new I_CACHE(5);
        TWOBIT_BRANCH_PREDICTOR POB = new TWOBIT_BRANCH_PREDICTOR(0); // To deal with control hazard
        double CPI,I_hits,D_hits;
        CPI = 0;
        I_hits = D_hits = 0;
        while(IP<num_actual_lines)
        {   
            if(Iob.cache_hit(Iob.head,IP,p,num_actual_lines))
            {
                CPI++;
                I_hits++;
            }
            else
            {
                CPI = CPI+50;
            }
            opcode = OPCODE(Iob.head.ins[IP][0]);
            if(opcode == 6)
            {
                will_branch = CMP(Iob.head.ins[IP],register);
                opcode = OPCODE(Iob.head.ins[IP+1][0]);
                if(opcode == 7)
                {
                    if(!will_branch)
                    {   
                        state = POB.predict_state('T');
                        if(state == 0 || state == 1)
                        {
                            CPI++;
                        }
                        else
                        {   
                            CPI = CPI+3;
                            //some calculations to be done here
                        }
                        if(Iob.cache_hit(Iob.head,IP+1,p,num_actual_lines))
                        {
                            CPI++;
                            I_hits++;
                        }
                        else
                        {
                            CPI = CPI+50;
                        }
                        IP = mp.get(Iob.head.ins[IP+1][1]);
                    }
                    else
                    {   
                        state = POB.predict_state('N');
                        if(state == 2 || state == 3)
                        {
                            CPI++;
                            // some calculations to be done here
                        }
                        else
                        {   
                            CPI = CPI+3;
                            //some calculations to be done here
                        }
                        IP++;
                    }
                }
                else
                {
                    if(will_branch)
                    {   
                        state = POB.predict_state('T');
                        if(state == 0 || state == 1)
                        {
                            CPI++;// some calculations to be done here
                        }
                        else
                        {   
                            CPI = CPI+3;
                            //some calculations to be done here
                        }
                        if(Iob.cache_hit(Iob.head,IP+1,p,num_actual_lines))
                        {   
                            CPI++;
                            I_hits++;
                            // some calculations to be done here
                        }
                        else
                        {
                            CPI = CPI+50;
                        }
                        IP = mp.get(Iob.head.ins[IP+1][1]);
                    }
                    else
                    {       
                        state = POB.predict_state('N');
                        if(state == 2 || state == 3)
                        {   
                            CPI++;
                            // some calculations to be done here
                        }
                        else
                        {   
                            CPI = CPI+3;
                            //some calculations to be done here
                        }
                        IP++;
                    }
                }
            }
            else if(opcode == 2)
            {
                will_branch = LDR(Iob.head.ins[IP],register);
                String temp[] = Iob.head.ins[IP];
                if(Iob.cache_hit(Iob.head, IP+1, Iob.head.ins, num_actual_lines))
                {   
                    CPI++;
                    I_hits++;
                }
                else
                {   
                    CPI = CPI+50;
                    //some calculations
                }
                opcode = OPCODE(Iob.head.ins[IP+1][0]);
                if(opcode == 3)
                {
                    if(temp[1].equals(Iob.head.ins[IP+1][1]))
                    {   
                        CPI = CPI+3;
                        // stall
                    }
                    else
                    {   
                        CPI++;
                        //OK
                    }
                }
                else if(opcode == 4 || opcode == 5)
                {
                    if(temp[2].equals(Iob.head.ins[IP+1][1]) || temp[2].equals(Iob.head.ins[IP+1][2]))
                    {   
                        CPI = CPI+3;
                        // stall
                    }
                    else
                    {   
                        CPI++;
                        // OK
                    }
                }
                if(will_branch)
                {
                    CPI++;
                    D_hits++;
                    // some calculations
                }
                else
                {   
                    CPI = CPI+50;
                }
                IP++;
            }
            else if(opcode == 3)
            {
                will_branch = STR(Iob.head.ins[IP],register);
                String temp[] = Iob.head.ins[IP];
                if(Iob.cache_hit(Iob.head, IP+1, Iob.head.ins, num_actual_lines))
                {
                    CPI++;
                    I_hits++;
                }
                else
                {   
                    CPI = CPI+50;
                    //some calculations
                }
                opcode = OPCODE(Iob.head.ins[IP+1][0]);
                if(opcode == 2)
                {
                    if(temp[1].equals(Iob.head.ins[IP+1][1]))
                    {
                        CPI = CPI++;
                    }
                    else
                    {
                        CPI = CPI+50;
                    }
                }
                if(will_branch)
                {   
                    CPI++;
                    D_hits++;
                    // some calculations
                }
                else
                {
                    CPI = CPI+50;
                }
                IP++;
            }
            else
            {
                EXECUTE(Iob.head.ins[IP], register, opcode);
                IP++;
            }
            
        }
        System.out.println(register[0][0]+" "+D_hits+" "+I_hits+" "+CPI/num_actual_lines);
    }   
}
