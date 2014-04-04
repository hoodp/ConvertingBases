import java.util.Arrays;

/**********************************************************************
 * The following class converts a number from any base to another base.
 * 
 * @author Paul Hood
 * @version 9/9/2013
 *
 *********************************************************************/
public class NumberBase {
	
	/** Static string stores string of accepted letters and numbers **/
	private static String[] valueArray = { "0", "1", "2", "3", "4",
		"5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g",
		"h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", 
		"t", "u", "v", "w", "x", "y", "z" };
	
	/******************************************************************
	 * Method that converts String from any base to another base. The 
	 * method first checks for errors before returning the answer.
	 * 
	 * @param input String input to be converted
	 * @param base_in The current base of the Number
	 * @param base_out Desired base of the input
	 * @return String of converted input to desired base
	 *****************************************************************/
	public static String convert(String input, int base_in, int base_out) {
		input = input.trim().toLowerCase();
		checkInput(input, base_in);
		return toBase(baseTen(input, base_in), base_out);
	}
	
	/******************************************************************
	 * Method that throws an error if a character is not valid, also 
	 * throws an error if the number is not a correct value for the 
	 * base.
	 * 
	 * @param input String from convert method
	 * @param base_in Base from convert method
	 ******************************************************************/
	private static void checkInput(String input, int base_in) {	
		
		for(int i = 0; i < input.length(); i++) {
			String character = "" + input.charAt(i);
			
			// checks for invalid character
			if(!(Arrays.asList(valueArray).contains(character))) {
				throw new IllegalArgumentException(character + 
						" is not a valid entry.");
			}
			
			// checks for incorrect base
			else if (Arrays.asList(valueArray).indexOf(character) >
				base_in - 1) {
				throw new IllegalArgumentException(character + 
						" is not a valid entry for base " + base_in);
			}
		}
	}
	
	/******************************************************************
	 * Converts every string to base 10 number regardless of base_out.
	 * Converting it to base 10 made it easier to convert to any base.
	 * If charVal * Math.pow(base_in, exponent) is greater than
	 * 2^31 - 1 an incorrect answer will be shown.
	 * 
	 * @param input Validated input from convert method
	 * @param base_in Base in value from convert method
	 * @param exponent value of exponent to convert to base 10
	 * @param character string representation of current character
	 * @param value base 10 number that is returned
	 * @return base 10 number of input
	 ******************************************************************/
	private static int baseTen(String input, int base_in) {
		int exponent = input.length() - 1;
		int value = 0;
		
		// loops assigns value to position in array
		for(int i = 0; i < input.length(); i++) {
			String character = "" + input.charAt(i);
			int charVal = Arrays.asList(valueArray).indexOf(character);
			value += (int) (charVal * Math.pow(base_in, exponent));
			exponent--;
		}
		return value;
	}
	
	/******************************************************************
	 * Returns String of number at desired base
	 * 
	 * @param decimal Converted base 10 number
	 * @param base_out Desired base conversion
	 * @param output String to be returned
	 * @param exponent Variable that stores desired powers in order
	 * 		  to convert number properly
	 * @param location value of the base to the power of its location
	 * 		  in the string.
	 * @param value the base 10 number divided by location value
     * @return Returns converted number in String format
	 ******************************************************************/
	private static String toBase(int decimal, int base_out) {
		String output = "";
		int exponent = 0;
		
		// finds the value of the highest base_out power
		while(Math.pow(base_out, exponent) <= decimal) {
			exponent++;
		}
				
		for(int i = exponent - 1; i >= 0; i--) {
			int location = (int) Math.pow(base_out, i);
		    int value = decimal / location;
		    if(value >= 1) {
		    	decimal -= value * location;
		    }
		    output += valueArray[value];
		}
		if (output.equals("")) {
			return "0";
		}
		
		// removes leading 0
		else if (output.charAt(0) == '0') {
			return output.substring(1);
		}
		else {
			return output;
		}	
	}
}
