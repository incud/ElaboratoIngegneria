package incud.gui.frame;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import incud.gui.controller.Controller;
import incud.immutable.Canzone;
import incud.immutable.Disco;
import incud.io.ImageLoader;
import incud.stato.Inventario;
import incud.util.FormatoData;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;

public class DetailsPane extends JPanel {

	private JLabel lblIcona, lblDiskCodice, lblDiskTitolo, lblDiskautore, lblDiskPrezzo, lblDiskGenere, lblDiskData, lblDiskPezzi;
	private JTextArea textPane;
	private JButton btnAcquista;
	
	private Disco ultimoDisco;
	
	public DetailsPane() {
		setLayout(new MigLayout("", "[grow][120px:120px:120px][180px:180px:180px][grow]", "[grow][100px:n][][][][][][][][][100px:n][][grow]"));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, "cell 1 0");
		
		lblIcona = new JLabel("icona");
		add(lblIcona, "cell 1 1 2 1,alignx center,aligny center");
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		add(verticalStrut_2, "cell 1 2");
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, "cell 0 3");
		
		JLabel lblCodice = new JLabel("Codice:");
		add(lblCodice, "cell 1 3,alignx left");
		
		lblDiskCodice = new JLabel("Disk Codice");
		add(lblDiskCodice, "cell 2 3");
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, "cell 3 3");
		
		JLabel lblTitolo = new JLabel("Titolo:");
		add(lblTitolo, "cell 1 4,alignx left");
		
		lblDiskTitolo = new JLabel("Disk Titolo");
		add(lblDiskTitolo, "cell 2 4,alignx left");
		
		JLabel lblAutore = new JLabel("Autore:");
		add(lblAutore, "cell 1 5,alignx left");
		
		lblDiskautore = new JLabel("DiskAutore");
		add(lblDiskautore, "cell 2 5,alignx left");
		
		JLabel lblPrezzo = new JLabel("Prezzo:");
		add(lblPrezzo, "cell 1 6,alignx left");
		
		lblDiskPrezzo = new JLabel("Disk Prezzo");
		add(lblDiskPrezzo, "cell 2 6,alignx left");
		
		JLabel lblGenere = new JLabel("Genere:");
		add(lblGenere, "cell 1 7,alignx left");
		
		lblDiskGenere = new JLabel("Disk genere");
		add(lblDiskGenere, "cell 2 7,alignx left");
		
		JLabel lblCaricatoIl = new JLabel("Caricato il:");
		add(lblCaricatoIl, "cell 1 8");
		
		lblDiskData = new JLabel("Disk Data");
		add(lblDiskData, "cell 2 8");
		
		JLabel lblPezziInMagazzino = new JLabel("Unità rimanenti: ");
		add(lblPezziInMagazzino, "cell 1 9");
		
		lblDiskPezzi = new JLabel("Disk Pezzi");
		add(lblDiskPezzi, "cell 2 9");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 10 2 1,grow");
		
		textPane = new JTextArea();
		textPane.setEditable(false);
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		scrollPane.setViewportView(textPane);
		
		btnAcquista = new JButton("Aggiungi al carrello");
		btnAcquista.setActionCommand(Controller.ADD_TO_CART);
		add(btnAcquista, "cell 1 11 2 1,alignx center");
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1, "cell 1 12");

	}
	
	public void carica(Disco disco) {
		ultimoDisco = disco;
		
		for(ActionListener al : btnAcquista.getActionListeners()) 
			btnAcquista.removeActionListener(al);
		btnAcquista.addActionListener(new Controller(disco));
		
		ImageLoader.caricaImmagineNellaLabel(lblIcona, disco.percorsoFoto);
		lblDiskTitolo.setText(disco.titolo);
		lblDiskCodice.setText(disco.codice);
		lblDiskGenere.setText(disco.genere.toString());
		lblDiskPrezzo.setText(String.format("%2.2f€", disco.prezzo));
		lblDiskautore.setText(disco.autore);
		lblDiskData.setText(FormatoData.instance().format(disco.dataCaricamento));
		updatePezziInMagazzino();
		
		StringBuilder desc = new StringBuilder(disco.descrizione+"\n");
		int i = 1;
		for(Canzone c : disco.brani) {
			desc.append("    "+i+". "+c.toString()+"\n");
			i++;
		}
		textPane.setText(desc.toString());
		
		revalidate();
		repaint();
	}
	
	public void updatePezziInMagazzino() {
		int pezzi = Inventario.instance().getScorte(ultimoDisco);
		lblDiskPezzi.setText("" + pezzi);
	}
}
