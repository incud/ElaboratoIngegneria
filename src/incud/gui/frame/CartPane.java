package incud.gui.frame;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

import incud.gui.controller.Controller;
import incud.immutable.Disco;
import net.miginfocom.swing.MigLayout;
import java.util.List;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class CartPane extends JPanel {

	private JList<Disco> list;
	
	public CartPane() {
		setLayout(new MigLayout("", "[][grow][]", "[][grow][][]"));
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		add(verticalStrut_1, "cell 1 0");
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		add(horizontalStrut, "cell 0 1");
		
		list = new JList<>();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new Controller());
		add(list, "cell 1 1,grow");
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		add(horizontalStrut_1, "cell 2 1");
		
		JButton btnAcquista = new JButton("Acquista");
		btnAcquista.setActionCommand(Controller.BUY);
		btnAcquista.addActionListener(new Controller());
		add(btnAcquista, "cell 1 2,alignx center");
		
		Component verticalStrut = Box.createVerticalStrut(20);
		add(verticalStrut, "cell 1 3");
	}
	
	public void carica(List<Disco> carrello) {
		
		DefaultListModel<Disco> model = new DefaultListModel<>();
		
		for(Disco d : carrello) 
			model.addElement(d);
		
		list.setModel(model);
	}

}
