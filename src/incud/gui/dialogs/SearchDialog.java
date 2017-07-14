package incud.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.NumberFormatter;

import incud.immutable.*;
import incud.util.AuthorFilter;
import incud.util.Filter;
import incud.util.GenreFilter;
import incud.util.PriceFilter;
import incud.util.TitleFilter;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.SpringLayout;
import net.miginfocom.swing.MigLayout;

public class SearchDialog extends JDialog implements ItemListener, ActionListener {

	private final JPanel contentPanel = new JPanel();
	private final JPanel cardPanel;
	
	private final JComboBox<String> comboBox;
	
	private final JComboBox<Genere> genere;
	private final JTextField titolo;
	private final JTextField autore;
	private final JFormattedTextField prezzoMin;
	private final JFormattedTextField prezzoMax;
	
	// se si preme 'Avvia ricerca' prende valore true
	private boolean annullato = true;
	
	private static final String[] opzioni = new String[] {"Ricerca per genere", "Ricerca per titolo dell'album", "Ricerca per artista titolare", "Ricerca per prezzo"};

	public SearchDialog() {
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setTitle("Ricerca");
		setModal(true);
		setBounds(100, 100, 450, 168);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[144px,grow][294px]", "[24px][39px,grow]"));
		{
			comboBox = new JComboBox<>();
			comboBox.setModel(new DefaultComboBoxModel<String>(opzioni));
			comboBox.addItemListener(this);
			contentPanel.add(comboBox, "cell 0 0 2 1,growx,aligny top");
		}
		{
			// creo componenti
			genere = new JComboBox<>(Genere.values());
			titolo = new JTextField();
			autore = new JTextField();
			NumberFormat format = NumberFormat.getNumberInstance();
			prezzoMin = new JFormattedTextField(new NumberFormatter(format));
			prezzoMax = new JFormattedTextField(new NumberFormatter(format));
			
			// creo pannello prezzo
			JPanel pricePanel = new JPanel(new MigLayout("", "[][grow]", "[][]"));
			pricePanel.add(new JLabel("Prezzo min:"), "cell 0 0 1 1");
			pricePanel.add(prezzoMin,                 "cell 1 0 1 1,growx");
			pricePanel.add(new JLabel("Prezzo max:"), "cell 0 1 1 1");
			pricePanel.add(prezzoMax,                 "cell 1 1 1 1,growx");
			
			// creo pannello grande
			cardPanel = new JPanel();
			cardPanel.setLayout(new CardLayout(0, 0));
			cardPanel.add(genere, opzioni[0]);
			cardPanel.add(titolo, opzioni[1]);
			cardPanel.add(autore, opzioni[2]);
			cardPanel.add(pricePanel, opzioni[3]);
			contentPanel.add(cardPanel, "cell 0 1 2 1,grow");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Avvia ricerca");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		System.out.println(arg0.getItem());
		String item = (String)arg0.getItem();
		CardLayout layout = (CardLayout)cardPanel.getLayout();
		layout.show(cardPanel, item);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		annullato = false;
		setVisible(false);
	}
	
	public boolean isAnnullato() {
		return annullato;
	}
	
	public Filter<Disco> getFiltro() {
		int i = comboBox.getSelectedIndex();
		
		switch(i) {
		case 0: return new GenreFilter((Genere)genere.getSelectedItem());
		case 1: return new TitleFilter(titolo.getText());
		case 2: return new AuthorFilter(autore.getText());
		case 3:
			String pMin = prezzoMin.getText().replaceAll("\\.", "").replaceAll(",", ".");
			if(pMin.isEmpty()) pMin = "0";
			String pMax = prezzoMax.getText().replaceAll("\\.", "").replaceAll(",", ".");
			if(pMax.isEmpty()) pMax = "1000000";
			return new PriceFilter(pMin, pMax);
		default:
			throw new AssertionError("Opzione impossibile");
		}
	}

}
