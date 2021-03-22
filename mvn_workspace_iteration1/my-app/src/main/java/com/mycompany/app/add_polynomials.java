package com.javatechie.StringPalindrome;
class AddNode
{
    float coeff;
    int power;
    AddNode next;
    AddNode(float coeff,int power)
    {
        this.coeff = coeff;
        this.power = power;
        this.next = null;
    }
}
public class add_polynomials {
    AddNode L;
    add_polynomials()
    {   
        try
        {
            L = new AddNode(-1,-1);
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
    }
    static String Display(AddNode np)
    {   
        String s = "";
        if(np.coeff < 0)
        {
            s = s+"-";
        }
        while(np != null)
        {   
            float coeff = np.coeff>0?np.coeff:-np.coeff;
            s = s+coeff+"x^"+np.power;
            if(np.next != null)
            {   
                if(np.next.coeff > 0)
                {
                    s = s + "+";
                }
                else
                {
                    s = s + "-";
                }                
            }
            np = np.next;
        }
        return s;
    }
    static void insert_term(AddNode L,int power,float coeff)
    {   
        AddNode ptr = null;
        try
        {
            ptr = new AddNode(coeff,power);
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        if(L.next == null)
        {
            L.next = ptr;
        }
        else
        {
            AddNode start = L;
            while(start.next != null && start.next.power < power)
            {
                start = start.next;
            }
            if(start.next != null && start.next.power == power)
            {
                start.next.coeff += coeff;
                if(start.next.coeff == 0)
                {
                    start.next = start.next.next;
                }
            }
            else
            {
                ptr.next = start.next;
                start.next = ptr;
            }
        }
    } 
    static void parse_expression(AddNode L,String s)
    {
        int i = 0;
        int n;
        int j;
        s = s+'+';
        String s1,s2;
        String str = "";
        float coeff;
        int power;
        char sign,ch;
        ch = s.charAt(0);
        sign = '+';
        if(ch == '+' || ch == '-')
        {
            sign = ch;
            i++;
        }
        for(;i<s.length();i++)
        {
            if(s.charAt(i) == '+' || s.charAt(i) == '-')
            {
                s1 = s2 = "";
                n = str.length();
                if(str.charAt(n-1) == 'x' || (str.charAt(n-1) == '1' && str.charAt(n-2) == '^'))
                {
                    s2 = "1";
                }
                else if(str.charAt(n-2) != '^' || (str.charAt(n-1) == '0' && str.charAt(n-2) == '^'))
                {
                    s2 = "0";
                }
                j = 0;
                while(j<n)
                {
                    if(str.charAt(j) == 'x')
                    {
                        break;
                    }
                    s1 = s1 + str.charAt(j);
                    j++;
                }
                j = j+2;
                if(s2.isEmpty())
                {
                    while(j<n)
                    {
                        s2 = s2+str.charAt(j);
                        j++;
                    }
                }
                coeff = Float.parseFloat(s1);
                coeff = sign=='+'?coeff:-coeff;
                sign = s.charAt(i);
                power = Integer.parseInt(s2);
                insert_term(L, power, coeff);
                str = "";
            }
            else
            {
                str = str+s.charAt(i);   
            }
        }
    }
    public static String ADD(add_polynomials p,String str1,String str2)
    {
        parse_expression(p.L, str1);
        parse_expression(p.L, str2);
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
