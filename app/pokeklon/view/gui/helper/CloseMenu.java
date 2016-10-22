package pokeklon.view.gui.helper;

import java.awt.Container;
import javax.swing.JOptionPane;

public class CloseMenu {
	
	public CloseMenu(Container c){
        int result = JOptionPane.showConfirmDialog(
            c,
            "Are you shure that you want to exit?",
            "exit application",
            JOptionPane.YES_NO_OPTION);

        if (result == JOptionPane.YES_OPTION){
            System.exit(0);
        }
	}

}
