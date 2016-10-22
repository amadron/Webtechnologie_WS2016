package pokeklon.controller.impl;

import pokeklon.controller.IAction;
import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import pokeklon.model.IType;
import pokeklon.model.impl.types.TypeNormal;
import util.random.*;

public class Action implements IAction {
	
	private static final float ONE_POINT_FIVE = 1.5f;
	private static final float TWO_FLOAT = 2f;
	private static final float ZERO_POIN_FIVE_FLOAT = 0.5f;
	private static final int TWO = 2;
	private static final int FIVE = 5;
	private static final int FIFTEEN = 15;
	private static final int FIFTY = 50;
	private static final int EIGHTY = 80;
	private static final int ONE_HUNDRED = 100;
	private float stab;
	private float typeStat;
	private static final int BOTTOM_RAND = 85;
	private IType targetType;
	private IType attackType;

	@Override 
	public double attack(IMonster sourceMonster, IAttack usedAttack,
			IMonster targetMonster) {
		IType sourceType;
		IMonster target;
		IAttack attack;
		
		target = targetMonster;
		attack = usedAttack;
		sourceType = sourceMonster.getType();
		this.targetType = target.getType();
		this.attackType = attack.getType();
		double fail;
		double basicDamage = attack.getStrength();
		double random = GlobalRandom.getNumber(FIFTEEN) + BOTTOM_RAND;
		int randFail = GlobalRandom.getNumber(ONE_HUNDRED);
		fail = setFail(randFail);
		setTypeStat();
		setStab(sourceType, attackType);
		
		double damage = ((((TWO/FIVE) + TWO) * basicDamage * 
				(sourceMonster.getAttack()/(FIFTY*targetMonster.getDefence())) + TWO) *
				random/ONE_HUNDRED * stab * typeStat) * fail;
		return Math.round(damage);
	}
	
	private int setFail(int rand) {
		if(rand > EIGHTY) {
			return 0;
		}
		return 1;
	}

	private void setTypeStat() {
		typeStat = 1f;
		if(!(attackType instanceof TypeNormal) && !(targetType instanceof TypeNormal)){
			checkTypeStrength();
			checkTypeWeak();
		}
		
	}

	private void checkTypeWeak() {
		if(targetType.getWeak().equals(attackType.getStrength())){
			typeStat = ZERO_POIN_FIVE_FLOAT;
		}
	}

	private void checkTypeStrength() {
		if(targetType.getStrength().equals(attackType.getWeak()) ){
			typeStat = TWO_FLOAT;
		}
	}

	private void setStab(IType sourceType, IType attackType) {
		if(sourceType.getType().equals(attackType.getType())) {
			stab = ONE_POINT_FIVE;
		} else {
			stab = 1;
		}
	}

	@Override
	public void useItem(IItem item, IMonster monster) {
		monster.increaseLife(item.getHealth());
		monster.increaseAttack(item.getAttack());
		monster.increaseDefence(item.getDefence());
	}

}
