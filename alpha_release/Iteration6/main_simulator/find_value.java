public class find_value extends polynomial{
    find_value()
    {   
        super();
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
        parse_expression(p.L, str,false);
        return VALUE(p.L.next, x);
    }   
    // public static void main(String args[])
    // {
    //     find_value p = new find_value();
    //     String str = "1.3+1.0x^1+2.1x^2-2.25x^3";
    //     System.out.println(p.FIND_VALUE(p,str,3));
    // } 
}
