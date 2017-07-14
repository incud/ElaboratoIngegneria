package incud.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import incud.immutable.Cliente;
import incud.stato.Inventario;
import incud.stato.RegistroClienti;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

public class LoginDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	
	private final JTextField nickField;
	
	private final JPasswordField passwordField;
	
	private boolean annullato = true;

	public LoginDialog() {
		setTitle("Effettua autenticazione");
		setModal(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setBounds(100, 100, 320, 157);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[77px][70px,grow]", "[15px][]"));
		{
			JLabel lblNickname = new JLabel("Nickname: ");
			contentPanel.add(lblNickname, "cell 0 0,alignx trailing,aligny top");
		}
		{
			nickField = new JTextField();
			contentPanel.add(nickField, "cell 1 0,growx");
			nickField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			contentPanel.add(lblPassword, "cell 0 1,alignx trailing,aligny top");
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField, "cell 1 1,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Login");
				okButton.addActionListener(this);
				okButton.setActionCommand("LOGIN");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	
	public boolean isAnnullato() {
		return annullato;
	}
	
	public String getNickname() {
		return nickField.getText();
	}
	
	private String getPassword() {
		return new String(passwordField.getPassword());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Cliente cliente = RegistroClienti.instance().findCliente(getNickname());
		if(cliente == null) {
			MessageDialog message = new MessageDialog("Non è stato trovato alcun cliente chiamato '" + getNickname() + "'");
			message.show();
			return;
		}
		
		if(!cliente.password.equals(getPassword())) {
			MessageDialog message = new MessageDialog("La password inserita non è corretta");
			message.show();
			return;
		}
		
		annullato = false;
		setVisible(false);
	}
}
