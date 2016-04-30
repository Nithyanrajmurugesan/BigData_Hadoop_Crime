package mrtools;

/**
 * Wish that we could extent the Stringbuilder or Stringbuffer classes but we cant.
 * This class, allows the building of a delimited Hadoop output String without as
 * many of the ugly chained appends cluttering the code as are normally required for this
 * 
 *
 */
public class OutputBuilder {
	private StringBuilder SB= new StringBuilder();	
	private String delimeter="\t";
/**
 * Create OutputBuilder with default Delimiter (tab)
 */
	public OutputBuilder(){
		
	}
/**
 * Create an OutputBuilder specifying a delimiter
 * @param D String delimiter
 */
	public OutputBuilder(String D){
		delimeter=D;
	}
/**
 * Append any Stringifyable object to the current content	
 * @param O
 */
	public void append(Object O){
		if(SB.length()>0)SB.append(delimeter);
		SB.append(O.toString());
	}
/**
 * Return as a String	
 */
	public String toString(){
		return SB.toString();
	}
/**
 * Return the length
 * @return int length
 */
	public int length(){
		return SB.length();
	}	
/**
 * Set the length (truncate) 
 * set to zero to reset 
 * @param i
 */
	public void setLength(int i){
		SB.setLength(i);
	}
/**
 * Shorthand for setLength(0)
 */
	public void clear(){
		SB.setLength(0);
	}
	
}
