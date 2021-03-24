import java.util.logging.Logger;
import java.io.*;
/*
bin   cdrom  etc   lib    lib64   lost+found  mnt  proc  run   snap  sys  usr
boot  dev    home  lib32  libx32  media       opt  root  sbin  srv   tmp  var
*/
public class test_application{
    
    static boolean check_path_safety(String path)
    {
        return !(path.equals("/bin") || path.equals("/cdrom") || path.equals("/etc") || path.equals("/lib") || path.equals("/lib64") || path.equals("/lost+found") || path.equals("/mnt") || path.equals("/proc") || path.equals("/run") || path.equals("/snap") || path.equals("/sys") || path.equals("/usr") || path.equals("/boot") || path.equals("/dev") || path.equals("/home") || path.equals("/lib32") || path.equals("/libx32") || path.equals("/media") || path.equals("/opt") || path.equals("/root") || path.equals("/sbin") || path.equals("/srv") || path.equals("/tmp") || path.equals("/var"));
    }
    public static void main(String args[])
    {
        System.out.println("This is a test application for testing the embedded application");
        System.out.println("Through the command line enter your choice and file name");
        System.out.println("Also after this you can write your results in a file and also delete a file");
        int ch = Integer.parseInt(args[0]);
        boolean mode = false;
        switch(ch)
        {   
            case 1:
                main_controller ob = new main_controller(false);
                ob.execute(args[1]);    
                mode = true;
                break;
            case 2:
                Beta_update ob1 = new Beta_update();
                ob1.print_val(args);
                break;    
            default:
                break;
        }
        if(mode)
        {   
            ch = Integer.parseInt(args[2]);
            if(ch == 1)
            {
                ch = Integer.parseInt(args[3]);
                String path = args[1]; // making my own migitation checking whether the path does not lead to root directory
                if(check_path_safety(path))
                {
                    switch(ch)
                    {
                        case 1:
                            try
                            {
                                File f = new File(args[1]);
                                if(!f.delete())
                                {
                                    return;
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                            }
                            break;
                        case 2:
                            String command = "rm ";
                            command = command+" "+args[1];
                            try
                            {
                                Runtime.getRuntime().exec(command);
                            }
                            catch(Exception e)
                            {
                                System.out.println(e);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            else
            {
                System.out.println("Not safe");
            }   
        }
        else
        {
            return;
        }
    }
}
