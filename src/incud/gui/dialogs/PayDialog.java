package incud.gui.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import incud.immutable.Pagamento;
import incud.immutable.Consegna;

public class PayDialog extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	
	private final JComboBox<Pagamento> cmbPagamento;

	private final JComboBox<Consegna> cmbConsegna;
	
	private boolean annullato = true;
	
	public PayDialog() {
		
		setTitle("Paga");
		setBounds(100, 100, 450, 142);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		{
			JLabel lblPagamento = new JLabel("Pagamento:");
			contentPanel.add(lblPagamento, "cell 0 0,alignx trailing");
		}
		{
			cmbPagamento = new JComboBox<>();
			cmbPagamento.setModel(new DefaultComboBoxModel<Pagamento>(Pagamento.values()));
			contentPanel.add(cmbPagamento, "cell 1 0,growx");
		}
		{
			JLabel lblConsegna = new JLabel("Consegna: ");
			contentPanel.add(lblConsegna, "cell 0 1,alignx trailing");
		}
		{
			cmbConsegna = new JComboBox<>();
			cmbConsegna.setModel(new DefaultComboBoxModel<Consegna>(Consegna.values()));
			contentPanel.add(cmbConsegna, "cell 1 1,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnPaga = new JButton("Paga");
				btnPaga.addActionListener(this);
				buttonPane.add(btnPaga);
				
				getRootPane().setDefaultButton(btnPaga);
			}
		}
	}
	
	public boolean isAnnullato() {
		return annullato;
	}
	
	public Pagamento getPagamento() {
		return (Pagamento)cmbPagamento.getSelectedItem();
	}
	
	public Consegna getConsegna() {
		return (Consegna)cmbConsegna.getSelectedItem();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		annullato = false;
		setVisible(false);
	}
}
