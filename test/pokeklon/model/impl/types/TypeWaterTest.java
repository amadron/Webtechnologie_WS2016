package pokeklon.model.impl.types;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pokeklon.model.IType;
import pokeklon.model.impl.types.TypeWater;
import util.TypeEnum;

public class TypeWaterTest {
	
	IType test;

	@Before
	public void setUp() throws Exception {
		test = new TypeWater();
	}

	@Test
	public void testGetType() {
		assertEquals(TypeEnum.WATER, test.getType());
	}

	@Test
	public void testGetWeak() {
		assertEquals(TypeEnum.PLANT, test.getWeak());
	}

	@Test
	public void testGetStrength() {
		assertEquals(TypeEnum.FIRE, test.getStrength());
	}
	@Test
	public void testGetName(){
		assertEquals("Water", test.getName());
	}


}
