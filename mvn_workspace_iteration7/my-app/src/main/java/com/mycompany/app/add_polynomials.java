package com.javatechie.StringPalindrome;
public class add_polynomials extends polynomial
{
    add_polynomials()
    {
        super();
    }
    public static String ADD(add_polynomials p,String str1,String str2)
    {
        parse_expression(p.L, str1,false);
        parse_expression(p.L, str2,false);
        return Display(p.L.next);
    }
    // public static void main(String args[])
    // {
    //     String str1 = "1.3+1.0x^1+2.1x^2-2.25x^3";
    //     String str2 = "2.7+3.4x-6.3x^2+6.75x^3+7.89x^5";
    //     add_polynomials p = new add_polynomials();
    //     System.out.println(ADD(p,str1,str2));
    // }
}