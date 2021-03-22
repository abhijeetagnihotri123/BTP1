package com.javatechie.StringPalindrome;

import static org.junit.Assert.assertEquals;

import java.util.StringTokenizer;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    test_application app = new test_application();
    String args1[] = {"1","sample_program.txt","3"};
    String args2[] = {"2","4","2x","3"};
    String args3[] = {"2","1","2x","3x","3"};
    String args4[] = {"2","2","3x","2x","3"};
    String args5[] = {"2","3","2x","3x","3"};
    String args6[] = {"2","5","2x","3","3"};
    int k1 = app.test(args1);
    int k2 = app.test(args2);
    int k3 = app.test(args3);
    int k4 = app.test(args4);
    int k5 = app.test(args5);
    int k6 = app.test(args6);
    int answer1 = 5050;
    int answer2 = 2;
    int answer3 = 15;
    int answer4 = 3;
    int answer5 = 54;
    int answer6 = 12;
    @Test
    public void embedded_code()
    {   
        assertEquals(k1,answer1);
    }
    @Test
    public void polynomial1()
    {   
        assertEquals(k2,answer2);
    }
    @Test
    public void polynomial2()
    {   
        assertEquals(k3,answer3);
    }
    @Test
    public void polynomial3()
    {   
        assertEquals(k4,answer4);
    }
    @Test
    public void polynomial4()
    {   
        assertEquals(k5,answer5);
    }
    @Test
    public void polynomial5()
    {   
        assertEquals(k6,answer6);
    }
}
