package pokeklon.view.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.google.inject.Inject;

import pokeklon.controller.IPokeklonController;
import pokeklon.view.gui.helper.CloseMenu;
import util.observer.Event;
import util.observer.IObserver;

public class PokeklonFrame extends JFrame implements IObserver {

	private static final long serialVersionUID = 2157096527833335841L;
	private IPokeklonController controller;
	private Container pane;
	private Container current;
	private StatusLine statusPane;
	private Battle battle;
	private ChoiceMenu change;
	@Inject
	public PokeklonFrame(final IPokeklonController controller) {
		this.controller = controller;
		controller.addObserver(this);
		battle = new Battle(controller);
		change  = new ChoiceMenu(controller);
		
		setTitle("Pokeklon");
		setSize(AbsoluteTermsGUI.WINDOW_SIZE);
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	new CloseMenu(current);
            }
    });
		
		setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		pane = getContentPane();
		pane.setLayout(new BorderLayout());
		constructPane(controller);
	}
	
	private void constructPane(IPokeklonController controller){
		if(current != null){
			pane.remove(current);
		}
		current = new MainMenu(controller);
		pane.add(current, BorderLayout.CENTER);
		if(statusPane != null){
			pane.remove(statusPane);
		}
		statusPane = new StatusLine(controller);
		pane.add(statusPane,BorderLayout.SOUTH);
		
		setVisible(true);
		repaint();
	}
	
	@Override
	public void update(Event e) {
		String stat = controller.getGameStat();
		switch (stat) {
		case "menu":
			current = new MainMenu(controller);
			rebuildWindow();
			break;
		case "monP1":
		case "monP2":
			change = new ChoiceMenu(controller);
			change.getMonsterPanel();
			current = change;
			rebuildWindow();
			break;
		case "battle":
			battle = new Battle(controller);
			battle.getFightPane();
			current = battle;
			rebuildWindow();
			break;
		case "attack":
			battle = new Battle(controller);
			battle.showAttackButtons();
			current = battle;
			rebuildWindow();
			break;
		case "itemCh":
			battle = new Battle(controller);
			battle.showItemChange();
			current = battle;
			rebuildWindow();
			break;
		case "changeMon":
			battle = new Battle(controller);
			battle.showMonsterChange();
			current = battle;
			rebuildWindow();
			break;
		case "end":
			battle.getFightPane();
			current = battle;
			rebuildWindow();
			new ExitScreen(controller);
			break;

		default:
			break;
		}
		
	}

	private void rebuildWindow() {
		pane.removeAll();
		pane.add(current, BorderLayout.CENTER);
		statusPane = new StatusLine(controller);
		pane.add(statusPane, BorderLayout.SOUTH);
		setVisible(true);
		repaint();
	}


}
