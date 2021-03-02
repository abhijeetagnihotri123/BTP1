package com.javatechie.StringPalindrome;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    App app = new App();
    String str = "noon";
    boolean flag = app.check_palindrome(str);
    String str1 = "noon";
    boolean flag1 = app.check_palindrome(str);
    
    @Test
    public void shouldAnswerWithTrue()
    {   
        assertTrue(flag);
    }
    @Test
	public void isNotPlaindromeTest() {
		assertTrue(flag1);
	}
}
