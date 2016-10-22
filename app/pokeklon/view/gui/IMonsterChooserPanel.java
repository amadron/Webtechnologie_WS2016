package pokeklon.view.gui;

import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import pokeklon.model.IMonster;

public interface IMonsterChooserPanel {
	
	JPanel getPanelBuilt(List<IMonster> ml);

	Map<IMonster, JCheckBox> getJCheckBoxMonsterMap();

}