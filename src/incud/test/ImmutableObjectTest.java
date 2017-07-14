package incud.test;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import incud.immutable.Autore;
import incud.immutable.Cliente;
import incud.immutable.Disco;
import incud.immutable.Genere;
import incud.immutable.Vendita;
import incud.stato.Inventario;
import incud.stato.RegistroClienti;
import incud.util.Filter;

public class ImmutableObjectTest {

	@Test(expected = RuntimeException.class) 
	public void diskWithNonExistentAuthor() {
		Disco.newBuilder().addAutore("giovanni");
	}

	@Test
	public void addAndSearchDisco() {
		String nomeAutore = "autore";
		Inventario.instance().addAutore(Autore.newBuilder()
				.addNome(nomeAutore)
				.addGenere(Genere.JAZZ)
				.build());
		assertTrue(Inventario.instance().existsArtista(nomeAutore));
		
		Disco disco = Disco.newBuilder()
				.addTitolo("x")
				.addAutore(nomeAutore)
				.addCodice("0")
				.build();
		Inventario.instance().addDisco(disco);
		assertEquals(Inventario.instance().getDischi().get(0), disco);
		
		Inventario.instance().addScorteDisco(disco, 10);
		assertTrue(Inventario.instance().existsScorta(disco));
		assertEquals(Inventario.instance().getScorte(disco), 10);
		
		Inventario.instance().removeScorteDisco(disco, 10);
		assertFalse(Inventario.instance().existsScorta(disco));
		
		Disco disco2 = Inventario.instance().findDisco("0");
		assertEquals(disco, disco2);
		
		Filter<Disco> filtro = new Filter<Disco>() {
			@Override
			public boolean canPass(Disco data) {
				return false;
			}
		};
		List<Disco> dischi = Inventario.instance().getDischi();
		assertEquals(dischi.size(), 1);
		assertEquals(Inventario.instance().filterDischi(filtro).size(), 0);
	}
	
	@Test
	public void addAndSearchCliente() {
		Cliente cliente = Cliente.newBuilder()
				.addNickname("gianni")
				.build();
		
		RegistroClienti.instance().addCliente(cliente);
		assertEquals(RegistroClienti.instance().findCliente("gianni"), cliente);
		assertTrue(RegistroClienti.instance().doesNicknameExists("gianni"));
		assertFalse(RegistroClienti.instance().doesNicknameExists("gianni55"));
	}
	
	public void checkSconto() {
		Cliente cliente1 = Cliente.newBuilder().addNickname("gianni1").build();
		Vendita vendita11 = Vendita.newBuilder().addNick("gianni1").addPrezzoTotale(300).build();
		Vendita vendita12 = Vendita.newBuilder().addNick("gianni1").addPrezzoTotale(300).build();
		Vendita vendita13 = Vendita.newBuilder().addNick("gianni1").addPrezzoTotale(300).build();
		Cliente cliente2 = Cliente.newBuilder().addNickname("gianni2").build();
		Vendita vendita21 = Vendita.newBuilder().addNick("gianni2").addPrezzoTotale(200).build();
		Vendita vendita22 = Vendita.newBuilder().addNick("gianni2").addPrezzoTotale(300).build();
		Vendita vendita23 = Vendita.newBuilder().addNick("gianni2").addPrezzoTotale(300).build();
		Cliente cliente3 = Cliente.newBuilder().addNickname("gianni3").build();
		Vendita vendita31 = Vendita.newBuilder().addNick("gianni3").addPrezzoTotale(300).build();
		Vendita vendita32 = Vendita.newBuilder().addNick("gianni3").addPrezzoTotale(300).build();
		Cliente cliente4 = Cliente.newBuilder().addNickname("gianni4").build();
		
		RegistroClienti.instance().addCliente(cliente1);
		RegistroClienti.instance().addCliente(cliente2);
		RegistroClienti.instance().addCliente(cliente3);
		RegistroClienti.instance().addCliente(cliente4);
		RegistroClienti.instance().addVendita(vendita11);
		RegistroClienti.instance().addVendita(vendita12);
		RegistroClienti.instance().addVendita(vendita13);
		RegistroClienti.instance().addVendita(vendita21);
		RegistroClienti.instance().addVendita(vendita22);
		RegistroClienti.instance().addVendita(vendita23);
		RegistroClienti.instance().addVendita(vendita31);
		RegistroClienti.instance().addVendita(vendita32);
		
		assertTrue(RegistroClienti.instance().canHaveSconti("gianni1"));
		assertFalse(RegistroClienti.instance().canHaveSconti("gianni2"));
		assertFalse(RegistroClienti.instance().canHaveSconti("gianni3"));
		assertFalse(RegistroClienti.instance().canHaveSconti("gianni4"));
		
		RegistroClienti.instance().useSconto("gianni1");
		assertFalse(RegistroClienti.instance().canHaveSconti("gianni1"));
		
	}
}