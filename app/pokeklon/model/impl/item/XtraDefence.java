package pokeklon.model.impl.item;

import pokeklon.model.IItem;

public class XtraDefence implements IItem {

	private static final int DEFENCE = 5;
	private static final int ATTACK = 0;
	private static final int HEALTH = 0;

	@Override
	public String getName() {
		return "X-Defence";
	}
	
	@Override
	public int getHealth() {
		return HEALTH;
	}

	@Override
	public int getAttack() {
		return ATTACK;
	}

	@Override
	public int getDefence() {
		return DEFENCE;
	}
	
}
