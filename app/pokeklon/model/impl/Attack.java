package pokeklon.model.impl;

import pokeklon.model.IAttack;
import pokeklon.model.IType;

public class Attack implements IAttack {

	private String name;
	private double strength;
	private double precision;
	private IType type;
	private String anormaly;
	
	public Attack(String name, double strength, double precission, IType type, String anormaly) {
		this.name = name;
		this.strength = strength;
		this.precision = precission;
		this.type = type;
		this.anormaly = anormaly;
	}
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getPrecission() {
		return precision;
	}

	@Override
	public double getStrength() {
		return strength;
	}


	@Override
	public IType getType() {
		return type;
	}
	
	@Override
	public boolean hasAnnormaly(){
		return !anormaly.isEmpty();
	}
	
	

}
