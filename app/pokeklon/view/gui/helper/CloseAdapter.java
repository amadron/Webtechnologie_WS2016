package pokeklon.view.gui.helper;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class CloseAdapter extends WindowAdapter {
	
	private JFrame frame;
	private Container c;
	
	public CloseAdapter(JFrame frame, Container current) {
		this.frame = frame;
		this.c = current;
	}
	
	@Override
    public void windowClosing(WindowEvent e) {
    	//new CloseMenu(c, frame);
    }
}
