package pokeklon.model;

import java.util.List;

public interface IMonster {
	
	/**
	 * Returns the name of the monster.
	 * @return name The name of the monster.
	 */
	String getName();

	
	/**
	 * Returns the current Life.
	 * @return Returns the current life of the monster,  -1 if not yet set.
	 */
	double getLife();
	
	/**
	 * @param decLife The damage an attack is causing.
	 * @return Life after decrementation. 0 if the value will be lower than 0.
	 */
	double decreaseLife(double decLife);
	
	/**
	 * @param incLife the value to increase life value.
	 * @return the increased life value.
	 */
	double increaseLife(double incLife);
	
	/**
	 * Returns current defence value.
	 * @return current defence value.
	 */
	double getDefence();
	
	/**
	 * @param decDef the value to decrease the defence value.
	 * @return the decreased defence value
	 */
	double decreaseDefence(double decDef);
	
	/**
	 * @param incDef the value to increase defence value.
	 * @return the increased defence value.
	 */
	double increaseDefence(double incDef);
	
	/**
	 * Returns current attack value.
	 * @return current attack value.
	 */
	double getAttack();
	
	/**
	 * @param decAtt the value to decrease the attack value.
	 * @return the decreased attack value.
	 */
	double decreaseAttack(double decAtt);
	
	/**
	 * @param incAtt the value to increase attack value.
	 * @return the increased attack value.
	 */
	double increaseAttack(double incAtt);
	
	/**
	 * Returns the maximum life value of a monster.
	 * @return Returns the maximum Life.
	 */
	double getMaxLife();
	
	/**
	 * Returns the type of the monster.
	 * @return type of the monster. Null if not set.
	 */
	IType getType();
	
	/**
	 * Returns the maximum allowed defence value.
	 * @return maximum defence value.
	 */
	double getMaxDefence();
	/**
	 * Returns the minimal allowed defence value.
	 * @return minimal defence value.
	 */
	double getMinDefence();
	/**
	 * Returns the maximum allowed attack value.
	 * @return maximum attack value.
	 */
	double getMaxAttack();
	/**
	 * Returns the minimal allowed attack value.
	 * @return minimal attack value.
	 */
	double getMinAttack();
	
	/**
	 * Returns a list of all attacks a montser has got.
	 * @return list of all attacks.
	 */
	List<IAttack> getAttackList();

}
