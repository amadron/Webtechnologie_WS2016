package pokeklon.model;

public interface IItem {
	/**
	 * Get the items name
	 * @return items name
	 */
	String getName();
	
	/**
	 * Get the health value
	 * @return health value
	 */
	int getHealth();
	
	/** 
	 * Get the attack value
	 * @return attacks value
	 */
	int getAttack();
	
	/**
	 * Get the defence value
	 * @return defence value
	 */
	int getDefence();
}
