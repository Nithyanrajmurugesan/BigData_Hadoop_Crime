package mrtools;
/**
 * 
 * Tools for handling input to be interpreted as a number
 *
 */
public class number {
	
	public static void main(String[] args){
		System.out.println( clean("($3,450.25)"));
	}
/**
 * clean up input to only acceptable chars, convert accounting notation to decimal
 * and interpret numerically	
 * @param S String to be interpreted
 * @return float the interpreted number
 */
	public static float clean (String S){
		Float F = Float.parseFloat(S.replaceAll("[^0123456789\\.]", ""));
		if(S.startsWith("-")|| S.matches("\\(.*\\)"))F*=-1;
		return F;
	}
/**
 * Lisp like predicate	
 * @param S String to test
 * @return boolean 
 */
	public static boolean isaNumber(String S){
		try {
			clean(S);
			return true;
		}catch(NumberFormatException nfe){
			return false;
			
		}
	}

}
