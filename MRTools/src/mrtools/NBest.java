package mrtools;

import java.util.*;
/**
 * 
 * Keeps the n-best assuming that the rank is an integer (such as a count)
 * for a Set of keys of type C
 * <ul>
 * <li> If the keys are strings create a new one as 
 * new NBest<String>(10) 
 * to keep the 10 best
 * <ul>
 * <li>Add entries one at a time or from a set of entries as <C,Integer> with addAll
 * <li>Retrieve the n-best as <C,Integer> in descending order with bestEntrySet()
 * <li>Uses floats internally to preserve duplicate rank entries
 * </ul>
 * </ul>
 * @param C The datatype of the Key (the Rank is always Integer)
 * 
 */

public class NBest<C> extends TreeMap<Float,C>

{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	float offset=0;
	float inc=0;
	int max=0;
/**
 * Create a new NBest for Ranking
 * @param m the number of "Best" to preserve
 */
	public NBest(int m){
		super();
		max=m;
		this.clear();
	}
/**
 * 	Reset this NBest for reuse
 *  One the initial parameter of how many to preserve is retained
 */
	public void clear(){
		 super.clear();
		 offset=0.9f / (float)max;
		 inc=0.9f / (float)max;
	}
/**
 * Add an item and Rank 	
 * @param c the item
 * @param i the rank
 * @return the item added
 */
	public C put(C c, Integer i){
		float f=(float)i;
		put(new Float(f+offset), c);
		offset+=inc;
		if(max>0 && size()>max)pollFirstEntry();
		return c;		
	}
/**
 * Add all items from a Map of the form < /item/ , /rank/ >	
 * @param M the Map
 */
	public void putMap(Map<C,Integer> M){
		for(Map.Entry<C, Integer> ME : M.entrySet()){
			this.put(ME.getKey(),ME.getValue());

		}
	}
/**
 * Return the best entries as a TreeSet	
 */
	public Set<Map.Entry<C, Integer>> bestEentrySet(){
		TreeSet<Map.Entry<C, Integer>> S = new TreeSet<Map.Entry<C,Integer>>();
		for (Map.Entry<Float, C>  M : super.entrySet()){
			S.add(new MyEntry<C>(M.getValue(),Math.round(M.getKey())));
		}
		return S;
	}
/**
 * 	Returns the best entries as a tab-delimited String
 */
	public String bestEntryTSV(){
		OutputBuilder OB=new OutputBuilder();
		for (Map.Entry<Float, C>  M : super.entrySet()){
                OB.append(M.getValue());  
            }

		return OB.toString();
	}
/**
 * Returns the best entries as /S/ separated item and rank in a
 * tab-delimited String	
 * @param S the separator between item and rank
 */
	public String bestEntryCountTSV(String S){
		OutputBuilder OB=new OutputBuilder();
		for (Map.Entry<Float, C>  M : super.entrySet()){
                OB.append(M.getValue()+S+((int)(Math.floor(M.getKey()))));  
            }

		return OB.toString();
	}
	final class MyEntry<K> implements Map.Entry<K, Integer>, Comparable<Map.Entry<K, Integer>>{
	    private final K key;
	    private Integer value;

	    public MyEntry(K key, Integer value) {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public K getKey() {
	        return key;
	    }

	    @Override
	    public Integer getValue() {
	        return value;
	    }

	    @Override
	    public Integer setValue(Integer value) {
	        Integer old = this.value;
	        this.value = value;
	        return old;
	    }

		@Override
		public int compareTo(java.util.Map.Entry<K, Integer> arg0) {
			//sort in reverse order (highest first)
			return arg0.getValue().compareTo(value);
		}
	}
	public static void main(String[] args ){
		NBest<String> N=new NBest<String>(3);
		for (int i =0;i<10;i++){
			//N.put(Integer.toString(i), i);		
		}
		for (Map.Entry<String, Integer> M : N.bestEentrySet()){
			System.out.printf("%s --- %d\n", M.getKey(),M.getValue());
		}
	}
	
}