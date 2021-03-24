public class Beta_update {
    public static void print_val(String args[])
    {   
        if(args == null)
        {
            return;
        }
        int k = 1;
        int ch = Integer.parseInt(args[k++]);
        String resulting_poly = "";
        //String s1 = "1.3+1.0x^1+2.1x^2-2.25x^3";
        //String s2 = "2.7+3.4x-6.3x^2+6.75x^3+7.89x^5";
        switch(ch)
        {
            case 1:
                add_polynomials add = new add_polynomials();
                try
                {
                    resulting_poly = (add.ADD(add, args[k++], args[k++]));
                    //resulting_poly = (add.ADD(add, s1, s2));
                }
                catch(NullPointerException e)
                {
                    //e.printStackTrace();
                }
                break;
            case 2:
                subtract_polynomial sub = new subtract_polynomial();
                try
                {
                    resulting_poly = (sub.SUB(sub, args[k++], args[k++]));
                    //resulting_poly = (sub.SUB(sub, s1, s2));
                }
                catch(NullPointerException e)
                {
                    //e.printStackTrace();
                }
                break;
            case 3:
                multiply_polynomial mul = new multiply_polynomial();
                multiply_polynomial p = new multiply_polynomial();
                multiply_polynomial q = new multiply_polynomial();
                try
                {   
                    resulting_poly = mul.MULTUPLY(p, q, args[k++], args[k++]);
                    //resulting_poly = (mul.MULTUPLY(p, q, s1, s2));
                }
                catch(NullPointerException e)
                {
                    //e.printStackTrace();
                }
                break;
            case 4:
                DERIVATIVE der = new DERIVATIVE();
                try
                {
                    resulting_poly = (der.differentiate(der, args[k++]));
                }
                catch(NullPointerException e)
                {
                    //e.printStackTrace();
                }
                break;
            case 5:
                INTEGRATE INT = new INTEGRATE();
                try
                {   
                    String s1 = args[k++];
                    float constant = Float.parseFloat(args[k++]);
                    resulting_poly = (INT.Integrate(INT, s1, constant));
                }
                catch(Exception e)
                {
                    //e.printStackTrace();
                }
                break;
            default:
                System.out.println("Wrong choice");
                resulting_poly = "";
                break;
        }
        find_value f = new find_value();
        try
        {   
            float val = Float.parseFloat(args[k++]);
            System.out.println(f.FIND_VALUE(f, resulting_poly, val));
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }
    }
}
