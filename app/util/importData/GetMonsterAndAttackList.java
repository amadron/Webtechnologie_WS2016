package util.importData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public final class GetMonsterAndAttackList {
	
	private static final String MONSTER_PATH = "app/pokeklon/data/monster";
	private static final String ATTACK_PATH = "app/pokeklon/data/attack";
	
	private static List<List<String>> monster = new LinkedList<List<String>>();
	private static List<List<String>> attack = new LinkedList<List<String>>();
	private static Logger logger;
	
	static {
		getAll(MONSTER_PATH, monster);
		getAll(ATTACK_PATH, attack);
		Logger.getLogger(GetMonsterAndAttackList.class);
	}
	
	private GetMonsterAndAttackList() {}

	private static void getAll(String filename, List<List<String>> list) {
		BufferedReader bf = null;
		try {
			bf = new BufferedReader(new FileReader(filename));
			String line = null;
			while((line = bf.readLine()) != null){
				if(isCommentOrEmpty(line)){
					continue;
				}
				List<String> tmp = Arrays.asList((line.split("\t")));
				checkNull(tmp);
				list.add(tmp);
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}

	private static void checkNull(List<String> tmp) {
		while(tmp.indexOf("null") != -1){
			int test = tmp.indexOf("null");
			tmp.set(test, "-");
		}
		
	}

	private static boolean isCommentOrEmpty(String line) {
		if(line.startsWith("#") || line.isEmpty()){
			return true;
		}
		return false;
	}
	
	public static List<List<String>> getMonsterList(){
		return monster;
	}
	
	public static List<List<String>> getAttackList(){
		return attack;
	}
}
