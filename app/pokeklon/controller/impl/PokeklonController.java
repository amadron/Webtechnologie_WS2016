package pokeklon.controller.impl;

import java.util.List;

import pokeklon.controller.IMonsterFactory;
import pokeklon.controller.IPlayer;
import pokeklon.controller.IPokeklonController;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import util.importData.GetMonsterAndAttackList;
import util.observer.Observable;

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
	private static final int WINDOW_WIDTH = 80;
	private static final int NUMBER_P1 = 1;
	private static final int NUMBER_P2 = 2;
	
	private String gameStat = null;
	private String statusLine = null;
	private IPlayer p1 = null;
	private IMonster currMonP1 = null;
	private IPlayer p2 = null;
	private IMonster currMonP2 = null;
	private IPlayer currentPlayer = null;
	private Action action = new Action();
	private int noOfMonster = 0;
	private IMonsterFactory factory = null;
	private IMonster currentMonster;
	private IMonster enemyCurrMonster;
	private IPlayer enemyPlayer;
	private boolean quick = false;
	
	@Inject
	public PokeklonController() {
		statusLine= 
				createStatusLine("Pokeklon - Project for SoftwareEngineering AIN3");
		factory = new MonsterFactory();
	}


	@Override
	public void reset() {
		getMain();
	}
	
	@Override
	public String getGameStat(){
		return gameStat;
	}
	
	@Override
	public void newGame(int i) {
		p1 = new Player();
		p2 = new Player();
		noOfMonster = i;
		currentPlayer = p1;
		gameStat = "monP1";
		//notifyObservers();
		callMonsterChoice();
	}
	
	@Override
	public void quickGame() {
		quick = true;
		p1 = new Player();
		p2 = new Player();
		noOfMonster = 1;
		currentPlayer = p1;
		int[] ranMon1 = new int[1];
		ranMon1[0] = getRandomMonster();
		addMonster(ranMon1);
		int[] ranMon2 = new int[1];
		do {
			ranMon2[0] = getRandomMonster();
		} while(ranMon2[0] == ranMon1[0]);
		addMonster(ranMon2);
	}
	
	private int getRandomMonster(){
		return (int) (Math.random() * GetMonsterAndAttackList.getMonsterList().size());
	}
	
	private String getCurrentPlayerAsString(){
		return "Player " + getPlayerNumber(currentPlayer);
	}
	
	private void callMonsterChoice(){
		String call = getCurrentPlayerAsString() + " please choose "+ noOfMonster + "Monster:";
		statusLine = createStatusLine(call);
		notifyObservers();
	}

	@Override
	public int getPlayerNumber(IPlayer player) {
		if(player.equals(p1)){
			return NUMBER_P1;
		} 
		return NUMBER_P2;
	}
	
	@Override
	public void addMonster(int[] number){
		int[] count = number;
		if(currentPlayer.equals(p1)){
			p1.addMonster(count);
			//notifyObservers();
			gameStat = "monP2";
			changePlayer();
			if(!quick)
				callMonsterChoice();
			//notifyObservers();
		} else {
			p2.addMonster(count);
			startBattle();
		}
	}
	
	
	
	private void startBattle(){
		statusLine = createStatusLine("Battle begins!");
		gameStat = "battle";
		currMonP1 = p1.getMonsterList().get(0);
		currMonP2 = p2.getMonsterList().get(0);
		currentPlayer = p1;
		currentMonster = currMonP1;
		enemyPlayer = p2;
		enemyCurrMonster = currMonP2;
		notifyObservers();
		battleMenu();
	}
	
	@Override
	public void battleMenu(){
		gameStat = "battle";
		notifyObservers();
	}
	
	@Override
	public List<IAttack> getCurrentAttackList(){
		return currentMonster.getAttackList();
	}
	
	@Override
	public void attackMenu(){
		gameStat = "attack";
		statusLine = createStatusLine("Attack");
		notifyObservers();
	}

	@Override
	public IMonster getCurrentMonster() {
		return currentMonster;
	}
	
	@Override
	public void itemMenu(){
		gameStat = "itemCh";
		statusLine = createStatusLine("Item selection");
		notifyObservers();
	}
	
	@Override
	public void useItem(IItem item){
		action.useItem(item, currentMonster);
		currentPlayer.removeItem(item);
		statusLine = createStatusLine(item.getName() + " used");
		battleMenu();
	}
	
	@Override
	public void changeMonsterMenu(){
		gameStat = "changeMon";
		statusLine = createStatusLine("Change Monster");
		notifyObservers();
	}
	
	@Override
	public List<IMonster> getPlayerMonsterWithoutCurrent() {
		List<IMonster> monList = currentPlayer.getMonsterList();
		monList.remove(currentMonster);
		return monList;
	}


	//Replace the current Monster by another Monster
	@Override
	public void changeMonster(IMonster monster){
		IMonster currmon = null;
		if(currentPlayer.equals(p1)){
			currmon = currMonP1;
			currMonP1 = monster;
		} else{
			currmon = currMonP2;
			currMonP2 = monster;
		}
		String display = "Raplaced " + currmon.getName() + " by " + monster.getName();
		statusLine = createStatusLine(display);
		changePlayer();
		battleMenu();
	}
	
	@Override 
	public List<IMonster> getCurrentPlayerMonsterList(){
		IPlayer curr = getCurrentPlayer();
		return curr.getMonsterList();
	}
	
	@Override
	public String getStatusLine() {
		return statusLine;
	}
	
	@Override
	public int getNoOfMonster(){
		return noOfMonster;
	}
	
	@Override
	public void getMain() {
		gameStat = "menu";
		statusLine = createStatusLine("Menu");
		notifyObservers();
	}

	@Override
	public IPlayer getCurrentPlayer(){
		return currentPlayer;
	}
	
	@Override
	public int getNoOfAttack(){
		if(currentPlayer.equals(p1)) {
			return currMonP1.getAttackList().size();
		} else {
			return currMonP2.getAttackList().size();
		}
	}
	
	//Attack the enemy Monster by using the attack Number of the Current players Monster
	@Override
	public void attack(IAttack attack){
		int damage = (int) action.attack(currentMonster, attack, enemyCurrMonster);
		enemyCurrMonster.decreaseLife(damage);
		String text = getAttackText(attack, damage);
		statusLine = createStatusLine(text);
		boolean contin = testEnemyGameOver();
		if(!contin) {
			end();
		} else {
		changePlayer();
		//notifyObservers();
		battleMenu();
		}
	}


	private boolean testEnemyGameOver() {
		boolean contin = true;
		if(enemyCurrMonster.getLife() == 0){
			contin = useNextMonster();
		}
		return contin;
	}


	private String getAttackText(IAttack attack, int damage) {
		String text = "";
		if(damage > 0){
			text = currentMonster.getName() + " uses " +  attack.getName() +
				" and made " + damage + " damage";
		} else {
			text = "Attack failed!";
		}
		return text;
	}


	@Override
	public void changePlayer() {
		if(currentPlayer.equals(p1)) {
			currentPlayer = p2;
			currentMonster = currMonP2;
			enemyPlayer = p1;
			enemyCurrMonster = currMonP1;
		} else {
			currentPlayer = p1;
			currentMonster = currMonP1;
			enemyPlayer = p2;
			enemyCurrMonster = currMonP2;
		}
	}
	
	@Override
	public int getWinner(){
		if(currentPlayer.equals(p1)){
			return NUMBER_P1;
		} else {
			return NUMBER_P2;
		}
	}
	
	private void end(){
		gameStat = "end";
		String text = "";
		text = getCurrentPlayerAsString() +" Wins ";
		text += "- Thx for playing :)";
		statusLine = createStatusLine(text);
		notifyObservers();
	}
	
	//returns True, if player has next Monster and false if there is no Monster Left
	private boolean useNextMonster(){
		enemyPlayer.getMonsterList().remove(enemyCurrMonster);
		if(enemyPlayer.getMonsterList().size() > 0){
			if(enemyPlayer.equals(p1)) {
				currMonP1 = enemyPlayer.getMonsterList().get(0);
			} else {
				currMonP2 = enemyPlayer.getMonsterList().get(0);
			}
			int playerNumber = getPlayerNumber(enemyPlayer);
			String message = "Player" + playerNumber + "Changed Monster";
			createStatusLine(message);
			notifyObservers();
			return true;
		} else {
			return false;
		}
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
		for (int i = 0; i < rest - 2; i++) {
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
	
	@Override
	public List<IItem> getItemList(){
		return currentPlayer.getItemList();
	}
	
	@Override
	public IPlayer getPlayer1(){
		return p1;
	}
	@Override
	public IPlayer getPlayer2(){
		return p2;
	}
}
