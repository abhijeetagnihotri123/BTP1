public class polynomial {
    Node L;
    polynomial()
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
    static String Display(Node np)
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
    static void parse_expression(Node L,String s,boolean mode)
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
                coeff = mode?-coeff:coeff;
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
}
