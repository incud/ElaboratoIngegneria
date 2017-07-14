package incud.immutable;

import java.util.*;

public final class Vendita {

    public final String nickCliente;

    public final Collection<Disco> prodotti;
    
    public final double prezzoTotale;
    
    public final Date dataAcquisto;
    
    public final String ip;
    
    public final Pagamento pagamento;
    
    public final Consegna consegna;
	
    public static Builder newBuilder() {
    	return new Builder();
    }
	
    private Vendita(Builder builder) {
    	nickCliente = builder.nickCliente;
    	prodotti = Collections.unmodifiableCollection(builder.prodotti);
    	prezzoTotale = builder.prezzoTotale;
    	dataAcquisto = builder.dataAcquisto;
    	ip = builder.ip;
    	pagamento = builder.pagamento;
    	consegna = builder.consegna;
    }
        
    public String toString() {
    	return String.format("%3.2f %s", prezzoTotale, Arrays.toString(prodotti.toArray(new Disco[prodotti.size()])));
    }

    public static class Builder {
        
        private String nickCliente;
        
        private Collection<Disco> prodotti;
        
        private double prezzoTotale;
        
        private Date dataAcquisto;
        
        private String ip;
        
        private Pagamento pagamento;
        
        private Consegna consegna;
        
        private Builder() {
            prodotti = new LinkedList<>();
        }

        public Builder addNick(String nick) {
            nickCliente = nick;
            return this;
        }
        
        public Builder addProdotto(Disco disco) {
            prodotti.add(disco);
            return this;
        }
        
        public Builder addPrezzoTotale(double prezzo) {
        	prezzoTotale = prezzo;
        	return this;
        }

        public Builder addDataAcquisto(Date data) {
            dataAcquisto = data;
            return this;
        }
        
        public Builder addIp(String ip) {
            this.ip = ip;
            return this;
        }
        
        public Builder addPagamento(Pagamento p) {
            pagamento = p;
            return this;
        }
        
        public Builder addConsegna(Consegna c) {
            consegna = c;
            return this;
        }
       
        public Vendita build() {
            return new Vendita(this);
        }
    }
}