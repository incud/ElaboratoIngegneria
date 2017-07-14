package incud.gui.dialogs;

import javax.swing.JOptionPane;

public class MessageDialog {

	private final boolean isQuestion;
	private final String message;
	private final String[] options;
	
	public MessageDialog(String question, String option1, String option2) {
		this.message = question;
		this.options = new String[] {option1, option2};
		this.isQuestion = true;
	}
	
	public MessageDialog(String message) {
		this.message = message;
		this.options = null;
		this.isQuestion = false;
	}
	
	private boolean showQuestion() {
		int n = JOptionPane.showOptionDialog(
				null, 
				message, 
				"Rispondi alla domanda", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, 
				options, 
				options[0]);
		return n == 0;
	}
	
	private boolean showMessage() {
		JOptionPane.showMessageDialog(null, message);
		return true;
	}
	
	public boolean show() {
		if(isQuestion) 
			return showQuestion();
		else
			return showMessage();
	}
}
