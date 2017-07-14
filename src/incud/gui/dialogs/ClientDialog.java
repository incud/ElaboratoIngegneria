package incud.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import incud.immutable.Cliente;
import incud.stato.RegistroClienti;
import incud.stato.StatoUtente;
import incud.stato.StatoUtente.Stato;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.Box;

public class ClientDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private final JLabel lblClNick, lblClNome, lblClCognome, lblClCitta, lblClTelefono, lblClCellulare, lblClCf;

	public ClientDialog() {
		setTitle("Informazioni cliente");
		setBounds(100, 100, 326, 275);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow][100px:100px:100px][150px:150px:150px][grow]", "[grow][][][][][][][][grow]"));
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			contentPanel.add(verticalStrut, "cell 1 0");
		}
		{
			JLabel lblNickname = new JLabel("Nickname:");
			contentPanel.add(lblNickname, "cell 1 1");
		}
		{
			lblClNick = new JLabel("cl nick");
			contentPanel.add(lblClNick, "cell 2 1");
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut, "cell 0 2");
		}
		{
			JLabel lblNome = new JLabel("Nome:");
			contentPanel.add(lblNome, "cell 1 2");
		}
		{
			lblClNome = new JLabel("cl nome");
			contentPanel.add(lblClNome, "cell 2 2");
		}
		{
			Component horizontalStrut = Box.createHorizontalStrut(20);
			contentPanel.add(horizontalStrut, "cell 3 2");
		}
		{
			JLabel lblCognome = new JLabel("Cognome:");
			contentPanel.add(lblCognome, "cell 1 3");
		}
		{
			lblClCognome = new JLabel("cl cognome");
			contentPanel.add(lblClCognome, "cell 2 3");
		}
		{
			JLabel lblCodiceFiscale = new JLabel("Codice fiscale:");
			contentPanel.add(lblCodiceFiscale, "cell 1 4");
		}
		{
			lblClCf = new JLabel("cl cf");
			contentPanel.add(lblClCf, "cell 2 4");
		}
		{
			JLabel lblCitt = new JLabel("Città:");
			contentPanel.add(lblCitt, "cell 1 5");
		}
		{
			lblClCitta = new JLabel("cl citta");
			contentPanel.add(lblClCitta, "cell 2 5");
		}
		{
			JLabel lblTelefono = new JLabel("Telefono:");
			contentPanel.add(lblTelefono, "cell 1 6");
		}
		{
			lblClTelefono = new JLabel("cl telefono");
			contentPanel.add(lblClTelefono, "cell 2 6");
		}
		{
			JLabel lblCellulare = new JLabel("Cellulare:");
			contentPanel.add(lblCellulare, "cell 1 7");
		}
		{
			lblClCellulare = new JLabel("cl cellulare");
			contentPanel.add(lblClCellulare, "cell 2 7");
		}
		{
			Component verticalStrut = Box.createVerticalStrut(20);
			contentPanel.add(verticalStrut, "cell 1 8");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Esci");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
		
		caricaCliente();
	}
	
	private void caricaCliente() {
		
		if(StatoUtente.instance().getStato() != Stato.CLIENTE) {
			throw new AssertionError("Questa classe dovrebbe essere usata solamente se si è loggati");
		}
		
		Cliente cliente = StatoUtente.instance().getClienteLoggato();
		lblClNick.setText(cliente.nickname);
		lblClNome.setText(cliente.nome);
		lblClCognome.setText(cliente.cognome);
		lblClCf.setText(cliente.codiceFiscale);
		lblClCitta.setText(cliente.citta);
		lblClTelefono.setText(cliente.telefono);
		lblClCellulare.setText(cliente.cellulare);
		
	}

}
