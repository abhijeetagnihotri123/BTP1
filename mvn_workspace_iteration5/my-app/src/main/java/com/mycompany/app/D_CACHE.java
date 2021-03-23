package com.javatechie.StringPalindrome;
public class D_CACHE 
{    
    static int capacity;
    static int num_nodes;
    static CNode head,rear,newptr;
    D_CACHE(int capacity)
    {
        this.capacity = capacity;
        this.num_nodes = 0;
        this.head = this.newptr =this.rear= null;
    }
    int getCapacity()
    {
        return capacity;
    }
    public static CNode find(CNode np,int data,String arr[][])
    {
        while(np != null)
        {   
            if((np.BA <= data && np.LA > data) && np.arr.equals(arr))
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
            if((data + 10) < size)
            {
                LA = data+10;
            }
            else
            {
                LA = size;
            }
            head = rear = new CNode(data,LA,arr);
            num_nodes = 1;
        }
        else if(num_nodes < capacity)
        {   
            CNode np = find(head,data,arr);
            if(np == null)
            {   
                int LA;
                if((data + 10) < size)
                {
                    LA = data+10;
                }
                else
                {
                    LA = size;
                }
                head.prev = new CNode(data,LA,arr);
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
                    CNode p,n;
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
            CNode np = find(head,data,arr);
            if(np == null)
            {   
                int LA;
                if((data + 10) < size)
                {
                    LA = data+10;
                }
                else
                {
                    LA = size;
                }
                head.prev = new CNode(data,LA,arr);
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
                    CNode p,n;
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
    public static boolean cache_hit(CNode head,int data,String [][]arr,int size)
    {   
        CNode np;
        np = find(head,data,arr);
        insert(data,arr,size);
        return np != null;
    }
}
