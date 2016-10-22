package pokeklon.view.tui;

import pokeklon.controller.IPokeklonController;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import util.importData.GetMonsterAndAttackList;
import util.observer.Event;
import util.observer.IObserver;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

public class TextUI implements IObserver{

	private static final int TWO = 2;
	private static final int FOUR = 4;
	private IPokeklonController controller;
	private Logger logger;
	private String battle = "battle";
	private String stat;
	private String tmpStatus = "";
	private String output;
	private String gameStat;
	
	@Inject
	public TextUI(IPokeklonController controller) {
		this.controller = controller;
		controller.addObserver(this);
		this.logger = Logger.getLogger(TextUI.class);
	}
	
	@Override
	public void update(Event e) {
		printTUI();
		
	}
	
	public void monsterAdd(String line){
		String[] monCh = line.split(" ");
		int noOfMonster = controller.getNoOfMonster();
		if(monCh.length == noOfMonster){
			int maxMonster = GetMonsterAndAttackList.getMonsterList().size();
			int[] choiceList = new int[noOfMonster];
			int counter = 0;
			for(String str: monCh){
				if(Integer.valueOf(str) > 0 || Integer.valueOf(str) <= maxMonster){
					choiceList[counter] = Integer.valueOf(str) - 1;
					counter++;
				} else {
					throw new IllegalArgumentException("Eingabe" +  counter + "keine gültige Monster Nummer");
				}
			}
			controller.addMonster(choiceList);
		}
	}
	
	public boolean processInputLine(String line){
		boolean exit = false;
		stat = controller.getGameStat();
		menuInput(line);
		choiceMenu(line);
		changeMonsterMenu(line);
		attackMenu(line);
		itemMenu(line);
		battleMenu(line);
		endMenu();

		return exit;
	}

	private void endMenu() {
		if(stat.equals("end")){
			System.exit(0);
		}
	}

	private void battleMenu(String line) {
		if(stat.equals("battle")){
			openAttackMenu(line);
			openMonsterChangeMenu(line);
			openUseItemMenu(line);
			exitGame(line);
		}
	}

	private void exitGame(String line) {
		if(line.equals("4")){
			System.exit(0);
		}
	}

	private void openUseItemMenu(String line) {
		if(line.equals("3")){
			controller.itemMenu();
		}
	}

	private void openMonsterChangeMenu(String line) {
		if(line.equals("2")){
			controller.changeMonsterMenu();
		}
	}

	private void openAttackMenu(String line) {
		if(line.equals("1")){
			controller.attackMenu();
		}
	}

	private void itemMenu(String line) {
		if(stat.equalsIgnoreCase("ItemCh")){
			if(line.equalsIgnoreCase("0")){
				backToMainBattle(line);
			}
			useItem(line);
		}
	}

	private void attackMenu(String line) {
		if(stat.equalsIgnoreCase("attack")){
			if(line.equalsIgnoreCase("0")){
				backToMainBattle(line);
			}
			useAttack(line);
		}
	}

	private void changeMonsterMenu(String line) {
		if(stat.equalsIgnoreCase("changeMon")){
			if(line.equalsIgnoreCase("0")){
				backToMainBattle(line);
			}
			changeMonster(line);
		}
	}

	private void choiceMenu(String line) {
		if(stat.equalsIgnoreCase("monP1") || stat.equalsIgnoreCase("monP2")){
			monsterAdd(line);
		}
	}

	private void menuInput(String line) {
		if(stat.equals("menu")){
			createQuickGame(line);
			create2PlayerGame(line);
			create4PlayerGame(line);
			exitGame(line);
		}
	}

	private void create4PlayerGame(String line) {
		if(line.equalsIgnoreCase("3")){
			int i = FOUR;
			controller.newGame(i);
		}
	}

	private void create2PlayerGame(String line) {
		if(line.equalsIgnoreCase("2")){
			int i = TWO;
			controller.newGame(i);
		}
	}

	private void createQuickGame(String line) {
		if(line.equalsIgnoreCase("1")){
			controller.quickGame();
		}
	}

	private void backToMainBattle(String line) {
		if(line.equalsIgnoreCase("0")){
			controller.battleMenu();
		}
	}


	private void useItem(String line) {
		IItem tmp = null;
		for(IItem item : controller.getCurrentPlayer().getItemList()){
			if(item.getName().equals(line)){
				controller.useItem(item);
				tmp = item;
				break;
			}
		}
		String display = tmp.getName() + " used. ";
		output = display;
	}

	private void useAttack(String line) {
		for(IAttack attack : controller.getCurrentMonster().getAttackList()){
			if(attack.getName().equals(line)) {
				output = null;
				controller.attack(attack);
				break;
			}
		}
	}

	private void changeMonster(String line) {
		for(IMonster mon : controller.getCurrentPlayer().getMonsterList()){
			if(mon.getName().equals(line)) {
				output = null;
				controller.changeMonster(mon);
				break;
			}
		}
	}

	private void battleMenu() {
		String menu = "Battle: \n";
		menu += "Player1 Monster: " + controller.getCurrentP1Mon().getName() + "\tLife: " 
				+ controller.getCurrentP1Mon().getLife() + "\n";
		menu += "Player2 Monster: " + controller.getCurrentP2Mon().getName() + "\tLife: "
				+ controller.getCurrentP2Mon().getLife() + "\n";
		menu += ">>Player";
			menu += " " + controller.getPlayerNumber(controller.getCurrentPlayer()) + " ";
		menu += " is on Turn!<<";
		menu += "Please choose: \n";
		menu += "(1) Attack \t (2) Change Monster \n";
		menu += "(3) Item \t(4) Exit\n";
		output = menu;
	}

	private void attackMenu() {
		String menu = "Attack: \nName \tType \n";
		for(IAttack att : controller.getCurrentMonster().getAttackList()){
			menu += att.getName() + "\t" + att.getType().getType() +  "\n";
		}
		menu += "(0) Back to Battle Menu";
		output = menu;
	}

	private void restartGame() {
		if(stat.equals("end")){
			startMainMenu();
			
		}
	}

	private void printFullMonsterList() {
		output = "Monsterlist:\nNo: \tName \tType \tAttack \tDefence \n";
		List<List<String>> monsterList = GetMonsterAndAttackList.getMonsterList();
		int counter =  1;
		for(List<String> list : monsterList){
			output += counter + "\t";
			List<String> tmp = list.subList(0, FOUR);
				for(String str : tmp){
					output += str + "\t";
				}
				output += "\n";
			counter++;
		}
	}

	private void changeMonsterMenu() {
		if(stat.equals(battle)) {
			String menu = "Change Monster: \n";
			menu += "Current Monster ";
			menu += controller.getCurrentMonster().getName();
			menu += " \n";
			menu += printMonsterList(controller.getPlayerMonsterWithoutCurrent());
			menu += "(0) Back to Battle \n";
			output = menu;
		}
	}

	private String printMonsterList(List<IMonster> mL) {
		StringBuilder ret = new StringBuilder();
		for (IMonster mon : mL) {
			ret.append(mon.getName() + "\t"
					+ mon.getLife() + "\t" + mon.getAttack() + "\t" +mon.getDefence() + "\n");
		}
		return ret.toString();
	}

	private void itemMenu() {
		String menu = "Items: \n";
		for(IItem item :  controller.getCurrentPlayer().getItemList()){
			menu += item.getName() + "\n";
		}
		menu += "(0) Back to Battle Menu\n";
		output = menu;
	}

	private void startMainMenu() {
		if(stat.equals(battle)){
			controller.getMain();
		}
	}

	private void getMain() {
		String menu = "Main Menu: \n"
				+ "(1) Quick Game \n"
				+ "(2) Two Monster fight \n"
				+ "(3) Four Monster fight \n"
				+ "(4) Exit";
		output = menu;
	}

	public void printTUI() {
		gameStat = controller.getGameStat();
		checkMenu();
		checkBattle();
		checkChoise();
		checkAttack();
		checkItemChoose();
		checkChangeMon();
		checkEnd();
		if(!controller.getStatusLine().equals(tmpStatus)){
			tmpStatus = controller.getStatusLine();
			logger.info(controller.getStatusLine());
			if(output != null) {
				logger.info(output);
			}
		}
	}

	private void checkEnd() {
		if(gameStat.equals("end")){
			output = "Battle is over: \n (1) Restart \n (2) Exit \n";
			restartGame();
		}
	}

	private void checkChangeMon() {
		if(gameStat.equals("changeMon")){
			changeMonsterMenu();
		}
	}

	private void checkItemChoose() {
		if(gameStat.equals("itemCh")){
			itemMenu();
		}
	}

	private void checkAttack() {
		if(gameStat.equals("attack")){
			attackMenu();
		}
	}

	private void checkChoise() {
		if(gameStat.equals("monP1") || gameStat.equals("monP2")){
			printFullMonsterList();
		}
		
	}

	private void checkBattle() {
		if(gameStat.equals("battle")){
			battleMenu();
		}
	}

	private void checkMenu() {
		if(gameStat.equals("menu")){
			getMain();
		}
	}

}
