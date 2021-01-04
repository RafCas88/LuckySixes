package luckysixes;
import java.util.ArrayList;

public class Play {

	//instance variable
	private String playerName;

	//instance of Dice Class 
	Dice dice = new Dice();

	//constructor 
	public Play(String playerName) {
		this.playerName = playerName;
	}

	//getter
	public String getPlayerName(){
		return this.playerName;
	}

	public ArrayList <Integer> returnDiceResults() {
		ArrayList <Integer> result = this.dice.throwDice();
		return result;
	}
}
