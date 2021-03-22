public class multiply_polynomial extends polynomial{
    multiply_polynomial()
    {
        super();
    }
    static multiply_polynomial multiply(multiply_polynomial p,multiply_polynomial q)
    {
        multiply_polynomial r = new multiply_polynomial();
        Node h1 = p.L.next;
        int c = 0;
        while(h1 != null)
        {   
            Node h2 = q.L.next;
            while(h2 != null)
            {   
                insert_term(r.L, h1.power+h2.power, h1.coeff*h2.coeff);
                h2 = h2.next;
            }
            h1 = h1.next;
        }
        return r;
    }
    public static String MULTUPLY(multiply_polynomial p,multiply_polynomial q,String str1,String str2)
    {
        parse_expression(p.L, str1,false);
        parse_expression(q.L, str2,false);
        multiply_polynomial r = multiply(p, q);
        return Display(r.L.next);
    }
    // public static void main(String args[])
    // {
    //     String str1 = "1.3+1.0x^1+2.1x^2-2.25x^3";
    //     String str2 = "2.7+3.4x-6.3x^2+6.75x^3+7.89x^5";
    //     multiply_polynomial p = new multiply_polynomial(); // p is an acronym for polynomial p(x)
    //     multiply_polynomial q = new multiply_polynomial();
    //     System.out.println(MULTUPLY(p, q, str1, str2));
    // }
}
