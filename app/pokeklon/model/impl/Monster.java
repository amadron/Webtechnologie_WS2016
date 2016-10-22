package pokeklon.model.impl;

import java.util.List;

import pokeklon.model.IAttack;
import pokeklon.model.IMonster;
import pokeklon.model.IType;

public class Monster implements IMonster {

	private String name;
	private double life;
	private double attack;
	private double defence;
	private IType type;
	private List<IAttack> attAll;
	private final double maxHealth;
	private final double maxDefence;
	private final double maxAttack;
	private final double minDefence;
	private final double minAttack;
	private static final double UPPER_LIMIT = 1.2;
	private static final double LOWER_LIMIT = 0.8;
	

	
	public Monster(String name, IType type, double life, double attack, double defence, List<IAttack> attList){
		setName(name);
		setLife(life);
		setType(type);
		setAttackValue(attack);
		setDefenceValue(defence);
		setAttacks(attList);
		maxHealth = life;
		maxDefence = defence * UPPER_LIMIT;
		maxAttack = attack * UPPER_LIMIT;
		minDefence = defence * LOWER_LIMIT;
		minAttack = attack * LOWER_LIMIT;
	}
	
	private void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	private void setLife(double life) {
		this.life = life;
	}

	@Override
	public double getLife() {
		return this.life;
	}
	
	@Override
	public double getMaxLife(){
		return this.maxHealth;
	}
	
	@Override
	public double decreaseLife(double decLife) {
		if(decLife < 0) {
			throw new IllegalArgumentException("Value to decrement have to be positive");
		}
		this.life -= decLife;
		if(this.life <=  0){
			this.life = 0;
		}
		return this.life;
	}
	
	@Override
	public double increaseLife(double incLife) {
		if(incLife < 0) {
			throw new IllegalArgumentException("Value to increment have to be positive");
		}
		if(this.life == 0){
			return 0;
		}
		this.life += incLife;
		if(this.life >  maxHealth){
			this.life = maxHealth;
		}
		return this.life;
	}

	public double getAttack() {
		return this.attack;
	}

	private void setAttackValue(double attack) {
		this.attack = attack;
	}
	
	@Override
	public double decreaseAttack(double decAtt) {
		if(decAtt < 0) {
			throw new IllegalArgumentException("Value to decrement have to be positive");
		}
		this.attack -= decAtt;
		if(this.attack < minAttack){
			this.attack = minAttack;
		} 
		return attack;
	}

	@Override
	public double increaseAttack(double incAtt) {
		if(incAtt < 0) {
			throw new IllegalArgumentException("Value to increment have to be positive");
		}
		this.attack += incAtt;
		if(this.attack > maxAttack){
			this.attack = maxAttack;
		}
		return attack;
	}

	public double getDefence() {
		return this.defence;
	}

	private void setDefenceValue(double defence) {
		this.defence = defence;
	}
	
	@Override
	public double decreaseDefence(double decDef) {
		if(decDef < 0) {
			throw new IllegalArgumentException("Value to decrement have to be positive");
		}
		this.defence -= decDef;
		if(this.defence < minDefence){
			this.defence = minDefence;
		}
		return defence;
	}

	@Override
	public double increaseDefence(double incDef) {
		if(incDef < 0) {
			throw new IllegalArgumentException("Value to increment have to be positive");
		}
		this.defence += incDef;
		if(this.defence > maxDefence){
			this.defence = maxDefence;
		}
		return defence;
	}

	private void setType(IType type) {
		this.type = type;
	}

	@Override
	public IType getType() {
		return this.type;
	}

	@Override
	public double getMaxDefence() {
		return maxDefence;
	}
	
	@Override
	public double getMaxAttack() {
		return maxAttack;
	}
	
	@Override
	public double getMinDefence() {
		return minDefence;
	}
	
	@Override
	public double getMinAttack() {
		return minAttack;
	}
	
	private void setAttacks(List<IAttack> attList) {
		this.attAll = attList;
	}
	
	public List<IAttack> getAttackList(){
		return attAll;
	}
}
