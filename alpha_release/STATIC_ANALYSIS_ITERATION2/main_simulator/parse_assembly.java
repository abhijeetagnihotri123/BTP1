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
        FileInputStream fin;
        try
        {
            fin = new FileInputStream(file_name);
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
            return;
        }
        try
        {
            String str = "";
            int num_lines=0;
            int i;
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
            System.out.println(e);
        }
        try {
            fin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
