package incud.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle.Control;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import incud.immutable.Cliente;
import incud.stato.RegistroClienti;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class RegisterDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNickname;
	private JTextField txtNome;
	private JTextField txtCognome;
	private JTextField txtCodicefiscale;
	private JTextField txtPassword;
	private JTextField txtCitta;
	private JTextField txtTelefono;
	private JTextField txtTelefonocellulare;
	
	private boolean annullato = true;

	public RegisterDialog() {
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setTitle("Effettua registrazione");
		setModal(true);
		setBounds(100, 100, 452, 276);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][][][][][][][]"));
		{
			JLabel nickLabel = new JLabel("Nickname:");
			contentPanel.add(nickLabel, "cell 0 0,alignx trailing");
		}
		{
			txtNickname = new JTextField();
			txtNickname.setToolTipText("");
			contentPanel.add(txtNickname, "cell 1 0,growx");
			txtNickname.setColumns(10);
		}
		{
			JLabel lblNome = new JLabel("Nome:");
			contentPanel.add(lblNome, "cell 0 1,alignx trailing");
		}
		{
			txtNome = new JTextField();
			txtNome.setText("");
			contentPanel.add(txtNome, "cell 1 1,growx");
			txtNome.setColumns(10);
		}
		{
			JLabel lblCognome = new JLabel("Cognome:");
			contentPanel.add(lblCognome, "cell 0 2,alignx trailing");
		}
		{
			txtCognome = new JTextField();
			contentPanel.add(txtCognome, "cell 1 2,growx");
			txtCognome.setColumns(10);
		}
		{
			JLabel lblCodiceFiscale = new JLabel("Codice fiscale:");
			contentPanel.add(lblCodiceFiscale, "cell 0 3,alignx trailing");
		}
		{
			txtCodicefiscale = new JTextField();
			contentPanel.add(txtCodicefiscale, "cell 1 3,growx");
			txtCodicefiscale.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			contentPanel.add(lblPassword, "cell 0 4,alignx trailing");
		}
		{
			txtPassword = new JTextField();
			contentPanel.add(txtPassword, "cell 1 4,growx");
			txtPassword.setColumns(10);
		}
		{
			JLabel lblCitt = new JLabel("Città:");
			contentPanel.add(lblCitt, "cell 0 5,alignx trailing");
		}
		{
			txtCitta = new JTextField();
			contentPanel.add(txtCitta, "cell 1 5,growx");
			txtCitta.setColumns(10);
		}
		{
			JLabel lblTelefono = new JLabel("Telefono:");
			contentPanel.add(lblTelefono, "cell 0 6,alignx trailing");
		}
		{
			txtTelefono = new JTextField();
			contentPanel.add(txtTelefono, "cell 1 6,growx");
			txtTelefono.setColumns(10);
		}
		{
			JLabel lblTelefonoCellulare = new JLabel("Cellulare:");
			contentPanel.add(lblTelefonoCellulare, "cell 0 7,alignx trailing");
		}
		{
			txtTelefonocellulare = new JTextField();
			contentPanel.add(txtTelefonocellulare, "cell 1 7,growx");
			txtTelefonocellulare.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registra");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public boolean isAnnullato() {
		return annullato;
	}

	public Cliente getCliente() {
		return Cliente.newBuilder()
				.addNome(txtNome.getText())
				.addCognome(txtCognome.getText())
				.addCodiceFiscale(txtCodicefiscale.getText())
				.addNickname(txtNickname.getText())
				.addPassword(txtPassword.getText())
				.addCitta(txtCitta.getText())
				.addTelefono(txtTelefono.getText())
				.addCellulare(txtTelefonocellulare.getText())
				.build();
	}

	private String getCampiVuoti() {
		String[] dati = { txtNome.getText(), txtCognome.getText(), txtCodicefiscale.getText(), txtNickname.getText(), txtPassword.getText(), txtCitta.getText(), txtTelefono.getText()};
		String[] campi = { "Nome", "Cognome", "Codice fiscale", "Nickname", "Password", "Città", "Telefono"};
		String vuoti = "", prefisso = "";
		for(int i = 0; i < dati.length; i++) {
			if(dati[i].isEmpty()) {
				vuoti += prefisso + campi[i];
				prefisso = ", ";
			}
		}
		return vuoti;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String campi = getCampiVuoti();
		if(!campi.isEmpty()) {
			MessageDialog message = new MessageDialog("I seguenti campi contengono dati non validi: " + campi);
			message.show();
			return;
		}
		
		if(RegistroClienti.instance().doesNicknameExists(txtNickname.getText())) {
			MessageDialog message = new MessageDialog("Il nickname scelto esiste già");
			message.show();
			return;
		}
		
		annullato = false; 
		setVisible(false);
	}
}
