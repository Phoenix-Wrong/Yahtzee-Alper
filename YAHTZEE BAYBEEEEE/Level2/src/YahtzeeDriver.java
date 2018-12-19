import java.util.Scanner;

public class YahtzeeDriver
{
	/*
	1. Creates a new instance of the YahtzeeGame class
	2. Calls the playGame method on the Yahtzee object
	3. Asks user if they would like to play again
	4. When the user is done playing, prints the number of games played, min, max, and average score
	*/
	public static void main(String [] args)
	{		Boolean repeat = true;
			Scanner scanneyBoy = new Scanner(System.in);
		int maxScore = 0;
		int minScore = 999;
		double totalScore = 0;
		double gamesPlayed = 0;
	while (repeat)
	{
			int score;
			YahtzeeGame myGame=new YahtzeeGame();
			System.out.println("Welcome to Aaron's Terrible Terrible Yahtzee Hell!");
			score=myGame.playGame();



			System.out.println("Would you like to go to another circle of Yahtzee Hell(TM)? (y/n)");
			String playAgain = scanneyBoy.nextLine();
			switch (playAgain) {
				case "y":
					repeat = true;
					break;
				case "n":
					repeat = false;
					System.out.println("Goodbye! You'll be back ;))))))");
					break;
					default:
						repeat = false;
					break;
			}
			if (score > maxScore){
				maxScore = score;
			}
			if (score < minScore){
				minScore = score;
			}
			totalScore = totalScore + score;
			gamesPlayed = gamesPlayed + 1;
		System.out.println("Max: " + maxScore + ", Min: " + minScore + ", Avg: " + totalScore/gamesPlayed);

			}



	}
}
