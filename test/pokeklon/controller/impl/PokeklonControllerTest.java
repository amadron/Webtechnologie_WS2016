package pokeklon.controller.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pokeklon.controller.IMonsterFactory;
import pokeklon.controller.IPlayer;
import pokeklon.controller.IPokeklonController;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import pokeklon.model.impl.Monster;
import pokeklon.model.impl.item.Potion;
import pokeklon.model.impl.item.XtraAttack;
import pokeklon.model.impl.item.XtraDefence;

public class PokeklonControllerTest {
	private IPokeklonController controller;

	@Before
	public void setUp() throws Exception {
		controller = new PokeklonController();
		controller.quickGame();
	}

	@Test
	public void testPokeklonController() {
		String statusLine = controller.getStatusLine();
		assertFalse("statusLine set incorrect!", statusLine.isEmpty());
		
	}

	@Test
	public void testReset() {
		controller.reset();
		String gSTest = controller.getGameStat();
		assertEquals("Getting wrong gameStat: " + gSTest, "menu", gSTest);
	}

	@Test
	public void testGetGamestat() {
		controller.reset();
		String gSTest = controller.getGameStat();
		assertEquals("Getting wrong gameStat: " + gSTest, "menu", gSTest);
		controller.battleMenu();
		gSTest = controller.getGameStat();
		assertEquals("Getting wrong gameStat: " + gSTest, "battle", gSTest);
		
	}

	@Test
	public void testNewGame() {
		controller.newGame(2);
		assertNotNull("Player1 or current Player not set!", controller.getCurrentPlayer());
		assertTrue("Wrong type for Player1!", controller.getCurrentPlayer() instanceof Player);
		controller.changePlayer();
		assertNotNull("Player2 not set", controller.getCurrentPlayer());		
		assertTrue("Wrong type for Player2!", controller.getCurrentPlayer() instanceof Player);
		controller.newGame(2);
		assertEquals("Getting wrong number of monsters!", 2 ,controller.getNoOfMonster());
		assertEquals("Getting wrong gameStat: " + controller.getGameStat(), "monP1", controller.getGameStat());
	}

	@Test
	public void testQuickGame() {
		controller.quickGame();
		assertNotNull("Player 1, no monster assigned to!", controller.getCurrentP1Mon() instanceof Monster);
		assertNotNull("Player 2, no monster assigned to!", controller.getCurrentP2Mon() instanceof Monster);
		assertEquals("Wrong startplayer set!",controller.getCurrentMonster(), controller.getCurrentP1Mon());
		assertEquals("To many monster set for currentPlayer!", controller.getCurrentPlayerMonsterList().size(), 1);
	}

	@Test
	public void testGetPlayerNumber() {
		IPlayer tmp = controller.getPlayer1();
		assertEquals("Getting wrong Player number!", 1, controller.getPlayerNumber(tmp));
		tmp = controller.getPlayer2();
		assertEquals("Getting wrong Player number!", 2, controller.getPlayerNumber(tmp));
	}

	@Test
	public void testBattleMenu() {
		controller.battleMenu();
		assertEquals("Getting wrong gameStat for battle menu!", "battle", controller.getGameStat());
		controller.changePlayer();
		controller.battleMenu();
	}

	@Test
	public void testAttackMenu() {
		controller.attackMenu();
		assertEquals("getting wrong gameStat for AttackMenu!", controller.getGameStat(), "attack");
	}

	@Test
	public void testGetCurrentMonster() {
		assertEquals("return of getCurrentMonster doesn't fit!", controller.getCurrentP1Mon(), controller.getCurrentMonster());
		controller.changePlayer();
		assertEquals("return of getCurrentMonster doesn't fit!", controller.getCurrentP2Mon(), controller.getCurrentMonster());
		
	}
	
	@Test
	public void testGetPlayerMonsterWithoutCurrent(){
		List<IMonster> mL = controller.getCurrentPlayerMonsterList();
		assertEquals("Getting Wrong size of List for getPlayerMonsterWithoutCurrent", controller.getPlayerMonsterWithoutCurrent().size(), mL.size());
	}
	
	@Test
	public void testGetCurrentAttackList(){
		List<IAttack> aL = controller.getCurrentAttackList();
		assertTrue("Attack List too small", aL.size() > 0);
		assertTrue("Attack List too big", aL.size() <= 4);
		assertTrue("Getting wrong list type!", aL.get(0) instanceof IAttack);
	}

	@Test
	public void testItemMenu() {
		controller.itemMenu();
		assertEquals("wrong gameStat for item menu", "itemCh" ,controller.getGameStat());
	}

	@Test
	public void testUseItem() {
		IItem potion = new Potion();
		IItem xAtt = new XtraAttack();
		IItem xDef = new XtraDefence();
		IMonster current = controller.getCurrentMonster();
		current.decreaseLife(25);
		double currLife = current.getLife();
		double currDef = current.getDefence();
		double currAtt = current.getAttack();
		controller.useItem(potion);
		assertEquals("getting unexceptet life value after using potion!", currLife + 20, current.getLife(),0);
		controller.useItem(xAtt);
		assertEquals("getting unexceptet attack value after using XtraAttack!", currAtt + 5, current.getAttack(),0);
		controller.useItem(xDef);
		assertEquals("getting unexceptet defence value after using XtraDefence!", currDef + 5, current.getDefence(),0);
	}

	@Test
	public void testChangeMonsterMenu() {
		controller.changeMonsterMenu();
		assertEquals("Getting wrong GameStat for ChangeMonsterMenu!", controller.getGameStat(), "changeMon");
	}

	@Test
	public void testChangeMonster() {
		controller.newGame(2);
		controller.addMonster(new int[]{1,2});
		controller.changePlayer();
		List<IMonster> mLP1 = controller.getCurrentPlayerMonsterList();
		controller.changePlayer();
		controller.addMonster(new int[]{1,2});
		controller.changePlayer();
		List<IMonster> mLP2 = controller.getCurrentPlayerMonsterList();
		controller.changePlayer();
		IPlayer tmpPlayer = controller.getCurrentPlayer();
		int mLSize = mLP1.size();
		//player 1
		controller.changeMonster(mLP1.get(1));
		assertEquals("Monster change for player 1 failed!", mLP1.get(1), controller.getCurrentP1Mon());
		assertNotEquals("Current player not changed after changeing monster!", tmpPlayer, controller.getCurrentPlayer());
		controller.changePlayer();
		assertEquals("List size changed after monster change! (player 1)", mLSize, controller.getCurrentPlayerMonsterList().size());
		//player 2
		controller.changePlayer();
		mLSize = mLP2.size();
		controller.changeMonster(mLP2.get(1));
		assertEquals("Monster change for player 2 failed!", mLP2.get(1), controller.getCurrentP2Mon());
		controller.changePlayer();
		assertEquals("List size changed after monster change! (player 2)", mLSize, controller.getCurrentPlayerMonsterList().size());
	}

	@Test
	public void testGetCurrentPlayerMonsterList() {
		//Tested with changeMonster
	}

	@Test
	public void testGetStatus() {
		//Already tested several Times in various methods e.g.: constructor
	}

	@Test
	public void testGetNoOfMonster() {
		assertEquals("Getting wrong number of monsters in quickGame", 1, controller.getNoOfMonster());
		controller.newGame(2);
		assertEquals("Getting wrong number of monsters in 2 vs. 2 Game", 2, controller.getNoOfMonster());
		controller.newGame(4);
		assertEquals("Getting wrong number of monsters in 4 vs. 4 Game", 4, controller.getNoOfMonster());
	}

	@Test
	public void testGetMain() {
		controller.getMain();
		String statusLine = controller.getStatusLine();
		String gameStat = controller.getGameStat();
		
		assertEquals("Getting wrong gamestat!","menu",gameStat);
		assertNotNull("Output not printed", statusLine);
		
		
	}

	@Test
	public void testGetCurrentPlayer() {
		IPlayer tmp = controller.getPlayer1();
		assertEquals("Getting wrong current player while being player 1", controller.getCurrentPlayer(), tmp);
		controller.changePlayer();
		tmp = controller.getPlayer2();
		assertEquals("Getting wrong current player while being player 2", controller.getCurrentPlayer(), tmp);
		
	}

	@Test
	public void testGetNoOfAttack() {
		List<IAttack> tmp = controller.getCurrentMonster().getAttackList();
		assertEquals("getting wrong size of attackList for monster of player1", controller.getNoOfAttack(), tmp.size());
		controller.changePlayer();
		tmp = controller.getCurrentMonster().getAttackList();
		assertEquals("getting wrong size of attackList for monster of player1", controller.getNoOfAttack(), tmp.size());
		
	}

	@Test
	public void testAttack() {
		controller.newGame(2);
		controller.addMonster(new int[]{0,1});
		controller.addMonster(new int[]{2,3});
		IMonster tmpMon;
		IAttack tmpAtt;
		while (controller.getCurrentMonster().getLife() > 0) {
			tmpMon = controller.getCurrentMonster();
			tmpAtt = tmpMon.getAttackList().get(0);
			controller.attack(tmpAtt);
			String status = controller.getStatusLine();
			if(status.contains("Attack failed!")){
				continue;
			} else if(status.contains("Thx")){
				break;
			}
			assertTrue("wrong monster in status! " + status, status.contains(tmpMon.getName()));
			assertTrue("wrong attack in status! " + status, status.contains(tmpAtt.getName()));
		}
	}

	@Test
	public void testChangePlayer() {
		IPlayer current = controller.getCurrentPlayer();
		controller.changePlayer();
		assertNotEquals("Player not changed from p1 to p2!", current, controller.getCurrentPlayer());
		current = controller.getCurrentPlayer();
		controller.changePlayer();
		assertNotEquals("Player not changed from p2 to p1!", current, controller.getCurrentPlayer());
	}

	@Test
	public void testGetWinner() {
		assertEquals("Getting wrong value for winner (current player1)", controller.getWinner(), 1);
		controller.changePlayer();
		assertEquals("Getting wrong value for winner (current player2)", controller.getWinner(), 2);
	}

	@Test
	public void testGetCurrentP1Mon() {
		assertEquals("getting wrong monster for player 1", controller.getCurrentMonster(), controller.getCurrentP1Mon());
	}

	@Test
	public void testGetCurrentP2Mon() {
		controller.changePlayer();
		assertEquals("getting wrong monster for player 2", controller.getCurrentMonster(), controller.getCurrentP2Mon());
	}

	@Test
	public void testGetImagePathIMonster() {
		IMonster testMonster = controller.getMonsterListFull().get(0);
		String path = "src/pokeklon/data/MonsterPics/01.jpg";
		assertEquals("Getting wrong imagepath!", path, controller.getImagePath(testMonster));
	}

	@Test
	public void testGetImagePathIMonsterString() {
		IMonster testMonster = controller.getMonsterListFull().get(0);
		String path = "src/pokeklon/data/MonsterPics/01_big.jpg";
		assertEquals("Getting wrong imagepath!", path, controller.getImagePath(testMonster, "_big"));
		
	}

	@Test
	public void testGetMonsterListFull() {
		IMonsterFactory factory = new MonsterFactory();
		List<IMonster> tmpList = factory.getMonsterListFull();
		assertEquals("getting corrupt monsterList", tmpList.size(), controller.getMonsterListFull().size());
	}

	@Test
	public void testGetMonsterNumber() {
		IMonster tmpMonster = controller.getMonsterListFull().get(0);
		assertEquals("getting wrong monster number", 0, controller.getMonsterNumber(tmpMonster.getName()));
	}

	@Test
	public void testGetItemList() {
		int iLSize = controller.getItemList().size();
		assertEquals("wrong amount of Items for Player 1", 4, iLSize);
	}

}
