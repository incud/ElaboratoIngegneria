package incud.util;

import java.util.*;
import java.util.Map.Entry;

public class Multiset<T> {

    private HashMap<T, Integer> contatori;
	
    public Multiset() {
    	contatori = new HashMap<>();
    }
    
    public void add(T data) {
        add(data, 1);
    }
    
    public void add(T data, int occ ) {
    	if(contatori.containsKey(data)) {
        	Integer i = contatori.get(data);
        	contatori.put(data, i+occ);
        } else {
        	contatori.put(data, occ);
        }
    }

    public void remove(T data) {
    	remove(data, 1);
    }
    
    public void remove(T data, int occ) {
    	if(contatori.containsKey(data)) {
        	Integer i = contatori.get(data);
        	if(i - occ >= 0) {
        		contatori.put(data, i-occ);
        	} else {
        		throw new RuntimeException("Non puoi rimuovere oltre le " + i + " unità");
        	}
        } else {
        	throw new RuntimeException("Non ci sono unità di questo elemento");
        }
    }
    
    public int get(T data) {
    	Integer i = contatori.get(data);
    	return i == null ? 0 : i;
    }
    
    public T getElementWithMaxOccurrences() {
    	
    	T max = null;
    	int maxOcc = -1;
    	
    	for(Entry<T, Integer> entry : contatori.entrySet()) {
    		if(entry.getValue() > maxOcc) {
    			max    = entry.getKey();
    			maxOcc = entry.getValue();
    		}
    	}
    	
    	return max;
    }

}