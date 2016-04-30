package mrtools;
/**
 * Used to read undelimited, fixed-width column data as fields
 * field widths must be specified when creating a new Column Reader
 * field numbers are <b>one</b>-based
 * fields can be named (names are case sensitive)

 * int[] Columns={2,3,4,1,2,4,3,4};
		ColumnReader Reader=new ColumnReader(Columns);
		Reader.setColName(1, "Case ID");
		Reader.reset(line);
		Reader.get("Case ID") //equiv. to Reader.get(1)
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Arrays;

public class ColumnReader {
	
	public static void main(String[] args){
		
		int[] Columns={2,3,4,1,2,4,3,4};
		ColumnReader Reader=new ColumnReader(Columns);
		Reader.setColName(5, "Age At Death");
		Reader.setColName(7, "Cause Code");
		Reader.setColName(8,"Count");
		try {
			BufferedReader R=new BufferedReader(new InputStreamReader(
					new FileInputStream("/home/student/Downloads/Mort7988.txt")));
			String line=null;
			int i=0;
			while((line=R.readLine())!=null&& i++<10){
				Reader.reset(line);
				System.out.println(Reader.get(7)+";"+Reader.getInt(8));
			}
			R.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Matcher M=null;
	private boolean match=false;
	private ArrayList<String> ColNames=null;
	int colCount=-0;
	
	private void setup(int[] Widths){
		StringBuilder rowPattern=new StringBuilder();
		for(Integer I : Widths){
			rowPattern.append("(.{").append(I).append(",").append(I).append("})");
			colCount+=1;
		}
		M = Pattern.compile(rowPattern.toString()).matcher("");
	}
/**
 * Create a new ColumnReader
 * @param Widths an array of ints listing field widths
 */
	public ColumnReader(int[] Widths){
		setup(Widths);
	}
/**
 * Assigns textual names to numbered columns (1-based)			
 * @param names an array of Strings
 */
	public void setColNames(String[] names){
		ColNames=new ArrayList<String>(Arrays.asList(names));
	}
/**
 * Assign a name to a single column
 * @param col int the (one-based) column number
 * @param name String the name
 */
	public void setColName(int col, String name){
		if (ColNames==null){
			ColNames=new ArrayList<String>(Arrays.asList(new String[colCount]));
		}
		ColNames.set(col-1, name);
	}
/**
 * Create a new ColumnReader with widths and names	
 * @param Widths
 * @param names
 */
	public ColumnReader(int[] Widths,String[] names){
		setup(Widths);
		setColNames(names);
	}
	public boolean match(){
		return match;
	}
/**
 * Apply this reader to a new String	
 * @param S the text to interpret as fields
 * @return boolean true=succeeded in finding fields in the text
 		false=failed to find fields in the new text
 */
	public boolean reset(String S){
		M.reset(S);
		match = M.find();
		return match;
	}
/**
 * Get field by number	
 * @param col the (one-based) field number
 * @return the field as a String
 */
	public String get(int col){
		if(!match)return null;
		return M.group(col);
	}
/**
 * get field by name	
 * @param col The column name
 * @return the field as a String or null 
 */
	public String get(String col){
		int colnum = ColNames.indexOf(col)+1;
		if(colnum==-1)throw new IndexOutOfBoundsException("Field: /"+col+"/ not found in field list");
		return get(colnum);
	}	 
	public int getInt(int col){
		return Integer.parseInt(get(col).trim());
	}
	public int getInt(String col){
		return Integer.parseInt(get(col).trim());
	}
	public float getFloat(int col){
		return Float.parseFloat(get(col).trim());
	}
	public float getFloat(String col){
		return Float.parseFloat(get(col).trim());
	}
	public double getDouble(int col){
		return Double.parseDouble(get(col).trim());
	}
	public double getDouble(String col){
		return Double.parseDouble(get(col).trim());
	}
}
