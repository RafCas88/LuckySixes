package luckysixes;

import java.util.ArrayList;
import java.util.Random;

public class Dice {

	private ArrayList<Integer> throwResults = new ArrayList<Integer>();

	public ArrayList<Integer> getThrowResults() {
		return throwResults;
	}

	public void setThrowResults(ArrayList<Integer> throwResults) {
		this.throwResults = throwResults;
	}

	public Dice() {}

	//this method returns an ArrayList of Integer 
	public ArrayList<Integer> throwDice() {
		Random rand = new Random();
		//looping through 6 times, each time creating a new random number between 1 and 6
		for (int i = 0; i < 6; i++) {
			int result = rand.nextInt(6) + 1;
			this.throwResults.add(result);
		}
		return this.throwResults;
	}
}
