package incud.immutable;

import java.util.*;

public final class Canzone {	
    
    public final String titolo;

    public final Collection<Partecipazione> partecipazioni;
	
    public static Builder newBuilder() {
        return new Builder();
    }

    private Canzone(Canzone.Builder builder) {
        titolo = builder.titolo;
        partecipazioni = Collections.unmodifiableCollection(builder.partecipazioni);
    }
    
    public String toString() {
    	return titolo;
    }
    
    // =====================================================
    // BUILDER

    public static class Builder {

	    private String titolo;

	    private Collection<Partecipazione> partecipazioni;

	    private Builder() {
	    	partecipazioni = new HashSet<>();
	    }
	        
	    public Builder addTitolo(String title) {
	    	titolo = title;
	    	return this;
	    }
	    
	    public Builder addPartecipazione(Partecipazione p) {
	        partecipazioni.add(p);
	        return this;
	    }
	    
	    public Canzone build() {
	        return new Canzone(this);
	    }
	}

}