package incud.stato;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;

import incud.immutable.Cliente;
import incud.immutable.Disco;
import incud.immutable.Genere;
import incud.immutable.Vendita;
import incud.util.Multiset;

public class RegistroClienti {

    private static final double ACQUISTO_IMPORTANTE = 250.0;
	
	private static final int ACQUISTI_NECESSARI_PER_SCONTO = 3;

	private Collection<Cliente> clienti;
    
    private Multiset<Cliente> venditeImportanti;
    
    private Collection<Vendita> vendite;
	
	private static RegistroClienti instance = new RegistroClienti();
    
    public static RegistroClienti instance() { 
    	return instance; 
    }
    
    private RegistroClienti() {
        clienti = new LinkedList<>();
        venditeImportanti = new Multiset<>();
        vendite = new LinkedList<>();
    }
    
    public void addCliente(Cliente c) {
        clienti.add(c);
    }
    
    public Cliente findCliente(String nick) {
    	for(Cliente c : clienti) {
    		if(c.nickname.equalsIgnoreCase(nick)) {
    			return c;
    		}
    	}
    	return null;
    }
    
	public boolean doesNicknameExists(String nick) {
		return RegistroClienti.instance().findCliente(nick) != null;
	}
    
    public void addVendita(Vendita v) {
    	vendite.add(v);
    	if(v.prezzoTotale >= ACQUISTO_IMPORTANTE) {
    		venditeImportanti.add(findCliente(v.nickCliente));
    	}
    }

    public boolean canHaveSconti(String nicknameCliente) {
    	Cliente cliente = findCliente(nicknameCliente);
        return venditeImportanti.get(cliente) >= ACQUISTI_NECESSARI_PER_SCONTO;
    }

    public void useSconto(String nicknameCliente) {
    	
    	Cliente cliente = findCliente(nicknameCliente);
    	
        if(canHaveSconti(nicknameCliente) == false) {
        	throw new RuntimeException("Il cliente non può ususfruire dello sconto");
        } else {
        	venditeImportanti.remove(cliente, ACQUISTI_NECESSARI_PER_SCONTO);
        }
    }
    
    private Multiset<Genere> conteGenereVendite(String nickCliente) {
    	Multiset<Genere> occorrenzeGenere = new Multiset<>();
    	for(Vendita vendita : vendite) {
			if(vendita.nickCliente.equals(nickCliente)) {
				for(Disco disco : vendita.prodotti) {
					occorrenzeGenere.add(disco.genere);
				}
			}
		}
    	return occorrenzeGenere;
    }
    
    public Comparator<Disco> getPreferenzeCliente(String nicknameCliente) {
    	
    	Multiset<Genere> occorrenzeGenere = conteGenereVendite(nicknameCliente);
    	
		Genere generePreferito = occorrenzeGenere.getElementWithMaxOccurrences();
		System.out.println("Genere preferito: " + generePreferito);
		
		if(generePreferito == null) {
			return new Comparator<Disco>() {
				public int compare(Disco a, Disco b) { return 0; }
			};
		} else {
			return new Comparator<Disco>() {
				public int compare(Disco o1, Disco o2) {
					return o1.genere == generePreferito ? -1 : 0;
				}
			};
		}	
    }
}
