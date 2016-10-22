package pokeklon.model.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pokeklon.model.IAttack;
import pokeklon.model.impl.types.TypeNormal;

public class AttackTest {
	
	private IAttack testAtt;

	@Before
	public void setUp() throws Exception {
		testAtt = new Attack("name", 50, 51, new TypeNormal(), "test");
	}

	@Test
	public void testAttack() {
		IAttack tmp = testAtt;
		testAtt = new Attack("name", 50, 51, new TypeNormal(), "test");
		assertEquals("problem with setting up new attack (name)", tmp.getName(), testAtt.getName());
		assertEquals("problem with setting up new attack (strength)", tmp.getStrength(), testAtt.getStrength(),0);
		assertEquals("problem with setting up new attack (precission)", tmp.getPrecission(), testAtt.getPrecission(),0);
		assertTrue("problem with setting up new attack (type)", testAtt.getType() instanceof TypeNormal);
		assertEquals("problem with setting up new attack (annormaly)", tmp.hasAnnormaly(), testAtt.hasAnnormaly());
	}

	@Test
	public void testGetName() {
		assertEquals("getting wrong name!", "name", testAtt.getName());
	}

	@Test
	public void testGetPrecission() {
		assertEquals("getting wrong precission!", 51, testAtt.getPrecission(),0);
	}

	@Test
	public void testGetStrength() {
		assertEquals("getting wrong strength!", 50, testAtt.getStrength(),0);
	}

	@Test
	public void testGetType() {
		assertTrue("getting wrong type of attack", testAtt.getType() instanceof TypeNormal);
	}

	@Test
	public void testHasAnnormaly() {
		assertTrue("problems on seting annormaly when true", testAtt.hasAnnormaly());
		testAtt = new Attack("name", 50, 51, new TypeNormal(), "");
		assertFalse("problems on seting annormaly when false", testAtt.hasAnnormaly());
	}

}
