package pokeklon.controller;

import java.util.List;

import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import util.observer.IObservable;

public interface IPokeklonController extends IObservable {

	void reset();
	
	/**
	 * Get the current game state.
	 * @return the current game state.
	 */
	String getGameStat();

	/**
	 * Start a new Game. Players will be initialized.
	 * @param i how many Monsters each player should have.
	 */
	void newGame(int i);

	/**
	 * Starts a new Game with one monster for each player.
	 * The monsters are chosen by a random number
	 */
	void quickGame();
	
	/**
	 * Get the player which is on turn.
	 * @return the player on turn
	 */
	IPlayer getCurrentPlayer();
	
	/**
	 * Adds the monsters to the current player
	 * @param number a list of monster numbers which
	 * should be added to current player.
	 */
	void addMonster(int[] number);
	
	/**
	 * Get the StatusLine.
	 * the statusLine is for logging and GUI
	 * @return
	 */
	String getStatusLine();
	
	/**
	 * Sets gameStat for getting battleMenu
	 */
	void battleMenu();
	
	/**
	 * Sets the game stat to "attack"
	 * and produces the attack menu as output for
	 * the TUI
	 * @return the List of attacks which are displayed
	 * in the Menu
	 */
	 void attackMenu();
	
	/**
	 * Sets the game stat to "changeMon" and
	 * produces the change Menu as output for the TUI
	 */
	 void changeMonsterMenu();
	
	/**
	 * Replace current monster on turn by another monster.
	 * @param monster the current Monster will replaced by
	 * this monster 
	 */
	 void changeMonster(IMonster monster);
	
	/**
	 * attacks the enemy monster.
	 * @param attack the attack which is used to attack
	 * the enemys monster
	 */
	 void attack(IAttack attack);

	/**
	 * set the game state to "menu", which means the game is 
	 * in main menu. It also set the game menu as output
	 * for the TUI
	 */
	void getMain();
	
	/**
	 * Returns the number of monsters for the current game.
	 * @return number of monster used.
	 */
	int getNoOfMonster();

	/**
	 * Get the players 1 Monster which is currently selected
	 * @return the current monster of player 1
	 */
	IMonster getCurrentP1Mon();

	/**
	 * Get the players 2 monster which is currently selected
	 * @return the current monster of player 2
	 */
	IMonster getCurrentP2Mon();

	/**
	 * Returns how many attacks the current monster
	 * has.
	 * @return the number of attacks.
	 */
	int getNoOfAttack();

	/**
	 * changes the game state to item selection
	 * an produces an Menu for the TUI as output.
	 */
	void itemMenu();

	/**
	 * Use an item to the monster which is on turn
	 * @param itemNo the Item to use
	 */
	void useItem(IItem itemNo);

	/**
	 * Get the list of all monsters of the player 
	 * which is on turn
	 * @return
	 */
	List<IMonster> getCurrentPlayerMonsterList();

	/**
	 * Get the path of the image of a monster
	 * @param mon the monster of which you want
	 * the image path
	 * @return the path of the monsters image 
	 */
	String getImagePath(IMonster mon);

	/**
	 * Get the path of the monsters image
	 * with extension (like big for the big picture)
	 * @param mon the monster that you want the path from
	 * @param ext additional parameter
	 * @return the image path
	 */
	String getImagePath(IMonster mon, String ext);

	/**
	 * Get the list of all monsters which you can choose from
	 * @return list of all monsters
	 */
	List<IMonster> getMonsterListFull();

	/**
	 * Get the number of the monster by input its name as
	 * a string.
	 * @param string the monsters name
	 * @return the number of the monster
	 */
	int getMonsterNumber(String string);
	
	/**
	 * returns the winner of the match.
	 * @return the winner.
	 */
	int getWinner();

	/**
	 * Get the monster of the current player
	 * @return the current monster
	 */
	IMonster getCurrentMonster();

	/**
	 * Switches/ changes the current player
	 */
	void changePlayer();
	
	/**
	 * Get the number of the player
	 * @param the player the number is needed for.
	 * @return the current players number
	 */
	int getPlayerNumber(IPlayer player);
	
	/**
	 * Get the ItemList of the current Player.
	 * @return current players items.
	 */
	List<IItem> getItemList();

	/**
	 * Get player 1
	 * @return Player 1
	 */
	IPlayer getPlayer1();

	/**
	 * Get player 2
	 * @return player 2
	 */
	IPlayer getPlayer2();

	/**
	 * Returns the current monsterList WITHOUT the fighting monster of the current player.
	 * @return monsterList without current monster.
	 */
	List<IMonster> getPlayerMonsterWithoutCurrent();

	/**
	 * Returns all attacks of the current monster.
	 * @return List with all attacks of current monster.
	 */
	List<IAttack> getCurrentAttackList();


}
