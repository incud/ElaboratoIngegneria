package incud.gui.frame;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import incud.stato.StatoUtente;

public class Frame extends JFrame {

	public static final String CATALOGO = "Catalogo", DETTAGLI = "Dettagli", CARRELLO = "Carrello";
	
	private final JPanel content;
	private final CardLayout layout;
	
	private final CataloguePane catalogo;
	private final DetailsPane dettagli;
	private final CartPane carrello;
	
	private static Frame instance = new Frame();
	
	public static Frame instance() {
		return instance;
	}
	
	private Frame() {
		super();
		setLocationRelativeTo(null);
		
		layout  = new CardLayout();
		content = new JPanel(layout);
		
		catalogo = new CataloguePane();
		dettagli = new DetailsPane();
		carrello = new CartPane();
		content.add(catalogo, CATALOGO);
		content.add(dettagli, DETTAGLI);
		content.add(carrello, CARRELLO);

		setContentPane(content);
		cambiaMenu(StatoUtente.Stato.OSPITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}
	
	public void cambiaVista(String nomeVista) {
		setTitle(nomeVista);
		layout.show(content, nomeVista);
	}
	
	public void cambiaMenu(StatoUtente.Stato stato) {
		setJMenuBar(new Menu(stato));
		revalidate();
		repaint();
	}
	
	public CataloguePane getPannelloCatalogo() {
		return catalogo;
	}
	
	public DetailsPane getPannelloDettagli() {
		return dettagli;
	}
	
	public CartPane getPannelloCarrello() {
		return carrello;
	}
}
