package pokeklon.view.gui;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import pokeklon.controller.IPokeklonController;
import pokeklon.controller.impl.MonsterFactory;
import pokeklon.model.IMonster;
import pokeklon.view.gui.helper.MonsterChooserPanel;

public class ChoiceMenu extends JPanel implements ItemListener{
	
	private static final long serialVersionUID = 1L;
	private MonsterFactory factory = new MonsterFactory();
	private int numberOfMonster;
	private Map<IMonster, JCheckBox> nameCheck = new HashMap<IMonster, JCheckBox>();
	private IPokeklonController controller;
	private JPanel pane;
	
	public ChoiceMenu(IPokeklonController controller)  {
		this.controller = controller;
		pane = new JPanel();
		pane.setLayout(new BorderLayout());
		setBackground(AbsoluteTermsGUI.BACKGROUND_COLOR);
	}

	public void getMonsterPanel(){
		IMonsterChooserPanel cp = new MonsterChooserPanel(controller);
		List<IMonster> ml = controller.getMonsterListFull();
		pane = cp.getPanelBuilt(ml);
		nameCheck = cp.getJCheckBoxMonsterMap();
		for (Entry<IMonster, JCheckBox> check : nameCheck.entrySet()) {
			JCheckBox tmp = check.getValue();
			tmp.addItemListener(this);
		}
		add(pane);
		

	}

	@Override
	public void itemStateChanged(ItemEvent item) {
		List<String> checked = new ArrayList<String>();
		getSelectedMonsters(checked);
		numberOfMonster = controller.getNoOfMonster();
		numberOfMonstersChecked(checked);
		
	}

	private void numberOfMonstersChecked(List<String> checked) {
		if(checked.size() == numberOfMonster){
			int[] numberList = getMonsterNumberList(checked);
			controller.addMonster(numberList);
		}
	}

	private void getSelectedMonsters(List<String> checked) {
		for (Entry<IMonster, JCheckBox> tmp : nameCheck.entrySet()) {
			if(tmp.getValue().isSelected()){
				checked.add(tmp.getKey().getName());
			}
		}
	}

	private int[] getMonsterNumberList(List<String> checked) {
		int[] ret = new int[checked.size()];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = factory.getMonsterNumber(checked.get(i));
		}
		return ret;
	}

}
