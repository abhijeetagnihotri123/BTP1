public class DERIVATIVE extends polynomial{
    DERIVATIVE()
    {   
        super();
    }
    static void Differentialte(Node L)
    {   
        Node head = L.next;
        if(head == null)
        {
            System.out.println("There is nothing left to differentiate");
            return;
        }
        if(head.power == 0)
        {   
            Node tmp = head;
            head = head.next;
            L.next = head;
            tmp.next = null;
        }
        while(head != null)
        {
            head.coeff = head.coeff * head.power;
            head.power -= 1;
            head = head.next;
        }
    }
    public static String differentiate(DERIVATIVE p,String str)
    {
        parse_expression(p.L, str,false);
        Differentialte(p.L);
        return Display(p.L.next);
    }
    // public static void main(String args[])
    // {
    //     DERIVATIVE p = new DERIVATIVE();
    //     String str = "1.3+1.0x^1+2.1x^2-2.25x^3";
    //     System.out.println(differentiate(p, str));
    // }   
}
