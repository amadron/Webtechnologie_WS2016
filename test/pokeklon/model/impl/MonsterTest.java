package pokeklon.model.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import pokeklon.model.IAttack;
import pokeklon.model.IType;
import pokeklon.model.impl.Monster;
import pokeklon.model.impl.types.TypeFire;

public class MonsterTest {

	private Monster monster;
	private String name;
	private IType type;
	private double life;
	private double attack;
	private double defence;
	private List<IAttack> attArr;
	
	@Before
	public void setUp() throws Exception {
		this.name = "test";
		this.type = new TypeFire();
		this.life = 50;
		this.attack = 51;
		this.defence = 52;
		this.monster = new Monster(name, type, life, attack, defence, attArr);
	}
	
//	@Rule
//	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetName() {
		assertTrue("Unexpected value for monster.getName():" + monster.getName(), this.name.equals(monster.getName()));
	}

	@Test
	public void testGetLife() {
		assertEquals("Unexpected value for monster.getLife():" + monster.getLife(), life, monster.getLife(),.1);
	}
	@Test
	public void testGetMaxLife() {
		assertEquals("Unexpected value for monster.getMaxLife():" + monster.getMaxLife(), life, monster.getMaxLife(),.1);
	}
	
	@Test
	public void testDecreaseLife() {
		double life = monster.decreaseLife(5);
		assertEquals("Unexpected value for monster.decrementLife(double x) 'normal':" + monster.getLife(), 45, life,.1);
		//test lower than 0.
		life = monster.decreaseLife(55);
		assertEquals("Unexpected value for monster.decrementLife(double x): '<0'" + monster.getLife(), 0, life,.1);
		
		Exception e = null;
		try {
			monster.decreaseLife(5);
		} catch (Exception e2) {
			e = e2;
		}
		assertNull(e);
		try {
			monster.decreaseLife(-5);
		} catch (Exception e2) {
			e = e2;
		}
		assertTrue(e instanceof IllegalArgumentException);
		
	}
	
	@Test
	public void testIncreaseLife(){		
		double life = monster.increaseLife(5);
		assertEquals("Unexpected value for monster.increaseLife(double x) '> MAX_HEALT':" + monster.getLife(), 50, life,.1);
		//Possible value
		life = monster.decreaseLife(25);
		life = monster.increaseLife(5);
		assertEquals("Unexpected value for monster.increaseLife(double x) '< MAX_HEALT':" + monster.getLife(), 30, life,.1);
		//Increase with no life remaining
		life = monster.decreaseLife(55);
		life = monster.increaseLife(25);
		assertEquals("Unexpected value for monster.increaseLife(double x) 'increase with life = 0':",0, 0,.0);
		Exception e = null;
		try {
			monster.increaseLife(-5);
		} catch (Exception e2) {
			e = e2;
		}
		assertTrue(e instanceof IllegalArgumentException);
	}

	@Test
	public void testGetAttack() {
		double attack = monster.getAttack();
		assertEquals("Unexpected value for monster.getAttack():" + monster.getAttack(), 51, attack,.1);
	}
	
	@Test
	public void testDecreaseAttack() {
		double decAttack = monster.decreaseAttack(5);
		assertEquals("Unexpected value for monster.decreaseAttack():" + monster.getAttack(), 46, decAttack,.0);
		decAttack = monster.getMinAttack();
		assertEquals(decAttack, monster.decreaseAttack(100),.0);
		Exception e = null;
		try {
			monster.decreaseAttack(5);
		} catch (Exception e2) {
			e = e2;
		}
		assertNull(e);
		try {
			monster.decreaseAttack(-5);
		} catch (Exception e2) {
			e = e2;
		}
		assertTrue(e instanceof IllegalArgumentException);
		
	}
	
	@Test
	public void testIncreaseAttack(){
		double attack = monster.increaseAttack(5);
		assertEquals("Unexpected value for monster.increaseAttack() 'normal':" + monster.getAttack() ,attack, 56,.0);
		//Test getting > MAX_ATT
		attack = monster.increaseAttack(100);
		assertEquals("Unexpected value for monster.increaseAttack() '>MAX_ATTACK':" + monster.getAttack(), attack, monster.getMaxAttack(),.0);
		Exception e = null;
		try {
			monster.increaseAttack(5);
		} catch (Exception e2) {
			e = e2;
		}
		assertNull(e);
		try {
			monster.increaseAttack(-5);
		} catch (Exception e2) {
			e = e2;
		}
		assertTrue(e instanceof IllegalArgumentException);
		
	}
	

	@Test
	public void testGetdefence() {
		double defence = monster.getDefence();
		assertEquals("Unexpected value for monster.getdefence():" + monster.getDefence(), this.defence, defence,.1);
	}
	
	@Test
	public void testDecreaseDefence(){
		double decDef = monster.decreaseDefence(5);
		assertEquals("Unexpected value for monster.decreaseAttack():" + monster.getDefence(), 47, decDef,.0);
		decDef = monster.getMinDefence();
		assertEquals(decDef, monster.decreaseDefence(100),.0);
		Exception e = null;
		try{
			monster.decreaseDefence(5);
		} catch (Exception e2) {
			e = e2;
		}
		assertNull(e);
		
		try {
			monster.decreaseDefence(-5);
		} catch (Exception e2) {
			e = e2;
		}
		assertTrue(e instanceof IllegalArgumentException);
	}
	
	@Test
	public void testIncreaseDefence(){
		double defence = monster.increaseDefence(5);
		assertEquals("Unexpected value for monster.increaseDefence() 'normal':" + monster.getDefence() ,defence, 57,.0);
		//Test getting > MAX_ATT
		defence = monster.increaseDefence(100);
		assertEquals("Unexpected value for monster.increaseAttack() '>MAX_ATTACK':" + monster.getDefence(), defence, monster.getMaxDefence(),.0);
		Exception e = null;
		try {
			monster.increaseDefence(5);
		} catch (Exception e2) {
			e = e2;
		}
		assertNull(e);
		try {
			monster.increaseDefence(-5);
		} catch (Exception e2) {
			e = e2;
		}
		assertTrue(e instanceof IllegalArgumentException);
	}

	@Test
	public void testGetType() {
		assertTrue("Unexpected value for monster.getType():" + monster.getType(), monster.getType() instanceof IType);
		
	}


}
