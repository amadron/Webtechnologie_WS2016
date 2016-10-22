package pokeklon.controller.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pokeklon.controller.IPlayer;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import pokeklon.model.impl.item.XtraAttack;

public class PlayerTest {
	private IPlayer player = null;
	private IMonster tmp = null;

	@Before
	public void setUp() throws Exception {
		player = new Player();
		player.addMonster(new int[]{1});
		tmp = player.getMonsterList().get(0);
		player = new Player();
		player.addMonster(new int[]{2,3,4,5});
	}

	@Test
	public void testPlayer() {
		player = new Player();
	}

	@Test
	public void testAddMonsterIntArray() {
		int[] arr = new int[]{3,4};
		int expected = 2;
		
		player.addMonster(arr);
		assertEquals("Adding monster with array dont work correctly "
				+ "(Size: "+ player.getMonsterList().size() + " expected: " + expected,
				player.getMonsterList().size(), expected);
	}

	@Test
	public void testAddMonsterIMonster() {
		player.addMonster(tmp);
		assertTrue(player.getMonsterList().contains(tmp));
	}

	@Test
	public void testGetMonsterList() {
		List<IMonster> mL = player.getMonsterList();
		assertEquals("Getting wrong size of MonsterList " + mL.size(), mL.size(), 4);
	}

	@Test
	public void testRemoveMonster() {
		tmp = player.getMonsterList().get(2);
		assertTrue("Monster at beginning not in monsterList: " + tmp,  (player.getMonsterList().contains(tmp)));
		player.removeMonster(tmp);
		assertTrue("Monster still in monsterList: " + tmp,  !(player.getMonsterList().contains(tmp)));
		assertEquals("Size of monsterlist don't fit: " + player.getMonsterList().size(), 3, player.getMonsterList().size());
	}

	@Test
	public void testGetItemList() {
		assertEquals("Wrong size of itemList: " + player.getNoOfItems(), player.getItemList().size(), player.getNoOfItems());
	}

	@Test
	public void testRemoveItem() {
		int sum = player.getNoOfItems() - 1;
		IItem xAtt = new XtraAttack();
		player.removeItem(xAtt);
		assertEquals("Number of items Wrong: " + player.getNoOfItems(), player.getNoOfItems(), sum);
	}

}
