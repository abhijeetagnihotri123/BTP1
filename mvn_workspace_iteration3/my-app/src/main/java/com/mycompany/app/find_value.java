package com.javatechie.StringPalindrome;
public class find_value 
{
    Node L;
    find_value()
    {   
        try
        {
            L = new Node(-1,-1);
        }
        catch(NullPointerException e)
        {
            //e.printStackTrace();
        }
    }
    static void Display(Node np)
    {   
        if(np.coeff < 0)
        {
            System.out.print(" - ");
        }
        while(np != null)
        {   
            float coeff = np.coeff>0?np.coeff:-np.coeff;
            System.out.print(coeff+"x^"+np.power);
            if(np.next != null)
            {   
                if(np.next.coeff > 0)
                {
                    System.out.print(" + ");
                }
                else
                {
                    System.out.print(" - ");
                }                
            }
            np = np.next;
        }
        System.out.println();
    }
    static void insert_term(Node L,int power,float coeff)
    {   
        Node ptr = null;
        try
        {
            ptr = new Node(coeff,power);
        }
        catch(NullPointerException e)
        {
            //e.printStackTrace();
        }
        if(L.next == null)
        {
            L.next = ptr;
        }
        else
        {
            Node start = L;
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
    static void parse_expression(Node L,String s)
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
    static float find_power(float a,int n)
    {   
        if(n == 0)
        {
            return 1;
        }
        if(a == 1.0 || n == 1)
        {
            return a;
        }
        int r = n>>1;
        r = r<<1;
        r = n-r;
        if(r == 0)
        {
            return find_power(a*a, n>>1);
        }
        else
        {
            return a*find_power(a*a, n>>1);
        }
    }
    static float VALUE(Node head,float x)
    {
        float term;
        float value = 0;
        while(head != null)
        {
            term = find_power(x,head.power);
            term = term * head.coeff;
            value += term;
            head = head.next;
        }
        return value;
    }
    public static float FIND_VALUE(find_value p,String str,float x)
    {
        parse_expression(p.L, str);
        return VALUE(p.L.next, x);
    }   
    // public static void main(String args[])
    // {
    //     find_value p = new find_value();
    //     String str = "1.3+1.0x^1+2.1x^2-2.25x^3";
    //     System.out.println(p.FIND_VALUE(p,str,3));
    // } 
}
