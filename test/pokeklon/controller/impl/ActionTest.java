package pokeklon.controller.impl;

import static org.junit.Assert.*;

import java.lang.reflect.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pokeklon.AbsoluteTestTerms;
import pokeklon.controller.IAction;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import pokeklon.model.impl.item.Potion;
import pokeklon.model.impl.item.XtraAttack;
import pokeklon.model.impl.item.XtraDefence;

public class ActionTest {

	IAction action = new Action();
	IMonster source;
	IMonster target;
	IAttack attack;

	@Before
	public void setUp() throws Exception {
		source = AbsoluteTestTerms.MONSTER_NORMAL;
		target = AbsoluteTestTerms.MONSTER_NORMAL;
		attack = AbsoluteTestTerms.ATTACK_NORMAL;
	}

	@Test
	public void testAttack() {
		List<IMonster> mL = AbsoluteTestTerms.MONSTER_TEST_LIST;
		for (IMonster curr : mL) {
			for (IAttack att : curr.getAttackList()) {	
				for (IMonster tar : mL) {
					double res = action.attack(curr, att, tar);
					assertTrue("attack failed for:" +curr.getName() + 
							", " + tar.getName() + "Attackvalue = " + res, 
							0 <= res && res <= 13);
				}
			}
			
		}
		
	}
	
	@Test
	public void testSetFail() {
		int result;
		String methodName = "setFail";
		Class<Integer> classArgument = int.class;
		Object[] arg = new Object[]{new Integer(80)};
		//getting 1 as res
		result = (Integer) setFail(methodName, classArgument, arg);
		assertEquals("setFail test for getting 1 unsuccessfully! " + result, 1, result,0);
		arg = new Object[]{new Integer(81)};
		result = (Integer) setFail(methodName, classArgument, arg);
		assertEquals("setFail test for getting 0 unsuccessfully! " + result, 0, result,0);
		
		
	}

	private Object setFail(String methodName,
			Class<Integer> classArgument, Object[] arg) {
		Method setFail;
		Object res = 2;
		try {
			setFail = action.getClass().getDeclaredMethod(methodName, classArgument);
			setFail.setAccessible(true);
			res = setFail.invoke(action, arg);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Test
	public void testUseItem() {
		source.decreaseLife(20);
		IItem potion = new Potion();
		IItem attPlus = new XtraAttack();
		IItem defPlus = new XtraDefence();
		double life = source.getLife();
		double attack = source.getAttack();
		double defence = source.getDefence();
		//give potion to monster
		action.useItem(potion, source);
		assertEquals("Unexpected value for use potion: " + source.getLife(), life + 20, source.getLife(), 2);
		//give xtraAttack to monster
		action.useItem(attPlus, source);
		assertEquals("Unexpected value for use XtraAttack: " + source.getAttack(), attack + 5, source.getAttack(), 2);
		//give XtraDefence to monster
		action.useItem(defPlus, source);
		assertEquals("Unexpected value for use XtraDefence: " + source.getDefence(), defence + 5, source.getDefence(), 2);
		
	}

}
