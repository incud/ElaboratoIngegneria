package incud.util;

import java.util.LinkedList;
import java.util.List;

public class Sieve {

	public static <T> List<T> sieve(List<T> list, Filter<T> filter) {
		
		List<T> newList = new LinkedList<T>();
		
		for(T element : list) {
			if(filter.canPass(element)) {
				newList.add(element);
			}
		}
		
		return newList;		
	}
}
