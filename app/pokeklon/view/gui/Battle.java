package pokeklon.view.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.apache.log4j.Logger;

import pokeklon.controller.IMonsterFactory;
import pokeklon.controller.IPokeklonController;
import pokeklon.controller.impl.MonsterFactory;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import pokeklon.view.gui.helper.CloseMenu;
import pokeklon.view.gui.helper.MonsterChooserPanel;

public class Battle extends JPanel implements ItemListener {
	
	private static final int FIVE = 5;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int THREE_HOUNDRED_FIFTY = 350;
	private static final int TWO_HUNDRED_FIFTY = 250;
	private static final long serialVersionUID = 1L;
	private IPokeklonController controller;
	private JPanel fightPane;
	private JPanel btnPane;
	private IMonsterChooserPanel cp;
	private static Logger logger;
	
	public Battle(IPokeklonController controller) {
		this.controller = controller;
		btnPane = new JPanel();
		fightPane = new JPanel();
		Logger.getLogger(Battle.class);
		addPanels();
	}

	private void addPanels() {
		removeAll();
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		fightPane.setSize(AbsoluteTermsGUI.BIG_PANE);
		add(fightPane);
		btnPane.setSize(AbsoluteTermsGUI.SMALL_PANE);
		add(btnPane);
		updateUI();
		
	}

	private JPanel createMonsterShow() {
		IMonster monsterP1;
		IMonster monsterP2;
		IMonsterFactory factory = new MonsterFactory();
		JPanel fight = new JPanel();
		fight.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		fight.setLayout(new BorderLayout());
		JPanel progress = new JPanel(new FlowLayout(FlowLayout.CENTER));
		progress.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		JPanel image = new JPanel(new FlowLayout(FlowLayout.CENTER));
		image.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		monsterP1 = controller.getCurrentP1Mon();
		monsterP2 = controller.getCurrentP2Mon();
		buildProgressBars(monsterP1, monsterP2, factory, fight, progress);
		buildMonsterImagaePane(monsterP1, monsterP2, factory, image);
		fight.add(image, BorderLayout.CENTER);
		return fight;
		
	}

	private void buildProgressBars(IMonster monsterP1, IMonster monsterP2,
			IMonsterFactory factory, JPanel fight, JPanel progress) {
		JProgressBar barP1;
		JProgressBar barP2;
		JTextArea lifeP1 = new JTextArea();
		lifeP1.setEditable(false);
		lifeP1.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		lifeP1.setText("(" + monsterP1.getMaxLife() + " / " + monsterP1.getLife() + ")");
		JTextArea lifeP2 = new JTextArea();
		lifeP2.setEditable(false);
		lifeP2.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		lifeP2.setText("(" + monsterP2.getMaxLife() + " / " + monsterP2.getLife() + ")");
		barP1 = new JProgressBar(JProgressBar.HORIZONTAL, 0, (int) monsterP1.getMaxLife());
		barP1.setValue((int) monsterP1.getLife());
		barP2 = new JProgressBar(JProgressBar.HORIZONTAL, 0, (int) monsterP2.getMaxLife());
		barP2.setValue((int) monsterP2.getLife());
		progress.setSize(AbsoluteTermsGUI.J_PROGRESS_BAR_SIZE);
		
		progress.add(lifeP1, BorderLayout.WEST);
		progress.add(barP1, BorderLayout.WEST);
		progress.add(barP2, BorderLayout.EAST);
		progress.add(lifeP2, BorderLayout.EAST);
		factory.getMonsterNumber(monsterP1.getName());
		factory.getMonsterNumber(monsterP2.getName());
		fight.add(progress, BorderLayout.NORTH);
	}

	private void buildMonsterImagaePane(IMonster monsterP1, IMonster monsterP2,
			IMonsterFactory factory, JPanel image) {
		BufferedImage imageP1 = null;
		BufferedImage imageP2 = null;
		File imagePathP1 = new File(factory.getImagePath(monsterP1, "_big"));
		File imagePathP2 = new File(factory.getImagePath(monsterP2, "_big"));
		try {
			imageP1 = ImageIO.read(imagePathP1);
			imageP2 = ImageIO.read(imagePathP2);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		JLabel lblP1 = new JLabel(new ImageIcon(imageP1));
		lblP1.setSize(TWO_HUNDRED_FIFTY, THREE_HOUNDRED_FIFTY);
		JLabel lblP2 = new JLabel(new ImageIcon(imageP2));
		lblP2.setSize(TWO_HUNDRED_FIFTY, THREE_HOUNDRED_FIFTY);
		LineBorder border = new LineBorder(Color.BLACK, THREE);
		setActiveBorder(monsterP1, lblP1, lblP2, border);
		
		image.add(lblP1);
		image.add(lblP2);
	}

	private void setActiveBorder(IMonster monsterP1, JLabel lblP1,
			JLabel lblP2, LineBorder border) {
		if(controller.getCurrentMonster().equals(monsterP1)){
			lblP1.setBorder(new TitledBorder(border,"active"));
		} else {
			lblP2.setBorder(new TitledBorder(border,"active"));
		}
	}

	private JPanel createBattleChoice() {
		JPanel button = new JPanel();
		JButton btnAttack;
		JButton btnChangeMonster;
		JButton btnItem;
		JButton btnExit;
		btnAttack = new JButton("attack");
		btnAttack.setSize(AbsoluteTermsGUI.BUTTON_SIZE);
		btnAttack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.attackMenu();
				
			}
		});
		
		btnChangeMonster = new JButton("change Monster");
		btnChangeMonster.setSize(AbsoluteTermsGUI.BUTTON_SIZE);
		btnChangeMonster.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeMonster();
				
			}
		});
		
		btnItem = new JButton("use item");
		btnItem.setSize(AbsoluteTermsGUI.BUTTON_SIZE);
		btnItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getItemButtons();
				
			}
		});
		
		btnExit = new JButton("exit");
		btnExit.setSize(AbsoluteTermsGUI.BUTTON_SIZE);
		btnExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addPanels();
				new CloseMenu(fightPane);
				
			}
		});
		button.setLayout(new GridLayout(TWO, TWO, FIVE, FIVE));
		button.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		button.add(btnAttack);
		button.add(btnChangeMonster);
		button.add(btnItem);
		button.add(btnExit);
		return button;
	}

	private void getItemButtons() {
		List<IItem> itemList = controller.getItemList();
		fightPane = new JPanel();
		fightPane.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		for (final IItem iItem : itemList) {
			JButton item = new JButton(iItem.getName());
			item.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.useItem(iItem);
					getFightPane();
				}
			});
			fightPane.add(item);
		}
		btnPane = new JPanel();
		btnPane.setSize(AbsoluteTermsGUI.SMALL_PANE);
		btnPane.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		btnPane.add(addCancelButton());
		addPanels();
	}

	private void getAttackButtons() {
		List<IAttack> attacks = controller.getCurrentAttackList();
		btnPane = new JPanel(new GridLayout(TWO, TWO, FIVE, FIVE));
		btnPane.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		JPanel attackPane = new JPanel(new GridLayout(TWO, TWO, FIVE, FIVE));
		attackPane.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		for (final IAttack iAttack : attacks) {
			JButton attack = new JButton(iAttack.getName());
			attack.setSize(AbsoluteTermsGUI.BUTTON_SIZE);
			attack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					controller.attack(iAttack);
					addPanels();
				}
			});
			attackPane.add(attack);
		}
		btnPane.add(attackPane, BorderLayout.NORTH);
		JPanel cancelPane = addCancelButton();
		btnPane.add(cancelPane, BorderLayout.NORTH);
		addPanels();
	}

	private JPanel addCancelButton() {
		JPanel cancelPane = new JPanel();
		cancelPane.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		cancelPane.setSize(AbsoluteTermsGUI.WINDOW_WIDTH/TWO, AbsoluteTermsGUI.BUTTON_HEIGHT);
		JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.battleMenu();
			}
		});
		cancelPane.add(cancel);
		return cancelPane;
	}
	
	private void changeMonster(){
		cp = new MonsterChooserPanel(controller);
		List<IMonster> currMonster = controller.getCurrentPlayerMonsterList();
		fightPane = cp.getPanelBuilt(currMonster);
		for (Entry<IMonster, JCheckBox> check : cp.getJCheckBoxMonsterMap().entrySet()) {
			JCheckBox tmp = check.getValue();
			tmp.addItemListener(this);
		}
		btnPane = addCancelButton();
		addPanels();
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		ie.getItemSelectable();
		Map<IMonster, JCheckBox> map = cp.getJCheckBoxMonsterMap();
		for (Entry<IMonster, JCheckBox> check : map.entrySet()) {
			if(ie.getItemSelectable().equals(check.getValue())){
				controller.changeMonster(check.getKey());
				getFightPane();
			}
		}
		
	}
	
	public void getFightPane(){
		fightPane = createMonsterShow();
		btnPane = createBattleChoice();
		addPanels();
	}
	
	public void showAttackButtons(){
		fightPane = createMonsterShow();
		getAttackButtons();
		addPanels();
	}
	
	public void showItemChange(){
		getItemButtons();
		addPanels();
	}
	
	public void showMonsterChange(){
		changeMonster();
		addPanels();
	}

}
