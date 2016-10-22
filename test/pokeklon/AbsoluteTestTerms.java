package pokeklon;

import java.util.Arrays;
import java.util.List;

import pokeklon.model.IAttack;
import pokeklon.model.IMonster;
import pokeklon.model.IType;
import pokeklon.model.impl.Attack;
import pokeklon.model.impl.Monster;
import pokeklon.model.impl.types.TypeFire;
import pokeklon.model.impl.types.TypeNormal;
import pokeklon.model.impl.types.TypePlant;
import pokeklon.model.impl.types.TypeWater;

public final class AbsoluteTestTerms {
	
	public AbsoluteTestTerms() {}

	public static final IType FIRE = new TypeFire();
	
	public static final IType WATER = new TypeWater();
	
	public static final IType PLANT = new TypePlant();
	
	public static final IType NORMAL = new TypeNormal();
	
	public static final IAttack ATTACK_NORMAL = new Attack("normal", 50, 100, NORMAL, null);
	
	public static final IAttack ATTACK_FIRE = new Attack("fire", 50, 100, FIRE, null);
	
	public static final IAttack ATTACK_WATER = new Attack("water", 50, 100, WATER, null);
	
	public static final IAttack ATTACK_PLANT = new Attack("plant", 50, 100, PLANT, null);
	
	public static final List<IAttack> FIRE_LIST = Arrays.asList(ATTACK_FIRE, ATTACK_NORMAL);
	
	public static final List<IAttack> WATER_LIST = Arrays.asList(ATTACK_WATER, ATTACK_NORMAL);
	
	public static final List<IAttack> PLANT_LIST = Arrays.asList(ATTACK_PLANT, ATTACK_NORMAL);
	
	public static final List<IAttack> NORMAL_LIST = Arrays.asList(ATTACK_NORMAL);
	
	public static final IMonster MONSTER_NORMAL = new Monster("normal", NORMAL, 50, 51, 52, NORMAL_LIST);
	
	public static final IMonster MONSTER_FIRE = new Monster("fire", FIRE, 50, 51, 52, FIRE_LIST);
	
	public static final IMonster MONSTER_WATER = new Monster("water", WATER, 50, 51, 52, WATER_LIST);
	
	public static final IMonster MONSTER_PLANT = new Monster("plant", PLANT, 50, 51, 52, PLANT_LIST);
	
	public static final List<IMonster> MONSTER_TEST_LIST = Arrays.asList(MONSTER_NORMAL, MONSTER_FIRE, MONSTER_PLANT, MONSTER_WATER);
	
	}
