package com.javatechie.StringPalindrome;
public class Beta_update {
    public static int print_val(String args[],boolean mode,int r)
    {   
        int result = r;
        if(mode)
        {
            return result;
        }
        int k = 1;
        int ch = Integer.parseInt(args[k++]);
        String resulting_poly = "";
        switch(ch)
        {
            case 1:
                add_polynomials add = new add_polynomials();
                try
                {
                    resulting_poly = (add.ADD(add, args[k++], args[k++]));
                }
                catch(NullPointerException e)
                {
                    e.printStackTrace();
                }
                break;
            case 2:
                subtract_polynomial sub = new subtract_polynomial();
                try
                {
                    resulting_poly = (sub.SUB(sub, args[k++], args[k++]));
                }
                catch(NullPointerException e)
                {
                    e.printStackTrace();
                }
                break;
            case 3:
                multiply_polynomial mul = new multiply_polynomial();
                multiply_polynomial p = new multiply_polynomial();
                multiply_polynomial q = new multiply_polynomial();
                try
                {
                    resulting_poly = (mul.MULTUPLY(p, q, args[k++], args[k++]));
                }
                catch(NullPointerException e)
                {
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
                break;
            case 5:
                INTEGRATE INT = new INTEGRATE();
                try
                {
                    resulting_poly = (INT.Integrate(INT, args[k++], 3));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
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
            result = (int)f.FIND_VALUE(f, resulting_poly, val);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
