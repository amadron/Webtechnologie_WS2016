package pokeklon.view.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import pokeklon.controller.IPokeklonController;

public class MainMenu extends JPanel{

	private static final long serialVersionUID = 1L;
	private Logger logger;
	public MainMenu(final IPokeklonController controller){
		Logger.getLogger(MainMenu.class);
		JLabel logoLabel;
		BufferedImage logoPic = null;
		JPanel button = new JPanel();
		JButton quickGame, monster2, monster4, exit;
		
		/*
		 * insert logo
		 */
		try {
			logoPic = ImageIO.read(AbsoluteTermsGUI.LOGO_PATH);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logoLabel = new JLabel(new ImageIcon(logoPic));
		/*
		 * create buttons
		 */
		quickGame = new JButton("Quick Game (1 vs. 1)");
		quickGame.setMaximumSize(AbsoluteTermsGUI.BUTTON_SIZE);
		quickGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.quickGame();
				
			}
		});
		
		monster2 = new JButton("New Game (2 vs. 2)");
		monster2.setMaximumSize(AbsoluteTermsGUI.BUTTON_SIZE);
		monster2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.newGame(AbsoluteTermsGUI.TWO);
				
			}
		});
		
		monster4 = new JButton("New Game (4 vs. 4)");
		monster4.setMaximumSize(AbsoluteTermsGUI.BUTTON_SIZE);
		monster4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.newGame(AbsoluteTermsGUI.FOUR);
				
			}
		});
		
		
		exit = new JButton("Exit");
		exit.setMaximumSize(AbsoluteTermsGUI.BUTTON_SIZE);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null,
						"Are you sure, that you want to exit?",
						"Abort",
						JOptionPane.YES_NO_OPTION);
				switch(result) {
				case JOptionPane.YES_OPTION:
					System.exit(0);
				}
				
			}
		});
		
		button.setLayout(new BoxLayout(button, BoxLayout.Y_AXIS));
			
		
		//Adding Buttons to Frame
		button.add(quickGame);
		button.add(monster2);
		button.add(monster4);
		button.add(exit);
		
		this.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(logoLabel);
		this.add(button);
	}

}
