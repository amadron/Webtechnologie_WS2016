package pokeklon.view.gui.helper;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import pokeklon.controller.IPokeklonController;
import pokeklon.model.IMonster;
import pokeklon.view.gui.AbsoluteTermsGUI;
import pokeklon.view.gui.IMonsterChooserPanel;

public class MonsterChooserPanel implements IMonsterChooserPanel {
	
	private IPokeklonController controller;
	private Map<IMonster, JCheckBox> mapCheck;
	private Logger logger;

	public MonsterChooserPanel(IPokeklonController controller) {
		this.controller = controller;
		mapCheck = new HashMap<IMonster, JCheckBox>();
		Logger.getLogger(MonsterChooserPanel.class);
	}

	@Override
	public JPanel getPanelBuilt(List<IMonster> ml) {
		BufferedImage monImage = null;
		JPanel monPane;
		JLabel monLbl;
		File monFile;
		JCheckBox monCheck;
		
		monPane = new JPanel();
		monPane.setLayout(new GridLayout(AbsoluteTermsGUI.FOUR,AbsoluteTermsGUI.ONE_HUNDRED,AbsoluteTermsGUI.FIVE,AbsoluteTermsGUI.FIVE));
		monPane.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
		
		for (IMonster iMonster : ml) {
			JPanel tmp = new JPanel();
			tmp.setLayout(new BoxLayout(tmp, BoxLayout.Y_AXIS));
			tmp.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
			monFile = new File(controller.getImagePath(iMonster));
			
			try {
				monImage = ImageIO.read(monFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
			monLbl = new JLabel(new ImageIcon(monImage));
			monLbl.setToolTipText(getMonsterText(iMonster));
			monCheck = new JCheckBox();
			monCheck.setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
			mapCheck.put(iMonster, monCheck);
			tmp.add(monLbl);
			tmp.add(monCheck);
			monPane.add(tmp);
		}
		
		return monPane;
	}

	private String getMonsterText(IMonster mon) {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + mon.getName() + " ");
		sb.append("Type: " + mon.getType().getName());
		sb.append("Attack: " + mon.getAttack() + " ");
		sb.append("Defence: " + mon.getDefence() + " ");
		sb.append("Life: " + mon.getLife() + " ");
		return sb.toString();
	}
	
	@Override
	public Map<IMonster, JCheckBox> getJCheckBoxMonsterMap(){
		return mapCheck;
	}

}
