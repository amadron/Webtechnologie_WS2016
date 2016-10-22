package util.random;

import java.util.Random;

public final class GlobalRandom {
	
	private GlobalRandom() {}
	
	private static Random random = new Random();
	
	public static int getRandom(){
		return random.nextInt();
	}
	
	public static int getNumber(int amount){
		return random.nextInt(amount);
	}
	
	public static void setSeed(int seed){
		random = new Random(seed);
	}
	
	public static void reset(){
		random = new Random();
	}
}
