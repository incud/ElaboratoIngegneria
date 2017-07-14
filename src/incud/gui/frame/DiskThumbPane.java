package incud.gui.frame;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import incud.gui.controller.Controller;
import incud.immutable.Disco;
import incud.io.ImageLoader;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import javax.swing.JButton;

public class DiskThumbPane extends JPanel {

	public DiskThumbPane(Disco disco) {
		setLayout(new MigLayout("", "[]", "[100px][][][]"));
		
		JLabel lblIcona = new JLabel("");
		add(lblIcona, "cell 0 0,alignx center");
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ImageLoader.caricaImmagineNellaLabel(lblIcona, disco.percorsoFoto);
			}
		});
		
		String shortTitle = disco.titolo.substring(0, Math.min(12, disco.titolo.length()));
		JLabel lblTitolo = new JLabel(shortTitle);
		lblTitolo.setToolTipText(disco.titolo);
		add(lblTitolo, "cell 0 1,alignx center");
		
		String shortAuthor = disco.autore.substring(0, Math.min(12, disco.autore.length()));
		JLabel lblAutore = new JLabel(shortAuthor);
		lblAutore.setToolTipText(disco.autore);
		add(lblAutore, "cell 0 2,alignx center");
		
		JButton btnVediDettagli = new JButton("Vedi dettagli");
		btnVediDettagli.setActionCommand(Controller.OPEN_DETAIL);
		btnVediDettagli.addActionListener(new Controller(disco));
		add(btnVediDettagli, "cell 0 3,alignx center");
	}

}
