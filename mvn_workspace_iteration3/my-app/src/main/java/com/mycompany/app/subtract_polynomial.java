package com.javatechie.StringPalindrome;
public class subtract_polynomial extends polynomial
{
    subtract_polynomial()
    {   
        super();
    }
    public static String SUB(subtract_polynomial p,String str1,String str2)
    {
        parse_expression(p.L, str1, false);
        parse_expression(p.L, str2, true);
        return Display(p.L.next);
    }
    // public static void main(String args[])
    // {
    //     subtract_polynomial p = new subtract_polynomial(); // p is an acronym for polynomial p(x)
    //     String str1 = "1.3+1.0x^1+2.1x^2-2.25x^3";
    //     String str2 = "2.7+3.4x-6.3x^2+6.75x^3+7.89x^5";
    //     System.out.println(p.SUB(p, str1, str2));
    // }
}
