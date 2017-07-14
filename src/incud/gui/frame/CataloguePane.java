package incud.gui.frame;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.Box;
import javax.swing.JScrollPane;

import incud.immutable.Disco;

public class CataloguePane extends JPanel {

	private JScrollPane scrollPane;
	
	public CataloguePane() {
		setLayout(new MigLayout("", "[grow][500px:500px][grow]", "[500px:500px,grow]"));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, "cell 0 0");
		
		scrollPane = new JScrollPane();
		add(scrollPane, "cell 1 0,grow");
		
		JPanel contenuto = new JPanel();
		scrollPane.setViewportView(contenuto);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, "cell 2 0");

	}
	
	public void carica(List<Disco> dischi) {
		JPanel contenuto = new JPanel();

		final int COLS = 3;
		final int RESTO = dischi.size() % COLS, RIGHE_COMPLETE = dischi.size() / COLS;
		final int ROWS = RESTO == 0 ? RIGHE_COMPLETE : RIGHE_COMPLETE + 1;
		
		contenuto.setLayout(new GridLayout(ROWS, COLS));
		for(Disco disco : dischi) {
			contenuto.add(new DiskThumbPane(disco));
		}
		
		scrollPane.setViewportView(contenuto);
		revalidate();
		repaint();
	}
}
