
package luckysixes;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Game {

	//instance variables
	private boolean debugMode;
	Results results = new Results();
	private int minNum;
	private int maxNum;

	//getters & setters
	public int getMinNum() {
		return minNum;
	}

	public void setMinNum(int minNum) {
		this.minNum = minNum;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public boolean isDebugMode() {
		return debugMode;
	}

	public void setDebugMode(boolean debugMode) {
		this.debugMode = debugMode;
	}

	//default constructor
	public Game() {}

	public void displayDebugModeMessage() {
		if (JOptionPane.showConfirmDialog(null, "Do you want to use debug mode?", "",
				JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
			this.setDebugMode(false);
		} else {
			this.setDebugMode(true);
		}
	}

	public int readInteger() {
		String intString = JOptionPane.showInputDialog("Please enter one of the menu options \n " + "\n "
				+ "1 - Play the game \n " + "2 - Collate the results \n " + "3 - Exit");
		//turns String into Integer
		return Integer.parseInt(intString);
	}

	public int selectOption() {
		int option = 0;
		//looping 10 times before moving to option 2. The user can choose to collate results before 10 though
		for (int loop = 0; loop < 10; loop++) {
			option = readInteger();
			if (option == 1) {
				String option1 = JOptionPane.showInputDialog("Please enter your name ");
				Play play = new Play(option1);
				ArrayList<Integer> diceResults = new ArrayList<Integer>();
				//if debugMode is selected....
				if (this.debugMode) {
					//looping 6 times before exiting the loop 
					for (int counter = 0; counter < 6; counter++) {
						////returns number entered by the user
						int input = readAndValidateNumber(1, 6);
						//adding user's input to ArrayList<Integer> diceResults
						diceResults.add(input);
					}
				} else {
					//no debugMode
					diceResults = play.returnDiceResults();
				}
				String message = play.getPlayerName() + " you rolled " + diceResults;
				JOptionPane.showMessageDialog(null, message);
				//playerName and diceResults stored in storeResults()
				results.storeResults(play.getPlayerName(), diceResults);
			} else {
				//option3 == break
				break;
			}
		}
		if (option == 1 || option == 2) {
			// when option 1 exits the loop after 10 times, the game automatically moves to this bit of code. Same happens if option 2 is selected
			JOptionPane.showMessageDialog(null, "Let's collate the results!");
			int totalWinnings = 0;
			// retrieve all keys/values in alphabetical order
			for (String tempPlayerName : results.getResults().keySet()) {
				//playerWinning holds calculateWinnings() as a value. This method takes in getResults().get(tempPlayerName) as a parameter
				// and returns the amount of money won by the players depending on how many sixes are rolled.
				int playerWinning = results.calculateWinnings(results.getResults().get(tempPlayerName));
				totalWinnings += playerWinning;
				JOptionPane.showMessageDialog(null, tempPlayerName + " you rolled "
						+ results.getResults().get(tempPlayerName) + " and win " + playerWinning + " pounds");
			}
			JOptionPane.showMessageDialog(null, calculateProfitOrLoss(totalWinnings));
		}
		return option;
	}

	public String calculateProfitOrLoss(int totalWinnings) {
		//the size of getResults() establishes how many players have played the game
		int bets = results.getResults().size();
		//1 bet = 1£
		int profits = bets - totalWinnings;
		String summary = "with " + bets + " bets and " + totalWinnings
				+ " paid out in prize money \n Lucky Sixes has made ";
		String profitsSummary = "";
		if (profits < 0) {
			profitsSummary = "a loss of " + profits + " pounds";
		} else if (profits > 0) {
			profitsSummary = "a profits of " + profits + " pounds";
		} else {
			profitsSummary = " no loss/profits ";
		}
		return summary + profitsSummary;
	}

	public int readAndValidateNumber(int minNum, int maxNum) {
		String message = JOptionPane.showInputDialog("Please enter a number between " + minNum + " and " + maxNum);
		int number = Integer.parseInt(message);
		while (number < minNum || number > maxNum) {
			message = "Oops! Numbers must be between " + minNum + " and " + maxNum + " PLEASE TRY AGAIN!";
			JOptionPane.showMessageDialog(null, message);
			message = JOptionPane.showInputDialog("Please enter a number between " + minNum + " and " + maxNum);
			number = Integer.parseInt(message);
		}
		return number;
	}

	public void play() {
		displayDebugModeMessage();
		selectOption();
	}
}
