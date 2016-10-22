package pokeklon.model.impl.types;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pokeklon.model.IType;
import pokeklon.model.impl.types.TypeFire;
import util.TypeEnum;

public class TypeFireTest {

	IType test;
	@Before
	public void setUp() throws Exception {
		test = new TypeFire();
	}

	@Test
	public void testGetType() {
		assertEquals(TypeEnum.FIRE, test.getType());
	}

	@Test
	public void testGetWeak() {
		assertEquals(TypeEnum.WATER, test.getWeak());
	}

	@Test
	public void testGetStrength() {
		assertEquals(TypeEnum.PLANT, test.getStrength());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Fire", test.getName());
	}

}
