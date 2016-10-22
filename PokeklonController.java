package pokeklon.controller.impl;

import java.util.List;

import pokeklon.controller.IMonsterFactory;
import pokeklon.controller.IPlayer;
import pokeklon.controller.IPokeklonController;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import pokeklon.util.importData.getMonsterAndAttackList;
import pokeklon.util.observer.Observable;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class PokeklonController extends Observable implements IPokeklonController {
	/* Gamestate for current gameposition:
	 * menu: main menu
	 * monP1: monster choosing player 1
	 * monP2: monster choosing player 2
	 * battle: the fight itself
	 * attack: attack choosing.
	 * itemCh: item choosing
	 * changeMon: pokemon changing
	 * end: battle ends
	 */
	String gameStat = null;
	private String output;
	private String statusLine;
	private IPlayer p1;
	private IMonster currMonP1;
	private IPlayer p2;
	private IMonster currMonP2;
	private final int WINDOW_WIDTH = 80;
	private IPlayer currentPlayer = null;
	private Action action = new Action();
	private int noOfMonster = 0;
	private IMonsterFactory factory;
	
	@Inject
	public PokeklonController() {
		statusLine= 
				createStatusLine("Pokeklon - Project for SoftwareEngineering AIN3");
	}


	@Override
	public void reset() {
		notifyObservers();

	}
	
	@Override
	public String getGamestat(){
		return gameStat;
	}
	
	@Override
	public String getOutput(){
		return output;
	}
	
	private String getMonsterList(){
		String menu = "Monsterlist:\nNo: \tName \tType \tAttack \tDefence \n";
		List<List<String>> monsterList = getMonsterAndAttackList.getMonsterList();
		int counter =  1;
		for(List<String> list : monsterList){
			menu += counter + "\t";
			List<String> tmp = list.subList(0, 4);
				for(String str : tmp){
					menu += str + "\t";
				}
			menu += "\n";
			counter++;
		}
		return menu;
	}
	
	@Override
	public void newGame(int i) {
		p1 = new Player();
		p2 = new Player();
		noOfMonster = i;
		currentPlayer = p1;
		gameStat = "monP1";
		output = getMonsterList();
		notifyObservers();
		callMonsterChoice();
	}
	
	private void callMonsterChoice(){
		int curr = 0;
		if(currentPlayer.equals(p1)){
			curr = 1;
		} else {
			curr = 2;
		}
		String call = "Player " + curr + " please choose "+ noOfMonster + "Monster:";
		output = call;
		statusLine = createStatusLine(call);
		notifyObservers();
	}
	
	@Override
	public void addMonster(int[] number){
		if(currentPlayer.equals(p1)){
			p1.addMonster(number);
			output = getPlayerMonster();
			notifyObservers();
			currentPlayer = p2;
			callMonsterChoice();
			gameStat = "monP2";
		} else if(currentPlayer.equals(p2)){
			currentPlayer.addMonster(number);
			output = getPlayerMonster();
			startBattle();
		}
	}
	
	
	
	private void startBattle(){
		statusLine = createStatusLine("Battle begins!");
		gameStat = "battle";
		currMonP1 = p1.getMonsterList().get(0);
		currMonP2 = p2.getMonsterList().get(0);
		notifyObservers();
		currentPlayer = p1;
		battleMenu();
	}
	
	@Override
	public void battleMenu(){
		gameStat = "battle";
		String menu = "Battle: \n";
		menu += "Player1 Monster: " + currMonP1.getName() + "\tLife: " 
				+ currMonP1.getLife() + "\n";
		menu += "Player2 Monster: " + currMonP2.getName() + "\tLife: "
				+ currMonP2.getLife() + "\n";
		menu += ">>Player";
		if(currentPlayer.equals(p1)){
			menu += " 1 ";
		} else {
			menu += " 2 ";
		}
		menu += " is on Turn!<<\nPlease choose: \n";
		menu += "(1) Attack \t (2) Change Monster \n";
		menu += "(3) Item \t(4) Exit\n";
		output = menu;
		notifyObservers();
	}
	
	@Override
	public List<IAttack> attackMenu(){
		IMonster currentMon = null;
		currentMon = getCurrentMonster();
		gameStat = "attack";
		String menu = "Attack: \nName \tType \n";
		for(IAttack att : currentMon.getAttackList()){
			menu += att.getName() + "\t" + att.getType().getType() +  "\n";
		}
		menu += "(0) Back to Battle Menu";
		output = menu;
		notifyObservers();
		return currentMon.getAttackList();
	}


	private IMonster getCurrentMonster() {
		IMonster currentMon;
		if(currentPlayer.equals(p1))
			currentMon = currMonP1;
		else
			currentMon = currMonP2;
		return currentMon;
	}
	
	@Override
	public void itemMenu(){
		gameStat = "itemCh";
		String menu = "Items: \n";
		for(IItem item :  currentPlayer.getItemList()){
			menu += item.getName() + "\n";
		}
		menu += "(0) Back to Battle Menu\n";
		output = menu;
		statusLine = createStatusLine("Item selection");
		notifyObservers();
	}
	
	@Override
	public void useItem(IItem item){
		IMonster monster = null;
		int currPlayer = 0;
		if(currentPlayer.equals(p1)) {
			monster = currMonP1;
			currPlayer = 1;
		} else {
			monster = currMonP2;
			currPlayer = 2;
		}
		action.useItem(item, monster);
		String display = "Player " + currPlayer + " used: \n"
				+ item.getName() + " which added: ";
		if(item.getHealth() > 0)
			display += item.getHealth() + " health. ";
		if(item.getAttack() > 0)
			display += item.getAttack() + " attack. ";
		if(item.getDefence() > 0)
			display += item.getDefence() + " defence. ";
		currentPlayer.removeItem(item);
		output = display;
		statusLine = createStatusLine(item.getName() + " used");
		notifyObservers();
		battleMenu();
	}
	
	@Override
	public void changeMonsterMenu(){
		gameStat = "changeMon";
		String menu = "Change Monster: \n";
		menu += "Current Monster ";
		if(currentPlayer.equals(p1)){
			menu += currMonP1.getName();
		} else {
			menu += currMonP2.getName();
		}
		menu += " \n";
		menu += getPlayerMonster();
		menu += "(0) Back to Battle \n";
		output = menu;
		statusLine = createStatusLine("Change Monster");
		notifyObservers();
	}
	
	//Replace the current Monster by another Monster
	@Override
	public void changeMonster(IMonster monster){
		IMonster currmon = null;
		if(currentPlayer.equals(p1)){
			currmon = currMonP1;
			currMonP1 = monster;
		} else if (currentPlayer.equals(p2)){
			currmon = currMonP2;
			currMonP2 = monster;
		}
		String display = "Raplaced " + currmon.getName() + " by " + monster.getName() + "\n";
		output = display;
		statusLine = createStatusLine(display);
		notifyObservers();
		battleMenu();
	}
	
	@Override
	public void quickGame() {
		newGame(1);
	}
	
	public String getPlayerMonster(){
			List<IMonster> monList = currentPlayer.getMonsterList();
			int playerNo = 0;
			if(currentPlayer.equals(p1))
				playerNo = 1;
			else
				playerNo = 2;
			String list = "Player " + playerNo + " Monster: \n Name \tLife \tAttack \tDefence\n";
			for(IMonster mon : monList){
				list += mon.getName() + "\t"
						+ mon.getLife() + "\t" + mon.getAttack() + "\t" +mon.getDefence() + "\n";
			}
			return list;
	}
	
	@Override 
	public List<IMonster> getCurrentPlayerMonsterList(){
		IPlayer curr = getCurrentPlayer();
		return curr.getMonsterList();
	}
	
	@Override
	public String getStatus() {
		return statusLine;
	}
	
	@Override
	public int getNoOfMonster(){
		return noOfMonster;
	}
	
	@Override
	public void getMain() {
		gameStat = "menu";
		String menu = "Main Menu: \n"
				+ "(1) Quick Game \n"
				+ "(2) Two Monster fight \n"
				+ "(3) Four Monster fight \n"
				+ "(q) Exit";
		output = menu;
		statusLine = createStatusLine("Menu");
		notifyObservers();
	}

	@Override
	public IPlayer getCurrentPlayer(){
		return currentPlayer;
	}
	
	@Override
	public int getNoOfAttack(){
		if(currentPlayer.equals(p1))
			return currMonP1.getAttackList().size();
		else
			return currMonP2.getAttackList().size();
	}
	
	//Attack the enemy Monster by using the attack Number of the Current players Monster
	@Override
	public void attack(IAttack attack){
		IMonster sourceMon = null;
		IMonster targetMon = null;
		IPlayer enemy = null;
		if(currentPlayer.equals(p1)){
			sourceMon = currMonP1;
			targetMon = currMonP2;
			enemy = p2;
		} else {
			sourceMon = currMonP2;
			targetMon = currMonP1;
			enemy = p1;
		} 
		int damage = (int) action.attack(sourceMon, attack, targetMon);
		targetMon.decreaseLife(damage);
		String text = sourceMon.getName() + " uses " +  attack.getName() +
				" and made " + damage + " damage";
		output = text;
		statusLine = createStatusLine(text);
		notifyObservers();
		boolean contin = true;
		if(targetMon.getLife() == 0){
			contin = useNextMonster(enemy);
		}
		if(!contin) {
			end();
		} else {
		if(currentPlayer.equals(p1))
			currentPlayer = p2;
		else
			currentPlayer = p1;
		battleMenu();
		}
	}
	
	@Override
	public int getWinner(){
		if(currentPlayer.equals(p1)){
			return 1;
		}
		return 2;
	}
	
	private void end(){
		gameStat = "end";
		String text = "";
		if(currentPlayer.equals(p1)){
			text = "Player 1 Wins";
		} else {
			text = "Player 2 Wins";
		}
			text += " Thx for playing :)";
		output = text;
		statusLine = createStatusLine(text);
		notifyObservers();
		getMain();
	}
	
	//returns True, if player has next Monster and false if there is no Monster Left
	private boolean useNextMonster(IPlayer player){
		IMonster monster = null;
		int playerNo;
		if(player.equals(p1)){
			monster = currMonP1;
			playerNo = 1;
		} else {
			monster = currMonP2;
			playerNo = 2;
		}
		player.getMonsterList().remove(monster);
		if(player.getMonsterList().size() > 0){
			if(player.equals(p1))
				currMonP1 = player.getMonsterList().get(0);
			else
				currMonP2 = player.getMonsterList().get(0);
			String message = "Player" + playerNo + "Changed Monster";
			output = message;
			createStatusLine(message);
			notifyObservers();
			return true;
		} else 
			return false;
	}
	
	@Override
	public IMonster getCurrentP1Mon(){
		return currMonP1;
	}
	
	@Override
	public IMonster getCurrentP2Mon(){
		return currMonP2;
	}
	
	private String createStatusLine(String statText) {
		int rest = (WINDOW_WIDTH - statText.length()) / 2;
		StringBuilder statLine = new StringBuilder();
		String numberSign = printNumberSign(rest);
		statLine.append(numberSign);
		statLine.append(statText);
		statLine.append(numberSign);
		statLine.append("\n");
		return statLine.toString();
	}

	
	
	private String printNumberSign(int rest) {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int i = 0; i < rest - 4; i++) {
			sb.append("#");
		}
		sb.append(" ");
		return sb.toString();
	}
	
	@Override
	public String getImagePath(IMonster mon){
		return factory.getImagePath(mon);
	}
	
	@Override
	public String getImagePath(IMonster mon, String ext){
		return factory.getImagePath(mon, ext);
	}
	
	@Override
	public List<IMonster> getMonsterListFull(){
		return factory.getMonsterListFull();
	}


	@Override
	public int getMonsterNumber(String string) {
		return factory.getMonsterNumber(string);
	}
}
