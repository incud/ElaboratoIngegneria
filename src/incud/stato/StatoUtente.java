package incud.stato;

import java.util.*;

import incud.immutable.Cliente;
import incud.immutable.Disco;
import incud.immutable.Genere;


public class StatoUtente {
	
	private Stato stato;
    
    private Cliente clienteRegistratoCorrente;
    
    private List<Disco> dischi;
        
    private static StatoUtente istanza = new StatoUtente();
    
    public static StatoUtente instance() {
        return istanza;
    }
	
    private StatoUtente() {
    	setUtenteOspite();
        clienteRegistratoCorrente = null;
        dischi = new LinkedList<>();
    }

    public void setUtenteOspite() {
    	stato = Stato.OSPITE;
    }
    
    public void setUtenteCliente(Cliente cliente) {
    	stato = Stato.CLIENTE;
    	clienteRegistratoCorrente = cliente;
    }
    
    public void setUtentePersonale() {
    	throw new UnsupportedOperationException("Non ancora supportato");
    }
    
    public Cliente getClienteLoggato() {
    	if(stato == Stato.CLIENTE) {
    		return clienteRegistratoCorrente;
    	} else {
    		throw new RuntimeException("Non sei un cliente autenticato");
    	}
    }
    
    public String getNickClienteLoggato() {
    	return clienteRegistratoCorrente.nickname;
    }
    
    public Stato getStato() {
    	return stato;
    }
    
	public List<Disco> getCarrello() {
		return Collections.unmodifiableList(dischi);
	}
	
	public void addToCarrello(Disco disco) {
		dischi.add(disco);
	}
	
	public void removeFromCarrello(int index) {
		dischi.remove(index);
	}
	
	public void emptyCarrello() {
		dischi.removeAll(dischi);
	}
	
	public enum Stato {
	    OSPITE,
	    CLIENTE,
	    PERSONALE
	}
}