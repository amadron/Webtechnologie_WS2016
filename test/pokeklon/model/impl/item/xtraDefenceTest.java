package pokeklon.model.impl.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pokeklon.model.IItem;
import pokeklon.model.impl.item.XtraDefence;

public class xtraDefenceTest {
	
	private IItem item;
	@Before
	public void setUp() throws Exception {
		item = new XtraDefence();
	}

	@Test
	public void testGetName() {
		String name = item.getName();
		assertEquals("Unexpected value for item.getName():" + item.getName(), "X-Defence", name);
	}

	@Test
	public void testGetHealth() {
		int health = item.getHealth();
		assertEquals("Unexpected value for item.getHealth():" + item.getHealth(), 0, health);
	}

	@Test
	public void testGetAttack() {
		int attack = item.getAttack();
		assertEquals("Unexpected value for item.getAttack():" + item.getAttack(), 0, attack);
	}

	@Test
	public void testGetDefence() {
		int defence = item.getDefence();
		assertEquals("Unexpected value for item.getDefence():" + item.getDefence(), 5, defence);
	}

}
