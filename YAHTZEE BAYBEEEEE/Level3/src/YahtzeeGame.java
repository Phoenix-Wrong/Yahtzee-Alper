import java.util.Scanner;

public class YahtzeeGame
{
	/* instance data should include the five yahtzee dice, a scoreboard, and a CONSTANT (static final) variable NUM_SIDES
	which should be set to six (the number of sides on a yahtzee die) */
private YahtzeeDie die1;
	private YahtzeeDie die2;
	private YahtzeeDie die3;
	private YahtzeeDie die4;
	private YahtzeeDie die5;
	public static final int NUM_SIDES = 6;
	private YahtzeeScorecard scoreboard;
	/* initializes the dice and scoreboard */
	public YahtzeeGame()
	{
		die1 = new YahtzeeDie(NUM_SIDES);
		die2 = new YahtzeeDie(NUM_SIDES);
		die3 = new YahtzeeDie(NUM_SIDES);
		die4 = new YahtzeeDie(NUM_SIDES);
		die5 = new YahtzeeDie(NUM_SIDES);
		scoreboard = new YahtzeeScorecard();
	}

	/* check to see if the game is over, and while the game is not over call the method takeTurn()
	once the game ends (hint: there are 13 turns in a game of Yahtzee), the method should print a
	final scorecard and return the grand total */
	public int playGame()
	{
		for(int enter = 0; enter < 13; ++enter) {
			this.takeTurn();
		}

		this.scoreboard.printScoreCard();
		return this.scoreboard.getGrandTotal();
	}

	/* 	1. call rollDice()
		2. call printDice()
		3. Ask the user if they are satisfied with their roll, or if they would like to roll again.
		   If they are not satisfied continue, otherwise call markScore()
		4. call chooseFrozen()
		5. call rollDice()
		6. call printDice()
		7. Ask the user if they are satisfied with their roll, or if they would like to roll again.
		   If they are not satisfied continue, otherwise call markScore()
		8. call chooseFrozen()
		9. call rollDice()
		10. call printDice()
		11. call markScore()
	*/
	private void takeTurn()
	{
		Scanner scanneyBoy = new Scanner(System.in);
		this.rollDice();
		this.rollDice();
		System.out.println("ROLL. THOSE. BOYS!!!: ");
		this.printDice();
		System.out.print("Accept or challenge your fate? (a/c): ");
		if (scanneyBoy.nextLine().equals("c")) {
			this.chooseFrozen();
			this.rollDice();
			System.out.println("Roll 2, Electric Boogaloo: ");
			this.printDice();
			System.out.print("Accept or challenge your fate? (a/c): ");
			if (scanneyBoy.nextLine().equals("c")) {
				this.chooseFrozen();
				this.rollDice();
				System.out.println("Your final roll: ");
				this.printDice();
			}
		}

		this.markScore();

	}

	/* Rolls all unfrozen dice.  Also resets all dice to the unfrozen state after the roll */
	private void rollDice()
	{
		if (!this.die1.isFrozen()) {
			this.die1.rollDie();
		} else {
			this.die1.unfreezeDie();
		}

		if (!this.die2.isFrozen()) {
			this.die2.rollDie();
		} else {
			this.die2.unfreezeDie();
		}

		if (!this.die3.isFrozen()) {
			this.die3.rollDie();
		} else {
			this.die3.unfreezeDie();
		}

		if (!this.die4.isFrozen()) {
			this.die4.rollDie();
		} else {
			this.die4.unfreezeDie();
		}

		if (!this.die5.isFrozen()) {
			this.die5.rollDie();
		} else {
			this.die5.unfreezeDie();
		}
	}

	/* Asks the user which dice should be frozen 1-5 (should be read in in one line) */
	private void chooseFrozen()
	{
		Scanner scanneyBoy = new Scanner(System.in);
		System.out.print("Make those dice reeeeeeeal cold (enter 1-5): ");
		String frozenDice = scanneyBoy.nextLine();
		if (frozenDice.indexOf("1") != -1) {
			this.die1.freezeDie();
		}

		if (frozenDice.indexOf("2") != -1) {
			this.die2.freezeDie();
		}

		if (frozenDice.indexOf("3") != -1) {
			this.die3.freezeDie();
		}

		if (frozenDice.indexOf("4") != -1) {
			this.die4.freezeDie();
		}

		if (frozenDice.indexOf("5") != -1) {
			this.die5.freezeDie();
		}
	}

	/* Should print the value of all five dice separated by a tab (\t) to the console */
	private void printDice()
	{
		System.out.print(this.die1.getValue() + "\t");
		System.out.print(this.die2.getValue() + "\t");
		System.out.print(this.die3.getValue() + "\t");
		System.out.print(this.die4.getValue() + "\t");
		System.out.println(this.die5.getValue() + "\t");
	}

	/* 1. Print a scoreboard
	   2. Ask the user where they would like to mark their score.
	   3. Call appropriate function
	   4. If false is returned the user entered an invalid number, so ask the user to try again	*/
	private void markScore()
	{
		boolean valid = false;
		Scanner scanneyBoy = new Scanner(System.in);

		do {
			this.scoreboard.printScoreCard();

			int x;
			do {
				System.out.println("Where would you like to place your dicey boys?");
				System.out.println("1. Ones \t7.  3 of Kind");
				System.out.println("2. Twos \t8.  4 of Kind");
				System.out.println("3. Threes \t9.  Full House");
				System.out.println("4. Fours \t10. Sm Straight");
				System.out.println("5. Fives \t11. Lg Straight");
				System.out.println("6. Sixes \t12. Yahtzee");
				System.out.println("\t\t13. Chance");
				System.out.print("Enter 1-13: ");
				x = scanneyBoy.nextInt();
				scanneyBoy.nextLine();
			} while(x > 13 || x < 0);

			switch(x) {
				case 1:
					valid = this.scoreboard.markOnes(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 2:
					valid = this.scoreboard.markTwos(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 3:
					valid = this.scoreboard.markThrees(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 4:
					valid = this.scoreboard.markFours(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 5:
					valid = this.scoreboard.markFives(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 6:
					valid = this.scoreboard.markSixes(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 7:
					valid = this.scoreboard.markThreeOfAKind(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 8:
					valid = this.scoreboard.markFourOfAKind(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 9:
					valid = this.scoreboard.markFullHouse(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 10:
					valid = this.scoreboard.markSmallStraight(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 11:
					valid = this.scoreboard.markLargeStraight(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 12:
					valid = this.scoreboard.markYahtzee(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
					break;
				case 13:
					valid = this.scoreboard.markChance(this.die1.getValue(), this.die2.getValue(), this.die3.getValue(), this.die4.getValue(), this.die5.getValue());
			}
		} while(!valid);{
		System.out.println("Try Again");
	}
	}
}