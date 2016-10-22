package pokeklon.model.impl.item;

import pokeklon.model.IItem;

public class XtraAttack implements IItem {

	private static final int DEFENCE = 0;
	private static final int ATTACK = 5;
	private static final int HEALT = 0;

	@Override
	public String getName() {
		return "X-Attack";
	}
	
	@Override
	public int getHealth() {
		return HEALT;
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
