package pokeklon.model;

public interface IAttack {
	
	/**
	 * Get the name of the attack.
	 * @return the Attacks name.
	 */
	String getName();
	
	/**
	 * Get the attacks precission value.
	 * @return the precission value.
	 */
	double getPrecission();
	
	/**
	 * Get the strength value.
	 * @return the strength.
	 */
	double getStrength();
	
	/**
	 * Get the attacks type.
	 * @return attacks type.
	 */
	IType getType();
	
	/**
	 * Not implemented yet.
	 * Checks if the attack has a special state.
	 * @return True if the attack has a special state.
	 */
	boolean hasAnnormaly();
}
