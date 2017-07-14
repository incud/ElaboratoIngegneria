package incud.gui.frame;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import incud.gui.controller.Controller;
import incud.stato.StatoUtente;

public class Menu extends JMenuBar {
	
	private JMenuItem creaElemento(String testo, String command) {
		JMenuItem item = new JMenuItem(testo);
		item.setActionCommand(command);
		item.addActionListener(new Controller());
		return item;
	}
	
	private JMenu createNavigaMenu() {
		JMenu naviga = new JMenu("Naviga");
		naviga.add(creaElemento("Torna al catalogo", Controller.OPEN_CATALOGUE));
		naviga.add(creaElemento("Vai al carrello", 	 Controller.OPEN_CART));
		naviga.add(creaElemento("Esci", 			 Controller.EXIT));
		return naviga;
	}
	
	private JMenu createAzioniClienteMenu() {
		JMenu azioni = new JMenu("Azioni");
		azioni.add(creaElemento("Ricerca", 				Controller.SEARCH));
		azioni.add(creaElemento("Informazioni cliente", Controller.SHOW_CLIENT_INFO));
		return azioni;
	}
	
	private JMenu createAzioniOspiteMenu() {
		JMenu azioni = new JMenu("Azioni");
		azioni.add(creaElemento("Ricerca", 	Controller.SEARCH));
		azioni.add(creaElemento("Login", 	Controller.LOGIN));
		azioni.add(creaElemento("Registra", Controller.REGISTER));
		return azioni;
	}
	
	private JMenu createPersonaleMenu() {
		throw new UnsupportedOperationException("Personale non ancora supportato");
	}
	
	public Menu(StatoUtente.Stato stato) {
		super();
		
		if(stato == StatoUtente.Stato.OSPITE) {
			add(createNavigaMenu());
			add(createAzioniOspiteMenu());
		} else if(stato == StatoUtente.Stato.CLIENTE) {
			add(createNavigaMenu());
			add(createAzioniClienteMenu());
		} else if(stato == StatoUtente.Stato.PERSONALE) {
			throw new UnsupportedOperationException("Personale non ancora supportato");
		}
	}
}
