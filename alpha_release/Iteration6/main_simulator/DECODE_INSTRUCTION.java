public class DECODE_INSTRUCTION extends EXECUTE_INSTRUCTION {
    public static int OPCODE(String str)
    {   
        int opcode;
        if(str.equals("mov"))
        {
            opcode = 1;
        }
        else if(str.equals("ldr"))
        {
            opcode = 2;
        }
        else if(str.equals("str"))
        {
            opcode = 3;
        }
        else if(str.equals("add"))
        {
            opcode = 4;
        }
        else if(str.equals("sub"))
        {
            opcode = 5;
        }
        else if(str.equals("cmp"))
        {
            opcode = 6;
        }
        else if(str.equals("bne"))
        {
            opcode = 7;
        }
        else if(str.equals("beq"))
        {
            opcode = 8;
        }
        else
        {
            opcode = 9;
        }
        return opcode;
    }
}
