package pokeklon.model;

import util.TypeEnum;

public interface IType {

	
	/**
	 * The type itself/ current type.
	 * @return the type class's type.
	 */
	TypeEnum getType();
	
	/**
	 * The type of this types weak.
	 * @return the weak.
	 */
	TypeEnum getWeak();
	
	/**
	 * The type of this types strength.
	 * @return the strength.
	 */
	TypeEnum getStrength();

	/**
	 * Returns the typeName of the Monster
	 * @return typeName
	 */
	String getName();
}
