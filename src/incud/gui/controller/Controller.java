package incud.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JList;

import incud.gui.dialogs.PayDialog;
import incud.gui.dialogs.ClientDialog;
import incud.gui.dialogs.LoginDialog;
import incud.gui.dialogs.MessageDialog;
import incud.gui.dialogs.RegisterDialog;
import incud.gui.dialogs.SearchDialog;
import incud.gui.frame.Frame;
import incud.immutable.Cliente;
import incud.immutable.Disco;
import incud.immutable.Vendita;
import incud.stato.Inventario;
import incud.stato.RegistroClienti;
import incud.stato.StatoUtente;
import incud.stato.StatoUtente.Stato;
import incud.util.Filter;

public class Controller extends MouseAdapter implements ActionListener {
	
	private static final double SCONTO = 10;

	public static final String OPEN_CATALOGUE 	= "Open catalogue";
	public static final String OPEN_DETAIL 		= "Open detail";
	public static final String OPEN_CART 		= "Open cart";
	public static final String SEARCH 			= "Search";
	public static final String LOGIN 			= "Login";
	public static final String REGISTER 		= "Register";
	public static final String EXIT 			= "Exit";
	public static final String ADD_TO_CART 		= "Add to cart";
	public static final String BUY 				= "Buy";
	public static final String ASK_FOR_DISCOUNT = "Ask for discount";
	public static final String SHOW_CLIENT_INFO = "Show client info";
	
	private final Disco disco;
	
	public Controller() {
		this.disco = null;
	}
	
	public Controller(Disco disco) {
		this.disco = disco;
	}

	public void openCatalogue() {
		
		switch(StatoUtente.instance().getStato()) {
		case OSPITE: { // inserisco i dischi nel catalogo
				List<Disco> dischi = Inventario.instance().getDischi();
				Frame.instance().getPannelloCatalogo().carica(dischi);
				Frame.instance().cambiaVista(Frame.CATALOGO);
			} break;
		case CLIENTE: { // inserisco i dischi nel catalogo ordinati per preferenza del clietnte loggato
				Cliente cliente = StatoUtente.instance().getClienteLoggato();
				Comparator<Disco> preferenze = RegistroClienti.instance().getPreferenzeCliente(cliente.nickname);
				List<Disco> dischi = Inventario.instance().getDischiOrdinati(preferenze);
				Frame.instance().getPannelloCatalogo().carica(dischi);
				Frame.instance().cambiaVista(Frame.CATALOGO);
			} break;
		case PERSONALE:
			throw new UnsupportedOperationException("Personale non ancora suportato");
		}
	}
	
	public void openDetails() {
		Frame.instance().getPannelloDettagli().carica(disco);
		Frame.instance().cambiaVista(Frame.DETTAGLI);
	}
	
	public void openCart() {
		List<Disco> carrello = StatoUtente.instance().getCarrello();
		Frame.instance().getPannelloCarrello().carica(carrello);
		Frame.instance().cambiaVista(Frame.CARRELLO);
	}
	
	public void search() {
		SearchDialog dialog = new SearchDialog();
		dialog.setVisible(true);
		
		if(!dialog.isAnnullato()) {
			Filter<Disco> filtro = dialog.getFiltro();
			List<Disco> dischi = Inventario.instance().filterDischi(filtro);
			
			// carico nel catalogo i soli dischi trovati
			Frame.instance().getPannelloCatalogo().carica(dischi);
			Frame.instance().cambiaVista(Frame.CATALOGO);
			
			// setto il titolo della finestra
			if(dischi.size() == 0) {
				Frame.instance().setTitle("Ricerca: non ci sono elemente trovati");
				(new MessageDialog("Non ci sono elementi trovati")).show();
			} else if(dischi.size() == 1) {
				Frame.instance().setTitle("Ricerca: 1 elemento trovato");
			} else {
				Frame.instance().setTitle("Ricerca: " + dischi.size() + " elementi trovati");
			}
		}
	}
	
	public void login() {
		LoginDialog dialog = new LoginDialog();
		dialog.setVisible(true);
		
		if(!dialog.isAnnullato()) {
			String nickname = dialog.getNickname();
			Cliente cliente = RegistroClienti.instance().findCliente(nickname); // LoginDialog mi assicura che esista
			StatoUtente.instance().setUtenteCliente(cliente);
			Frame.instance().cambiaMenu(StatoUtente.instance().getStato());
		}
	}
	
	public void register() {
		RegisterDialog dialog = new RegisterDialog();
		dialog.setVisible(true);
		
		if(!dialog.isAnnullato()) {
			(new MessageDialog("Registrazione avvenuta correttamente")).show();
			
			Cliente cliente = dialog.getCliente(); // RegisterDialog mi assicura che esiste
			RegistroClienti.instance().addCliente(cliente);
			StatoUtente.instance().setUtenteCliente(cliente);
			Frame.instance().cambiaMenu(StatoUtente.instance().getStato());
		}
	}
	
	public void addToCart() {
		if(Inventario.instance().existsScorta(disco)) {
			Inventario.instance().removeScorteDisco(disco, 1);
			StatoUtente.instance().addToCarrello(disco);
			MessageDialog dialog = new MessageDialog("Prodotto aggiunto al carrello", "Vai al carrello", "Rimani qui");
			boolean ok = dialog.show();
			if(ok) {
				openCart();
			} else {
				Frame.instance().getPannelloDettagli().updatePezziInMagazzino();
			}
		} else {
			(new MessageDialog("Siamo spiacenti, non ci sono più scorte di questo disco")).show();
		}
	}
	
	public void buy() {
		if(StatoUtente.instance().getCarrello().size() == 0) {
			(new MessageDialog("Non ci sono elementi nel carrello")).show();
			return;
		}
		
		if(StatoUtente.instance().getStato() == Stato.OSPITE) {
			login();
		}
		
		if(StatoUtente.instance().getStato() == Stato.CLIENTE) {
			PayDialog dialog = new PayDialog();
			dialog.setVisible(true);
			
			if(!dialog.isAnnullato()) {
				Vendita.Builder builder = Vendita.newBuilder()
						.addNick(StatoUtente.instance().getNickClienteLoggato())
						.addDataAcquisto((new GregorianCalendar()).getTime())
						.addIp("10.0.50.210")
						.addPagamento(dialog.getPagamento())
						.addConsegna(dialog.getConsegna());
				
				double prezzo = 0.0f;
				for(Disco disco : StatoUtente.instance().getCarrello()) {
					builder.addProdotto(disco);
					prezzo += disco.prezzo;
				}
				
				builder.addPrezzoTotale(askForDiscount() ? prezzo * (100.0 - SCONTO) / 100.0 : prezzo);
				Vendita vendita = builder.build();
				
				RegistroClienti.instance().addVendita(vendita);
				StatoUtente.instance().emptyCarrello();
				
				openCatalogue();
			}
		}
	}
	
	private boolean askForDiscount() {
		String nick = StatoUtente.instance().getNickClienteLoggato();
		if(RegistroClienti.instance().canHaveSconti(nick)) {
			
			MessageDialog message = new MessageDialog("Hai diritto ad uno sconto del " + SCONTO + "%. Ne vuoi usufruire?", "Si, voglio usufruire dello sconto", "No");
			boolean ok = message.show();
			
			if(ok) {
				RegistroClienti.instance().useSconto(nick);
				return true;
			} 
		}
		
		return false;
	}
	
	public void showClientInfo() {
		ClientDialog dialog = new ClientDialog();
		dialog.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		case OPEN_CATALOGUE: 
			openCatalogue();
			break;
		case OPEN_DETAIL: 	
			openDetails();
			break;
		case OPEN_CART:
			openCart();
			break;
		case SEARCH: 
			search();
			break;
		case LOGIN:
			login();
			openCatalogue();
			break;
		case REGISTER: 
			register();
			break;
		case EXIT: 
			System.exit(0);
			break;
		case ADD_TO_CART: 
			addToCart();
			break;
		case BUY:
			buy();
			break;
		case ASK_FOR_DISCOUNT:
			askForDiscount();
			break;
		case SHOW_CLIENT_INFO:
			showClientInfo();
			break;
		default:
			throw new UnsupportedOperationException("Comando sconosciuto: " + e.getActionCommand());
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if((e.getSource() instanceof JList<?>) == false) {
			throw new UnsupportedOperationException("Hai applicato il Controller al MouseListener sbagliato");
		}
		
		if(e.getClickCount() == 2) {
			
			JList<Disco> list = (JList<Disco>)e.getSource();
			int index = list.locationToIndex(e.getPoint());
			Disco disco = list.getModel().getElementAt(index);
			
			MessageDialog dialog = new MessageDialog("Vuoi rimuovere il disco " + disco.titolo + "dal carrello?", "Rimuovi", "Non rimuovere");
			boolean remove = dialog.show();
			
			if(remove) {
				Inventario.instance().addScorteDisco(disco, 1);
				StatoUtente.instance().removeFromCarrello(index);
				openCart();
			}
		}
	}
}
