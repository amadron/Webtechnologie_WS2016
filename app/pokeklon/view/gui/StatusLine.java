package pokeklon.view.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import pokeklon.controller.IPokeklonController;

public class StatusLine extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public StatusLine(IPokeklonController controller) {
		JTextArea statusLine;
		statusLine = new JTextArea();
		statusLine.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		statusLine.setText(controller.getStatusLine());
		statusLine.setEditable(false);
		statusLine.setSize(AbsoluteTermsGUI.STATAUS_LINE);
		setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		this.add(statusLine);
	}
}
