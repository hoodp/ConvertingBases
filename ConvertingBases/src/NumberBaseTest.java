import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

/**********************************************************************
 * This JUnit tests tests the convert method in the NumberBase class
 * @author Paul Hood
 * @version 9/9/2013
 *
 **********************************************************************/
public class NumberBaseTest {
	
	/** random decimal **/
	Random random = new Random();
	
	/******************************************************************
	 * This method randomly gets a decimal from the random number
	 * generator. It then converts it to a binary number and
	 * checks that answer against the NumberBase.convert 
	 * method. It converts and checks from base 2 through base 32.
	 * 
	 * @param decimal random number between 2^31 -1 
	 * @param answer string of converted decimal to binary
	 ******************************************************************/
	@Test
	public void decimal_to_bases() throws Throwable {
		for(int base = 2; base <= 36; base++) {
			for(int i = 0; i < 10; i++) {
				int decimal = random.nextInt((int) (Math.pow(2,31) - 1));
				String answer = Integer.toString(decimal, base);
				assertEquals(answer, NumberBase.convert("" + decimal, 10, base));
			}
		}
	}
	
	/******************************************************************
	 * This method randomly gets a decimal number and converts it to a 
	 * binary number. It then calculates that number in another base
	 * and checks that answer against the NumberBase.convert method.
	 * It checks binary numbers from base two through thirty two.
	 * 
	 * @param number random decimal 
	 * @param binary String conversion of number
	 * @param convertedNumber Converted binary string to integer
	 * @param answer answer of converted number to base
	 ******************************************************************/
	@Test 
	public void binary_to_bases() throws Throwable {
		for(int base = 2; base <= 36; base++) {
			for(int i = 0; i < 10; i++) {
				int number = random.nextInt((int) (Math.pow(2,31) - 1));
				String binary = Integer.toString(number, 2);
				int convertedNumber = Integer.parseInt(binary, 2);
				String answer = Integer.toString(convertedNumber, base);
				assertEquals(answer, NumberBase.convert("" + binary, 2, base));
			}
		}
	}
	
	/******************************************************************
	 * Tests decimal to binary conversion
	 ******************************************************************/
	@Test 
	public void decimal_to_binary() throws Throwable {
		assertEquals("101101", NumberBase.convert("45", 10, 2));
		assertEquals("10111", NumberBase.convert("23", 10, 2));
		assertEquals("1100011", NumberBase.convert("99", 10, 2));
		assertEquals("10011010000", NumberBase.convert("1232", 10, 2));
		assertEquals("1111101000", NumberBase.convert("1000", 10, 2));
		assertEquals("0", NumberBase.convert("0", 10, 2));
	}
	
	/******************************************************************
	 * Tests binary to decimal conversion
	 ******************************************************************/
	@Test
	public void binary_to_decimal() throws Throwable {
	    assertEquals("0", NumberBase.convert("0", 2, 10));
	    assertEquals("1", NumberBase.convert("1", 2, 10));
	    assertEquals("2", NumberBase.convert("10", 2, 10));
	    assertEquals("3", NumberBase.convert("11", 2, 10));
	    assertEquals("10", NumberBase.convert("1010", 2, 10));
	    assertEquals("4337", NumberBase.convert("1000011110001", 2, 10));
	}
	
	/******************************************************************
	 * Tests decimal to hex conversion
	 ******************************************************************/
	@Test
	public void decimal_to_hex() throws Throwable {
	    assertEquals("0", NumberBase.convert("0", 10, 16));
	    assertEquals("1", NumberBase.convert("1", 10, 16));
	    assertEquals("a", NumberBase.convert("10", 10, 16));
	    assertEquals("64", NumberBase.convert("100", 10, 16));
	    assertEquals("dead", NumberBase.convert("57005", 10, 16));
	    assertEquals("199b", NumberBase.convert("6555", 10, 16));
	   }
	
	/******************************************************************
	 * Tests base three to thirty two conversion
	 ******************************************************************/
	@Test
	public void baseThree_to_thirtyTwo() {
		assertEquals("h", NumberBase.convert("122", 3, 32));
		assertEquals("1ou", NumberBase.convert("002111111", 3, 32));
		assertEquals("mo", NumberBase.convert("222222", 3, 32));
		assertEquals("10", NumberBase.convert("01012", 3, 32));
		assertEquals("0", NumberBase.convert("000000", 3, 32));
		assertEquals("1", NumberBase.convert("000001", 3, 32));
	}
	
	/******************************************************************
	 * Tests base six to base thirty six conversion
	 ******************************************************************/
	@Test
	public void baseSix_to_thirtySix() {
		assertEquals("5v", NumberBase.convert("551", 6, 36));
		assertEquals("5zzzz", NumberBase.convert("555555555", 6, 36));
		assertEquals("543210", NumberBase.convert("050403020100", 6, 36));
		assertEquals("y", NumberBase.convert("54", 6, 36));
		assertEquals("3", NumberBase.convert("3", 6, 36));
		assertEquals("3ef", NumberBase.convert("32223", 6, 36));
	}
	
	/******************************************************************
	 * Tests base 17 to base 16 conversion
	 ******************************************************************/
	@Test
	public void baseSeventeen_to_sixteen() throws Throwable {
		assertEquals("c11", NumberBase.convert("abc", 17, 16));
		assertEquals("cd2e", NumberBase.convert("abcd", 17, 16));
		assertEquals("da01c", NumberBase.convert("abcde", 17, 16));
		assertEquals("e7a1eb", NumberBase.convert("abcdef", 17, 16));
		assertEquals("f60505c", NumberBase.convert("abbbbbb", 17, 16));
		assertEquals("ff", NumberBase.convert("f0", 17, 16));
	}
	
	/******************************************************************
	 * Tests hex to binary conversion
	 ******************************************************************/
	@Test
	public void hex_to_binary() throws Throwable {
		assertEquals("1011110011000110", NumberBase.convert("bcc6", 16, 2));
		assertEquals("10101011", NumberBase.convert("ab", 16, 2));
		assertEquals("1001000110011", NumberBase.convert("1233", 16, 2));
		assertEquals("101000010100", NumberBase.convert("a14", 16, 2));
		assertEquals("101000011111", NumberBase.convert("a1f", 16, 2));
		assertEquals("11", NumberBase.convert("3", 16, 2));
	}
	
	/******************************************************************
	 * Tests base 13 to base 27 conversion
	 ******************************************************************/
	@Test
	public void baseThirteen_to_twentySeven() throws Throwable {
		assertEquals("2e9", NumberBase.convert("abc", 13, 27));
		assertEquals("1g", NumberBase.convert("34", 13, 27));
		assertEquals("294", NumberBase.convert("a12", 13, 27));
		assertEquals("1c0m", NumberBase.convert("cc49", 13, 27));
		assertEquals("70nl3", NumberBase.convert("a0b1c2", 13, 27));
		assertEquals("53fb8j", NumberBase.convert("012345679", 13, 27));
	}
	
	/******************************************************************
	 * Test for input errors
	 *****************************************************************/
    @Test (expected = IllegalArgumentException.class) 
    public void testInputError1() {
    	NumberBase.convert("5+2", 10, 2);
    }
    
    @Test (expected = IllegalArgumentException.class) 
    public void testInputError2() {
    	NumberBase.convert("120*120", 10, 32);
    }
    
    @Test (expected = IllegalArgumentException.class) 
    public void testInputError3() {
    	NumberBase.convert("7^2", 2, 3);
    }
    
    /******************************************************************
	 * Test for incorrect base errors
	 ******************************************************************/
    @Test (expected = IllegalArgumentException.class) 
    public void testBaseError1() {
    	NumberBase.convert("011011000002", 2, 13);
    }
    
    @Test (expected = IllegalArgumentException.class) 
    public void testBaseError2() {
    	NumberBase.convert("6af55", 15, 36);
    }
    
    @Test (expected = IllegalArgumentException.class) 
    public void testBaseError3() {
    	NumberBase.convert("g", 16, 21);
    }
}
