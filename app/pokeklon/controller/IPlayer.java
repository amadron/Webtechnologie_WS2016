package pokeklon.controller;

import java.util.List;

import pokeklon.model.IItem;
import pokeklon.model.IMonster;

public interface IPlayer {

	/**
	 * Get the Monster List of the Player.
	 * @return the Monster List of the Player
	 */
	List<IMonster> getMonsterList();

	/**
	 * Get the Item List of the Player.
	 * @return a list of the Players items.
	 */
	List<IItem> getItemList();

	/**
	 * Get the amount of Players items.
	 * @return the number of Items of the Player.
	 */
	int getNoOfItems();

	/**
	 * Removes an Item from Players inventory.
	 * @param item the Item you want to remove.
	 */
	void removeItem(IItem item);
	
	/**
	 * Add a Monster to the Player.
	 * @param monster as Number the Monster you want to add.
	 */
	void addMonster(int[] monster);

	/**
	 * Add a Monster to the Player.
	 * @param monster the Monster you want to add.
	 */
	void addMonster(IMonster monster);

	/**
	 * Removes a Monster from the Player.
	 * @param monster the Monster you want to remove.
	 */
	void removeMonster(IMonster monster);

}