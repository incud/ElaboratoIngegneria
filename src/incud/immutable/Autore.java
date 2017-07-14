package incud.immutable;
import java.util.*;

public final class Autore {
	
    public final String nomeArte;
 
    public final Genere generePrincipale;
    
    public final Date annoNascita;
    
    public final Collection<Strumento> strumentiSuonati;
    
    public static Builder newBuilder() {
    	return new Builder();
    }
	
    private Autore(Builder builder) {
        nomeArte 		 = builder.name;
        generePrincipale = builder.genre;
        annoNascita 	 = builder.bornYear;
        strumentiSuonati = Collections.unmodifiableCollection(builder.instruments);
    }
    
    public String toString() {
    	return nomeArte;
    }
    
    public static class Builder {
    	
    	private String name;
    	
    	private Genere genre;
    	
    	private Date bornYear;
    	
    	private Collection<Strumento> instruments;
    	
    	private Builder() {
    		instruments = new LinkedList<>();
    	}
    	
    	public Builder addNome(String name) {
    		this.name = name;
    		return this;
    	}
    	
    	public Builder addGenere(Genere genre) {
    		this.genre = genre;
    		return this;
    	}
    	
    	public Builder addAnnoNascita(Date date) {
    		bornYear = date;
    		return this;
    	}
    	
    	public Builder addStrumento(Strumento s) {
    		instruments.add(s);
    		return this;
    	}
    	
    	public Autore build() {
    		return new Autore(this);
    	}
    }
}