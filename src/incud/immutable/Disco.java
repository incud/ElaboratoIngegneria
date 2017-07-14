package incud.immutable;

import java.util.*;

import incud.stato.Inventario;

public final class Disco {
	
    public final String codice;
    
    public final String titolo;
    
    public final String autore;
    
    public final double prezzo;
    
    public final Genere genere;
    
    public final List<Canzone> brani;
    
    public final String descrizione;
    
    public final String percorsoFoto;
        
    public final Date dataCaricamento;
	
    public static Builder newBuilder() {
        return new Builder();
    }

    private Disco(Builder builder) {
    	codice = builder.codice;
    	titolo = builder.titolo;
    	autore = builder.autore;
    	prezzo = builder.prezzo;
    	genere = builder.genere;
    	brani = Collections.unmodifiableList(builder.brani);
    	descrizione = builder.descrizione;
    	percorsoFoto = builder.percorsoFoto;
    	dataCaricamento = builder.dataCaricamento;
    }
    
    public String toString() {
    	return String.format("%30s %30s    %2.2f€", titolo, "("+autore+")", prezzo);
    }
    
    // =====================================================
    // BUILDER

    public static class Builder {
        
        private String codice;
        
        private String titolo;
        
        private String autore;
        
        private double prezzo;
        
        private Genere genere;
        
        private List<Canzone> brani;
        
        private String descrizione;
        
        private String percorsoFoto;
                
        private Date dataCaricamento;
        
        private Builder() {
            brani = new ArrayList<>();
        }
     
        public Builder addCodice(String code) {
            codice = code;
            return this;
        }
        
        public Builder addTitolo(String title) {
            titolo = title;
            return this;
        }
        
        public Builder addAutore(String author) {
            autore = author;
            if(!Inventario.instance().existsArtista(author)) {
            	throw new RuntimeException("L'artista dell'album non esiste");
            }
            return this;
        }
        
        public Builder addPrezzo(double price) {
            prezzo = price;
            return this;
        }
        
        public Builder addGenere(Genere genre) {
            genere = genre;
            return this;
        }
        
        public Builder addCanzone(Canzone song) {
            brani.add(song);
            return this;
        }
        
        public Builder addDescrizione(String description) {
            descrizione = description;
            return this;
        }
        
        public Builder addPercorsoFoto(String path) {
            percorsoFoto = path;
            return this;
        }
        
        public Builder addDataCaricamento(Date d) {
            dataCaricamento = d;
            return this;
        }
        
        public Disco build() {
            return new Disco(this);
        }
    }

}