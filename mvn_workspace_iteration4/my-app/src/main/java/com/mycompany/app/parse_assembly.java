package com.javatechie.StringPalindrome;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
public class parse_assembly {

    private static String filename;
    static String p[][];
    parse_assembly(String filename)
    {   
        this.filename = filename;
        this.p = null;
    }
    public static String getFileName()
    {
        return filename;
    }
    public static String[][] getProgram()
    {
        return p;
    }
    public static String[] tokenised_instruction(String str)
    {
        StringTokenizer st = new StringTokenizer(str," ");
        String []ins = new String[st.countTokens()];
        int i=0;
        while(st.hasMoreTokens())
        {
            String s = st.nextToken();
            ins[i++] = s;
        }
        return ins;
    }
    public static void parse()
    {   
        String file_name = getFileName();
        FileInputStream fin = null;
        try
        {
            fin = new FileInputStream(file_name);
        }
        catch(FileNotFoundException e)
        {
            //e.printStackTrace();
            //return;
        }
        finally
        {
            try
            {
                String str = "mov r0 #0;mov r2 #100;mov r3 #1;mov r4 #0;b :;str [r5#r4] r3;add r3 r3 #1;add r4 r4 #1;sub r2 r2 #1;cmp r2 #0;bne b;mov r2 #100;mov r3 #1;mov r4 #0;c :;ldr [r5#r4] r3;add r0 r0 r3;add r4 r4 #1;sub r2 r2 #1;cmp r2 #0;bne c;.AA r5 100";
                int num_lines=0;
                int i;
                // do
                // {
                //     i = fin.read();
                //     if(i != -1)
                //     {
                //         char ch = (char)i;
                //         str += ch;
                //     }
                // }while(i != -1);
                StringTokenizer st = new StringTokenizer(str,";");
                num_lines = st.countTokens();
                String p1[][] = new String[num_lines][]; // p is an acronym for the assembly program
                i = 0;
                str = "";
                while(st.hasMoreTokens())
                {   
                    str = st.nextToken();
                    p1[i] = tokenised_instruction(str);
                    i++;
                }
                p = p1;
            }
            catch(Exception e)
            {
                //e.printStackTrace();
            }
        }
        try {
            fin.close();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
