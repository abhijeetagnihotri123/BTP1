import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
class Node
{
    int BA;
    int LA;
    String [][] ins;
    Node next;
    Node prev;
    Node(int BA,int LA,String [][] ins)
    {
        this.BA = BA;
        this.LA = LA;
        this.ins = ins;
        this.next = this.prev = null;
    }
}
public class I_CACHE 
{    
    static int capacity;
    static int num_nodes;
    static Node head,rear,newptr;
    static String p[][];
    I_CACHE(int capacity)
    {
        this.capacity = capacity;
        this.num_nodes = 0;
        this.head = this.newptr =this.rear= null;
        this.p = null;
    }
    static int getCapacity()
    {
        return capacity;
    }
    public static void display(Node np)
    {   
        while(np != null)
        {
            System.out.print("("+np.BA+" "+np.LA+" "+num_nodes+")\n");
            int i = np.BA;
            int n = np.LA;
            while(i<n)
            {
                for(String y : np.ins[i])
                {
                    System.out.print(y+" ");
                }
                i++;
                System.out.println();
            }
            np = np.next;
        }
        System.out.println("==================");
    }
    public static Node find(Node np,int data,String arr[][])
    {
        while(np != null)
        {   
            if(np.BA <= data && np.LA > data)
            {
                return np;
            }
            np = np.next;
        }
        return null;
    }
    public static void insert(int data,String arr[][],int size)
    {   
        if(head == null)
        {   
            int LA;
            if(data >= size)
            {
                return;
            }
            else if((data + 5) < size)
            {
                LA = data+5;
            }
            else
            {
                LA = size;
            }
            head = rear = new Node(data,LA,arr);
            num_nodes = 1;
        }
        else if(num_nodes < capacity)
        {   
            Node np = find(head,data,arr);
            if(np == null)
            {   
                int LA;
                if(data >= size)
                {
                    return;
                }
                else if((data + 5) < size)
                {
                    LA = data+5;
                }
                else
                {
                    LA = size;
                }
                head.prev = new Node(data,LA,arr);
                head.prev.next = head;
                head = head.prev;
                num_nodes++;
            }
            else
            {
                if(np == head) // don't have to do anything
                {
                    return;
                }
                else if(np.next == null)
                {
                    rear = rear.prev;
                    rear.next = null;
                    np.prev = null;
                    np.next = head;
                    np.next.prev = np;
                    head = np;
                }
                else
                {
                    Node p,n;
                    p = np.prev;
                    n = np.next;
                    p.next = n;
                    n.prev = p;
                    np.next = head;
                    np.prev = null;
                    head.prev = np;
                    head = np;
                }
            }
        }
        else
        {
            Node np = find(head,data,arr);
            if(np == null)
            {   
                int LA;
                if(data >= size)
                {
                    return;
                }
                else if((data + 5) < size)
                {
                    LA = data+5;
                }
                else
                {
                    LA = size;
                }
                head.prev = new Node(data,LA,arr);
                head.prev.next = head;
                head = head.prev;
                rear = rear.prev;
                rear.next = null;
            }
            else
            {
                if(np == head) // don't have to do anything
                {
                    return;
                }
                else if(np.next == null)
                {
                    rear = rear.prev;
                    rear.next = null;
                    np.prev = null;
                    np.next = head;
                    np.next.prev = np;
                    head = np;
                }
                else
                {
                    Node p,n;
                    p = np.prev;
                    n = np.next;
                    p.next = n;
                    n.prev = p;
                    np.next = head;
                    np.prev = null;
                    head.prev = np;
                    head = np;
                }
            }
        }
    }
    public static boolean cache_hit(Node head,int data,String [][]arr,int size)
    {   
        Node np;
        np = find(head,data,arr);
        insert(data,arr,size);
        return np != null;
    }
}
