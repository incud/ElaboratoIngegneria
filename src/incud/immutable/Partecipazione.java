package incud.immutable;

public final class Partecipazione {
    
    public Partecipazione(String musician, Strumento instrument) {
        musicista = musician;
        strumento = instrument;
    }
        
    public final String musicista;

    public final Strumento strumento;

    public String toString() {
    	return String.format("%s (usa %s)", musicista, strumento);
    }
}