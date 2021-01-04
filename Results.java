package luckysixes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class Results {

	private TreeMap<String,ArrayList<Integer>> totalResults = new TreeMap<String,ArrayList<Integer>>();

	//default constructor
	public Results() {}

	public void storeResults(String playerName, ArrayList <Integer> diceResults){
		totalResults.put(playerName, diceResults);
	}

	public TreeMap<String,ArrayList <Integer>> getResults(){
		return this.totalResults;
	}

	public int calculateWinnings(ArrayList <Integer> result) {
		//frequency()takes in result as a collection in which the frequency of 6 is determined.
		int occurrences = Collections.frequency(result, 6);
		int amount = 0;
		switch(occurrences) {
		case 1:
			amount =  1;
			break;
		case 2:
			amount = 40;
			break;
		case 3:
			amount = 250;
			break;
		case 4:
			amount = 1500;
			break;
		case 5:
			amount = 10000;
			break;
		case 6:
			amount = 50000;
			break;
		default:
			amount = 0;
		}
		return amount;
	}
}
