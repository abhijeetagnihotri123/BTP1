class CNode
{
    int BA;
    int LA;
    CNode next;
    CNode prev;
    String[][]arr;
    CNode(int BA,int LA,String arr[][])
    {
        this.BA = BA;
        this.LA = LA;
        this.arr = arr;
        this.prev = null;
        this.next = null;
    }
}
