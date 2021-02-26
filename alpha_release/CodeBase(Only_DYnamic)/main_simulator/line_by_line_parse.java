import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

// In this file I'll be writing a parser for my pipeline simulator which will parse the ARM assembly program and will store it in an array of Strings
class line_by_line_parsing{
    public static void main(String args[])
    {       
        int i=3;
        FileInputStream fin;
        try
        {
            fin = new FileInputStream(args[0]);
        }
        catch(Exception e)
        {
            System.out.println(e);
            return;
        }
        try
        {   
            String str = "";
            do
            {
                i = fin.read();
                if(i != -1)
                {
                    char ch = (char)i;
                    str += ch;
                }
            }while(i != -1);    
            StringTokenizer st = new StringTokenizer(str,"\n");
            System.out.println("Using String Tokenizer\n");
            i = 0;
            System.out.println(st.countTokens());
            while(st.hasMoreTokens())
            {
                System.out.println(st.nextToken()+" "+i);
                i+=1;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        try
        {
            fin.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}