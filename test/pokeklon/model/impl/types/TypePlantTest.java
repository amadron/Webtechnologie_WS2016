package pokeklon.model.impl.types;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pokeklon.model.IType;
import pokeklon.model.impl.types.TypePlant;
import util.TypeEnum;

public class TypePlantTest {

	IType test;
	@Before
	public void setUp() throws Exception {
		test = new TypePlant();
	}

	@Test
	public void testGetType() {
		assertEquals(TypeEnum.PLANT, test.getType());
	}

	@Test
	public void testGetWeak() {
		assertEquals(TypeEnum.FIRE, test.getWeak());
	}

	@Test
	public void testGetStrength() {
		assertEquals(TypeEnum.WATER, test.getStrength());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Plant", test.getName());
	}


}
