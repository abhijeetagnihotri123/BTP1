package com.javatechie.StringPalindrome;

public class App {
    public boolean check_palindrome(String s)
    {
        if(s == null)
        {
            return false;//throw IllegalArgumentException;
        }
        int n = s.length();
        int i=0;
        int m = n>>1;
        boolean flag = true;
        while(i<m && s.charAt(i) == s.charAt(n-1-i))
        {
            flag = flag && (s.charAt(i) == s.charAt(n-1-i));
            i++;
        }
        return flag && (s.charAt(i) == s.charAt(n-1-i));
    }    
}
