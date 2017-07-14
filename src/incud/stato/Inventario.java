package incud.stato;

import java.util.*;
import incud.immutable.*;
import incud.util.*;

public class Inventario {

    private List<Disco> dischi;
    
    private Multiset<Disco> scorte;
    
    private Map<String, Autore> autori;
    
    private static Inventario instance = new Inventario();
    
    public static Inventario instance() {
        return instance;
    }
    
    private Inventario() {
        dischi   = new LinkedList<>();
        scorte   = new Multiset<>();
        autori   = new HashMap<>();
    }

    // =================================================================
    // GESTIONE DISCHI
    
    public List<Disco> getDischi() {
        return Collections.unmodifiableList(dischi);
    }
    
    public List<Disco> getDischiOrdinati(Comparator<Disco> ordine) {
        List<Disco> dischiOrdinati = new LinkedList<>(dischi);
        Collections.sort(dischiOrdinati, ordine);
		return Collections.unmodifiableList(dischiOrdinati);
    }
    
    public List<Disco> filterDischi(Filter<Disco> filter) { 	
    	return Sieve.sieve(dischi, filter);   
    }
    
    public void addDisco(Disco disco) {
    	if(existsArtista(disco.autore) == false) {
    		throw new RuntimeException("L'autore " + disco.autore + " non e' nel sistema");
    	}
        dischi.add(disco);
    }
    
    public Disco findDisco(String codice) {
    	for(Disco disco : dischi) {
    		if(disco.codice.compareToIgnoreCase(codice) == 0) {
    			return disco;
    		} 
    	}
    	throw new AssertionError("Disco non trovato");
    }

    // =================================================================
    // GESTIONE AUTORI
    
    public void addAutore(Autore autore) {
        autori.put(autore.nomeArte, autore);
    }
    
    public boolean existsArtista(String nomeArtista) {
        return autori.containsKey(nomeArtista);
    }
    
    // =================================================================
    // GESTIONE SCORTE
    
    public void addScorteDisco(Disco d, int n) {
        scorte.add(d, n);
    }
    
    public void removeScorteDisco(Disco d, int n) {
    	scorte.remove(d, n);
    }
    
	public boolean existsScorta(Disco disco) {
		return scorte.get(disco) > 0;
	}
	
	public int getScorte(Disco disco) {
		return scorte.get(disco);
	}

}