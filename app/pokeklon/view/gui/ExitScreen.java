package pokeklon.view.gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pokeklon.controller.IPokeklonController;

public class ExitScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private IPokeklonController controller;
	
	public ExitScreen(IPokeklonController controller){
		this.controller = controller;
		closeMenu();
	}
	
	private void closeMenu(){
		String options[] = {"restart", "exit"};
		int n = JOptionPane.showOptionDialog(
				this,
				"Congratulations\nPlayer " + controller.getWinner() + " is the winner!",
				"Game Over!",
				JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]);
		if (n == 0){
			controller.reset();
		} else {
			System.exit(0);
		}
	}

}
