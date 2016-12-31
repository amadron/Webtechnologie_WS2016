package pokeklon.controller.impl;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import pokeklon.controller.IMonsterFactory;
import pokeklon.model.IAttack;
import pokeklon.model.IMonster;
import pokeklon.model.IType;
import pokeklon.model.impl.Attack;
import pokeklon.model.impl.Monster;
import pokeklon.model.impl.types.TypeFire;
import pokeklon.model.impl.types.TypeNormal;
import pokeklon.model.impl.types.TypePlant;
import pokeklon.model.impl.types.TypeWater;
import util.importData.GetMonsterAndAttackList;

public class MonsterFactory implements IMonsterFactory{


	private static final int ONE = 1;
	private static final int ZERO = 0;
	private static final int FIVE = 5;
	private static final int FOUR = 4;
	private static final int THREE = 3;
	private static final int TWO = 2;
	private List<List<String>> monsterAll;
	private List<List<String>> attackAll;
	
	public MonsterFactory() {
		monsterAll = GetMonsterAndAttackList.getMonsterList();
		attackAll = GetMonsterAndAttackList.getAttackList();
		
	}
	
	@Override
	public List<IMonster> getMonsterListFull(){
		int[] n = new int[monsterAll.size()];
		for (int i = 0; i < monsterAll.size(); i++) {
			n[i] = i;
		}
		return getMonsterList(n);
	}
	
	@Override
	public List<List<String>> getAttackListFull(){
		return attackAll;
	}
	
	@Override
	public List<IMonster> getMonsterList(int[] n) {
		List<IMonster> monsterUsed = new LinkedList<>();
		monsterUsed = new LinkedList<>();
		for(int i = 0; i < n.length; i++) {
			if(n[i] < 0){
				continue;
			}
			List<String> tmp = monsterAll.get(n[i]);
			monsterUsed.add(new Monster(tmp.get(ZERO),
					getType(tmp.get(1)),
					Double.parseDouble(tmp.get(TWO)), 
					Double.parseDouble(tmp.get(THREE)), 
					Double.parseDouble(tmp.get(FOUR)),
					getAttackList(tmp.subList(FIVE, tmp.size()))));
		}
		return monsterUsed;
	}
	
	private IType getType(String type) {
		IType tmp;
		if(type.equalsIgnoreCase("Pflanze"))
			tmp = new TypePlant();
		else if(type.equalsIgnoreCase("Feuer"))
			tmp = new TypeFire();
		else if(type.equalsIgnoreCase("Wasser"))
			tmp = new TypeWater();
		else
			tmp = new TypeNormal();
		System.out.println("Got type: " + type + ", set type: " + tmp.getName());
		return tmp;
	}

	@Override
	public List<IAttack> getAttackList(List<String> att) {
		List<IAttack> attackUsed = new LinkedList<>();
		for (String currAtt : att) {
			if(currAtt.equals("-") || Integer.parseInt(currAtt) < 0){
				continue;
			}
			List<String> tmp = attackAll.get(Integer.parseInt(currAtt) - 1);
			String anormaly = hasAnormaly(tmp);
				attackUsed.add(new Attack(tmp.get(ZERO),
					Double.parseDouble(tmp.get(THREE)),
					Double.parseDouble(tmp.get(TWO)), 
					getType(tmp.get(ONE)),
					anormaly));
		}
		return attackUsed;
	}

	private String hasAnormaly(List<String> tmp) {
		return tmp.get(FOUR);
	}
	@Override
	public String getImagePath(IMonster monster){
		int i = 0;
		for (List<String> tmpMonster : monsterAll) {
			i++;
			if(tmpMonster.get(0).equals(monster.getName())){
				break;
			}
		}
		return "app/pokeklon/data/MonsterPics/" + new DecimalFormat("00").format(i) + ".jpg";
	}
	
	@Override
	public String getImagePath(IMonster monster, String s){
		int i = 0;
		for (List<String> tmpMonster : monsterAll) {
			i++;
			if(tmpMonster.get(0).equals(monster.getName())){
				break;
			}
		}
		return "app/pokeklon/data/MonsterPics/" + new DecimalFormat("00").format(i) + s + ".jpg";
	}
	
	


	@Override
	public List<String> getAllImagePaths(List<List<String>> monster) {
		List<String> tmpLst = new LinkedList<String>();
		for (int i = 0; i < monster.size(); i++) {
			String tmp = "app/pokeklon/data/MonsterPics/" + new DecimalFormat("00").format(i) + ".jpg";
			tmpLst.add(tmp);
		}
		return tmpLst;
	}
	
	@Override
	public int getMonsterNumber(String name){
		for (List<String> list : monsterAll) {
			if(list.get(0).equals(name)){
				return monsterAll.indexOf(list);
			}
		}
		return -1;
	}
	
}
