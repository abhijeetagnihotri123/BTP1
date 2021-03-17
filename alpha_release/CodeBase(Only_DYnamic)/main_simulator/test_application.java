import java.util.logging.Logger;
import java.io.*;
public class test_application  {
    
    public static void main(String args[])
    {
        int ch = Integer.parseInt(args[0]);
        boolean mode = false; // this is kept for this code base so as to deal with fall through without getting any exception
                              // will not be needing this in case of proper migitated code
        switch(ch)
        {   
            case 1:
                main_controller ob = new main_controller(false);
                ob.execute(args[1]);    
                mode = true;
            case 2:
                Beta_update ob1 = new Beta_update();
                ob1.print_val(args,mode);
            default:
                break;
        }
        if(mode)
        {
            ch = Integer.parseInt(args[2]);
            if(ch == 1 && mode)
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
                            e.printStackTrace();
                        }
                        break;
                }
            }   
        }
        else
        {   
            return;
        }
    }
}
