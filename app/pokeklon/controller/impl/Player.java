package pokeklon.controller.impl;

import java.util.LinkedList;
import java.util.List;

import pokeklon.controller.IMonsterFactory;
import pokeklon.controller.IPlayer;
import pokeklon.model.IItem;
import pokeklon.model.IMonster;
import pokeklon.model.impl.item.Potion;
import pokeklon.model.impl.item.XtraAttack;
import pokeklon.model.impl.item.XtraDefence;

public class Player implements IPlayer {
	private List<IMonster> monsterList;
	private List<IItem> itemList = new LinkedList<>();
	private IMonsterFactory monster = new MonsterFactory();
	
	public Player(){
		addItem(new Potion());
		addItem(new Potion());
		addItem(new XtraAttack());
		addItem(new XtraDefence());
	}
	
	@Override
	public void addMonster(int[] monster){
		int[] tmpList = monster;
		monsterList = this.monster.getMonsterList(tmpList);
	}
	
	@Override
	public void addMonster(IMonster monster){
		monsterList.add(monster);
	}
	
	@Override
	public List<IMonster> getMonsterList(){
		return monsterList;
	}

	@Override
	public void removeMonster(IMonster monster){
		int i = 0;
		for(IMonster mon: monsterList){
			if(mon.getName().equals(monster.getName())){
				monsterList.remove(i);
			}
			i++;
		}
	}

	private void addItem(IItem item){
		itemList.add(item);
	}

	@Override
	public List<IItem> getItemList(){
		return itemList;
	}
	
	@Override
	public int getNoOfItems(){
		return itemList.size();
	}
	
	@Override
	public void removeItem(IItem item){
		int i = 0;
		for (IItem tmp : itemList) {
			if(tmp.getName().equals(item.getName())){
				itemList.remove(i);
				break;
			}
			i++;
		}
	}
}
