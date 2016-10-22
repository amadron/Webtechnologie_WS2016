package pokeklon.model.impl.item;

import pokeklon.model.IItem;

public class Potion implements IItem{

	private static final int DEFENCE = 0;
	private static final int ATTACK = 0;
	private static final int HEALT = 20;

	@Override
	public String getName() {
		return "HP Trank";
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
