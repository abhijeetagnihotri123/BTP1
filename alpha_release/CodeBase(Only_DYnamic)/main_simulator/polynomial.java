class Node
{
    float coeff;
    int power;
    Node next;
    Node(float coeff,int power)
    {
        this.coeff = coeff;
        this.power = power;
        this.next = null;
    }
}
public class polynomial 
{   
    Node L;
    polynomial()
    {
        L = new Node(-1,-1);
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
        Node ptr = new Node(coeff,power);
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
    public static void main(String args[])
    {
        int ch = 1;
        polynomial p = new polynomial();
        Scanner sc = new Scanner(System.in);
        float coeff;
        int power;
        String str = "1.3+1.0x^1+2.1x^2-2.25x^3";
        parse_expression(p.L, str);
    }
}
