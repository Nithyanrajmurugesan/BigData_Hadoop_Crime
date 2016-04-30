package mrtools;
/*
 * Keep a count of keys as they are added
 * return a map of <key,count> when asked
 * 
 */

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;

public class CountMap<C> extends HashMap<C,Integer> 
{
	private static final long serialVersionUID = 1L;
	public CountMap(){
	}
	public void put(C v){
		if (get(v)!=null){
			put(v,get(v)+1);
		}else{
			put(v, 1);
		}
	}
	@SuppressWarnings("unchecked")
	public int countInto(@SuppressWarnings("rawtypes") Iterator I){
		int total=0;
		while (I.hasNext()) {
			total++;
			Object O = I.next();
			//Everything but a Text has a "get" method. Find and call the right one.
			//The type-cast is required to please the compiler
			//Ugly -- but functionally generic
			try {
				put((C)(O.getClass().getMethod("get").invoke(O)));
			} catch (SecurityException | NoSuchMethodException | IllegalArgumentException | 
					IllegalAccessException | InvocationTargetException  e) {
					//No get(); try toString
					put((C) O.toString());

			}
		}
		return total;
	}		 
}
