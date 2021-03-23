package com.javatechie.StringPalindrome;
public class INTEGRATE extends polynomial {
    INTEGRATE()
    {   
        super();
    }
    static void PRIMITIVE(Node L,float constant)
    {
        Node head = L.next;
        Node tmp = new Node(constant,0);
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
        parse_expression(p.L, args,false);
        PRIMITIVE(p.L,constant);
        return Display(p.L.next);
    }
    // public static void main(String args[])
    // {
    //     INTEGRATE p = new INTEGRATE();
    //     find_value op = new find_value();
    //     String str = "2x";//"1.3+1.0x^1+2.1x^2-2.25x^3";
    //     String s = Integrate(p, str,3);
    //     System.out.println(s);
    //     System.out.println(op.FIND_VALUE(op,s,3));
    // } 
}
