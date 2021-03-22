package com.javatechie.StringPalindrome;
public class TWOBIT_BRANCH_PREDICTOR {
    static int state = 0;
    TWOBIT_BRANCH_PREDICTOR(int state)
    {
        this.state = state;
    }
    public static int predict_state(char ch)
    {
        /* 
        0-> ST
        1->WT
        2->WNT
        3->SNT
        4->D_State
        */
            if(state == 0)
            {   
                if(ch == 'T')
                {
                }
                else if(ch == 'N')
                {
                    state = 1;
                }
                else
                {
                    state = 4;
                }
            }
            else if(state == 1)
            {
                if(ch == 'T')
                {
                    state = 0;
                }
                else if(ch == 'N')
                {
                    state = 2;
                }
                else
                {
                    state = 4;
                    
                }
            }
            else if(state == 2)
            {   
                if(ch == 'T')
                {
                    state = 1;
                }
                else if(ch == 'N')
                {
                    state = 3;
                }
                else
                {
                    state = 4;
                    
                }
            }
            else if(state == 3)
            {
                if(ch == 'T')
                {
                    state = 2;
                }
                else if(ch == 'N')
                {
                }
                else
                {
                    state = 4;
                }
            }
            return state;
    }
}
