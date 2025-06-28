package javaTester;

import java.util.Random;

public class TC_Random {

	public static void main(String[] args) {
		// Utilities = Tiện ích
		// Data type: Class/Interface/Collection/String/Float/..
		Random rand = new Random();
		System.out.println(rand.nextInt(1000));
		System.out.println(rand.nextInt(10));
	}
}
