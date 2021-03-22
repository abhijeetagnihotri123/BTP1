class PNODE
{
    float coeff;
    int power;
    PNODE next;
    PNODE(float coeff,int power)
    {
        this.coeff = coeff;
        this.power = power;
        this.next = null;
    }
}
public class INTEGRATE {
    PNODE L;
    INTEGRATE()
    {   
        try
        {
            L = new PNODE(-1,-1);
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
    }
    static String Display(PNODE np)
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
    static void insert_term(PNODE L,int power,float coeff)
    {   
        PNODE ptr = null;// = new PNODE(coeff,power);
        try
        {
            ptr = new PNODE(coeff,power);
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
            PNODE start = L;
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
    static void parse_expression(PNODE L,String s)
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
    static void PRIMITIVE(PNODE L,float constant)
    {
        PNODE head = L.next;
        PNODE tmp = new PNODE(constant,0);
        L.next = tmp;
        tmp.next = head;
        while(head != null)
        {
            head.power = head.power+1;
            head.coeff /= head.power;
            head = head.next;
        }
    }
    public static String Integrate(INTEGRATE p,String args,float constant)
    {
        parse_expression(p.L, args);
        PRIMITIVE(p.L,constant);
        return Display(p.L.next);
    }
    // public static void main(String args[])
    // {
    //     INTEGRATE p = new INTEGRATE();
    //     String str = "1.3+1.0x^1+2.1x^2-2.25x^3";
    //     System.out.println(Integrate(p, str,3));
    // } 
}
