package pokeklon.controller;

import pokeklon.model.IAttack;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;

public interface IAction {
	
	/** Function that attacks the enemy Monster
	 * 
	 * @param sourceMonster Is the Monster that uses the Attack.
	 * @param attack Is the Attack that is selected/used.
	 * @param targetMonster Is the enemy Monster/ the Target of the Attack.
	 * @return The Damage of the attack.
	 */
	double attack(IMonster sourceMonster, IAttack attack, IMonster targetMonster);
	
	/** Function to apply an Item to an Monster.
	 * @param item the Item that are used. 
	 * @param monster the Monster on which the Item will be apply
	 */
	void useItem(IItem item, IMonster monster);
}
