import java.util.logging.Logger;
import java.io.*;
public class test_application  {
    
    public static void main(String args[])
    {
        System.out.println("This is a test application for testing the embedded application");
        System.out.println("Through the command line enter your choice and file name");
        System.out.println("Also after this you can write your results in a file and also delete a file");
        int ch = Integer.parseInt(args[0]);
        switch(ch)
        {   
            case 1:
                main_controller ob = new main_controller(false);
                ob.execute(args[1]);    
            case 2:
                Beta_update ob1 = new Beta_update();
                ob1.print_pattern();
            default:
                break;
        }
        ch = Integer.parseInt(args[2]);
        if(ch == 1)
        {
            ch = Integer.parseInt(args[3]);
            switch(ch)
            {
                case 1:
                    File f = new File(args[1]);
                    if(f.exists())
                    {
                        f.delete();
                    }
                    break;
                default:
                    String command = "rm ";
                    command = command+" "+args[1];
                    try
                    {
                        Runtime.getRuntime().exec(command);
                    }
                    catch(Exception e)
                    {
                        //LOGGER.log("context", e);
                    }
                    break;
            
            }   
        }
        else
        {
            return;
        }
    }
}
