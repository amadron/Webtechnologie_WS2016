package pokeklon.controller;

import java.util.List;

import pokeklon.model.IAttack;
import pokeklon.model.IMonster;

public interface IMonsterFactory {

	/**
	 * Returns a List of Monsters.
	 * @param n is a list of Monster numbers. Which Monsters you want.
	 * @return the List of Monsters you demanded by the List.
	 */
	List<IMonster> getMonsterList(int[] n);

	/**
	 * Returns a List of attacks.
	 * @param att a List of Names of attacks you want to return.
	 * @return returns the Attack List you demanded.
	 */
	List<IAttack> getAttackList(List<String> att);

	/**
	 * Returns the Image pathes of a List of Monsters.
	 * @param a List of Monster names from which you want the Image pathes.
	 * @return a List of Image Pathes to the Monsters.
	 */
	List<String> getAllImagePaths(List<List<String>> monster);
	
	/**
	 * Returns the Image Path of a Monster.
	 * @param monster the Monster you want the Image path from.
	 * @return the Image path of the Monster.
	 */
	String getImagePath(IMonster monster);

	/**
	 * Get the Complete List of all Monsters.
	 * @return the List of all Monsters.
	 */
	List<IMonster> getMonsterListFull();

	/**
	 * Get the Number of a Monster of complete List.
	 * @param name the Monster you want the Number from.
	 * @return the Number of the Monster.
	 */
	int getMonsterNumber(String name);

	/**
	 * Get the Image path of an Monster.
	 * @param monster the Monster you want the Image from.
	 * @param s adds the String to the Files name (e. g. _big for big Images).
	 * @return returns the Path of the monsters image.
	 */
	String getImagePath(IMonster monster, String s);

	/**
	 * get the complete List of attacks.
	 * @return the complete List of attacks.
	 */
	List<List<String>> getAttackListFull();
}
