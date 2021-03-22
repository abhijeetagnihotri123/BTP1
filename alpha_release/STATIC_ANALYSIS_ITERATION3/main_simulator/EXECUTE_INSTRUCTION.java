import java.util.HashMap;
import java.util.Map;
public class EXECUTE_INSTRUCTION {
    static D_CACHE Dob = null;
    public static int extract_number(String register)
    {
        String str = "";
        int n = register.length();
        int i = 1;
        for(;i<n;i++)
        {
            str += register.charAt(i);
        }
        n = Integer.parseInt(str);
        return n;
    }
    public static void MOV(String []ins,int register[][])
    {
        int n = extract_number(ins[1]);
        int constant = extract_number(ins[2]);
        register[n][0] = constant;
    }
    public static void ADD(String []ins,int register[][])
    {
        int store_register = extract_number(ins[1]);
        int a,b;
        if(ins[2].charAt(0) == 'r')
        {
            int n = extract_number(ins[2]);
            a = register[n][0];
        }
        else
        {
            a = extract_number(ins[2]);
        }
        if(ins[3].charAt(0) == 'r')
        {
            int n = extract_number(ins[3]);
           b = register[n][0];
        }
        else
        {
            b = extract_number(ins[3]);
        }
        register[store_register][0] = a+b;
    }
    public static void SUB(String []ins,int register[][])
    {
        int store_register = extract_number(ins[1]);
        int a,b;
        if(ins[2].charAt(0) == 'r')
        {
            int n = extract_number(ins[2]);
            a = register[n][0];
        }
        else
        {
            a = extract_number(ins[2]);
        }
        if(ins[3].charAt(0) == 'r')
        {
            int n = extract_number(ins[3]);
            b = register[n][0];
        }
        else
        {
            b = extract_number(ins[3]);
        }
        register[store_register][0] = a-b;
    }
    public static boolean CMP(String []ins,int register[][])
    {
        int store_register = extract_number(ins[1]);
        int a,b;
        if(ins[2].charAt(0) == 'r')
        {
            int n = extract_number(ins[2]);
            b = register[n][0];
        }
        else
        {
            b = extract_number(ins[2]);
        }
        a = register[store_register][0];
        return (a == b);
    }
    public static boolean LDR(String []ins,int register[][])
    {   
        if(Dob == null)
        {
            Dob = new D_CACHE(5);
        }
        int c,n1,n2;
        c = extract_number(ins[2]); // this will be register number
        String r1 = "";
        String r2 = "";
        int i=1;
        for(;i<ins[1].length();i++)
        {
            if(ins[1].charAt(i) == '#')
            {
                break;
            }
            r1 += ins[1].charAt(i);
        }
        i++;
        for(;i<ins[1].length()-1;i++)
        {
            r2 += ins[1].charAt(i);
        }
        n1 = extract_number(r1);
        n2 = extract_number(r2);
        boolean flag = Dob.cache_hit(Dob.head, register[n2][0], register[n1], 100);
        register[c][0] = Dob.head.arr[register[n2][0]];
        return flag;
    }
    public static boolean STR(String []ins,int register[][])
    {       
        if(Dob == null)
        {
            Dob = new D_CACHE(5);
        }
        int c,a,n;
        if(ins[2].charAt(0) == 'r')
        {
            n = extract_number(ins[2]);
            c = register[n][0];
        }
        else
        {
            c = extract_number(ins[2]);
        }
        String r1 = "";
        String r2 = "";
        int i=1;
        for(;i<ins[1].length();i++)
        {
            if(ins[1].charAt(i) == '#')
            {
                break;
            }
            r1 += ins[1].charAt(i);
        }
        i++;
        for(;i<ins[1].length()-1;i++)
        {
            r2 += ins[1].charAt(i);
        }
        n = extract_number(r2);
        a = register[n][0];
        n = extract_number(r1);
        boolean flag = Dob.cache_hit(Dob.head, a, register[n], 100);
        Dob.head.arr[a] = c;
        return flag;
    }
    public static void EXECUTE(String []ins,int register[][],int opcode)
    {
        if(opcode == 1)
        {   
            MOV(ins,register);
        }
        else if(opcode == 2)
        {
            LDR(ins,register);
        }
        else if(opcode == 3)
        {
            STR(ins,register);
        }
        else if(opcode == 4)
        {
            ADD(ins,register);
        }
        else if(opcode == 5)
        {
            SUB(ins,register);
        }
        else
        {
            return;
        }
    }
}
