public class I_CACHE 
{    
    static int capacity;
    static int num_nodes;
    static CNode head,rear,newptr;
    static String p[][];
    I_CACHE(int capacity)
    {
        this.capacity = capacity;
        this.num_nodes = 0;
        this.head = this.newptr =this.rear= null;
        this.p = null;
    }
    public static CNode find(CNode np,int data,String arr[][])
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
    public static void REPLACE_LRU(CNode np)
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
    public static int PAD_LIMITS(int data,int offset,int size)
    {
        int LA;
        if(data >= size)
        {
            LA = -1;
        }
        else if((data + 5) < size)
        {
            LA = data+5;
        }
        else
        {
            LA = size;
        }
        return LA;
    }
    public static void insert(int data,String arr[][],int size)
    {       
        CNode np = find(head,data,arr);
        if(np != null)
        {
            REPLACE_LRU(np);
        }
        else
        {
            int LA = PAD_LIMITS(data, 5, size);
            if(LA == -1)
            {
                return;
            }
            else
            {   
                newptr = new CNode(data,LA,arr);
                if(head == null)
                {
                    head = rear = newptr;
                    num_nodes = 1;
                }
                else
                {
                    head.prev = newptr;
                    head.prev.next = head;
                    head = head.prev;
                    if(num_nodes < capacity)
                    {
                        num_nodes++;
                    }
                    else
                    {
                        rear = rear.prev;
                        rear.next.prev = null;
                        rear.next = null;
                    }
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
