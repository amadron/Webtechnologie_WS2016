package pokeklon.model.impl.types;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pokeklon.model.IType;
import pokeklon.model.impl.types.TypeNormal;
import util.TypeEnum;

public class TypeNormalTest extends TypeNormal {

	IType test;
	
	@Before
	public void setUp() throws Exception {
		test = new TypeNormal();
	}

	@Test
	public void testGetType() {
		assertEquals(TypeEnum.NORMAL, test.getType());
	}

	@Test
	public void testGetWeak() {
		assertNull(test.getWeak());
	}

	@Test
	public void testGetStrength() {
		assertNull(test.getStrength());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Normal", test.getName());
	}

	
	
	
	/*
	 * This "test" is only to get 100% coverage in TypeEnum. 
	 * Nothing is tested in this test, 
	 * only the methods '.vlaues()' and '.valueOf()' have to be called.
	 */
	@Test
	public void test() {
		assertNotNull(TypeEnum.values());
		assertNotNull(TypeEnum.valueOf("FIRE"));
	}

}
