package pokeklon.controller.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pokeklon.controller.IMonsterFactory;
import pokeklon.model.IMonster;
import util.importData.GetMonsterAndAttackList;

public class MonsterFactoryTest {
	
	private IMonsterFactory monster = null;
	private IMonster testMonster = null;
	private String monsterPath;
	private String monsterPathBig;

	@Before
	public void setUp() throws Exception {
		monster = new MonsterFactory();
		testMonster = monster.getMonsterListFull().get(1);
		monsterPath =  "src/pokeklon/data/MonsterPics/02.jpg";
		monsterPathBig =  "src/pokeklon/data/MonsterPics/02_big.jpg";
	}

	@Test
	public void testMonsterFactory() {
		assertTrue("monsterList not created", !monster.getMonsterListFull().isEmpty());
		assertTrue("attackList not created!", !monster.getAttackListFull().isEmpty());
	}

	@Test
	public void testGetMonsterListFull() {
		assertTrue("Error on getMonsterListFull", !monster.getMonsterListFull().isEmpty());
	}

	@Test
	public void testGetMonsterList() {
		int[] testArr = new int[]{1, 2, -1, 3};
		List<IMonster> ml = monster.getMonsterList(testArr);
		assertEquals("getting monsterList of size 3. Current size: " + ml.size(),
				3, ml.size());
	}

	@Test
	public void testGetAttackList() {
		List<String> aL = Arrays.asList("1","3","-2", "-");
		int size = monster.getAttackList(aL).size();
		assertEquals("Wrong length of AttackList! Exp. 2 Actual: " +size , 2, size);
	}

	@Test
	public void testGetImagePathIMonster() {
		String tmpPath = monster.getImagePath(testMonster);
		assertEquals("Getting wrong image path: " + tmpPath, monsterPath, tmpPath);
	}

	@Test
	public void testGetImagePathIMonsterString() {
		String tmpPath = monster.getImagePath(testMonster, "_big");
		assertEquals("Getting wrong big image path: " + tmpPath, monsterPathBig, tmpPath);
	}

	@Test
	public void testGetAllImagePaths() {
		int size = monster.getAllImagePaths(GetMonsterAndAttackList.getMonsterList()).size();
		assertEquals("getting wrong size of image list" + size, GetMonsterAndAttackList.getMonsterList().size(), size);
	}

	@Test
	public void testGetMonsterNumber() {
		String name = testMonster.getName();
		int monsterNumber = monster.getMonsterNumber(name);
		assertEquals("Getting wrong monster Number: " + monsterNumber, 1, monsterNumber);
	}

}
