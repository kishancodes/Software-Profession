import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
	    userDescription(); // Display the program to the users
	    try { // wait till the user reads the description
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
             ex.printStackTrace();
        } 
		try (Scanner keyboard = new Scanner(System.in)) {
			int slots = numberOfSlots(keyboard); // Step 1. user input 2-200 slots.
			int zeros = numberOfZeroSlots(keyboard); // Step 2. user input label 0 or 00 with int between 0-2 
			int N = numberOfVisits(keyboard); // Step 3. user input of visits in casino 1-100,000
			int amount = amountSize(keyboard); // Step 4. user input of money per visit of step 3, 1-1,000,000
			int strategy = getStrategyNum(keyboard); // Step 5. get user input on strategies from the given three
			int fixedBet = strategy != 3 ? 0 : getFixedBet(keyboard, amount); // check if the user selected 3 in step 4 and then invoke  

            // create a list to store user input and machine output values.
			List<Integer> finalResults = new ArrayList<>(); 
			for (int i = 0; i<N; i++) {
				finalResults.add(visitCasino(amount, strategy, slots, zeros, fixedBet)); // Invoke Backend layer 1
			}
            // calculate data for after N visits
			long total = finalResults.stream().mapToInt(i -> i).sum(); 
			long amountAtRisk = (long) N * amount;
			double percentage = 100.0 * total / amountAtRisk;
			long biggestGain = finalResults.stream().mapToInt(i -> i - amount).max().orElse(0);
			long losses = finalResults.stream().mapToInt(i -> i).filter( i -> i == 0).count();
			double averageChange = finalResults.stream().mapToInt(i -> i - amount).average().orElse(0.0);
			long numLess =  finalResults.stream().mapToInt(i -> i).filter( i -> i < amount).count();
			long numMore =  finalResults.stream().mapToInt(i -> i).filter( i -> i > amount).count();
			long numSame =  finalResults.stream().mapToInt(i -> i).filter( i -> i == amount).count();
            
            // output N visits data
			System.out.println("Total number of dollars the user walked away with (absolute): $" + total);
			System.out.println("Total number of dollars the user walked away with (percentage): " + percentage + "%");

			System.out.println("Most dollars the user won: $" + biggestGain);
			System.out.println("Number of times the user finished with no money at all: " + losses);
			System.out.println("Average change: $" + averageChange);
			System.out.println("Number of times a visit finished with more money than when the user started: " + numMore);
			System.out.println("Number of times a visit finished with less money than when the user started: " + numLess);
			System.out.println("Number of times a visit finished with same money than when the user started: " + numSame);
			keyboard.nextLine(); // wait till the user press enter to exit.
		}
	}
	
	// Print descripton for the user
	static void userDescription(){
	    System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	    System.out.println("* This is homework 2 of CS 4500 FA 2021 developed by Tyrese Robinson and Kishan Bhakta. It is a *");
	    System.out.println("* Casino Roulette stimulator with some extra steps. These extra steps licludes strategies for   *");
	    System.out.println("* the users which include:                                                                      *");
	    System.out.println("*                                                                                               *");
	    System.out.println("* 1. Martingale Strategy: Users starts bet at $1. If they win, they quit. If they loose, the    *");
	    System.out.println("*                         bet doubles. Cycle continues till user wins or quits voluntarily.     *");
	    System.out.println("* 2. Random Strategy: Users starts bet at $1 or a random amount or their entire ssavings. Each  *");
	    System.out.println("*                     bet is equal to the one amount the user picked at first. Repeats 50 times *");
        System.out.println("*                     or till the user looses all the money.                                    *");
        System.out.println("* 3. Fixed Bet Strategy: Users starts bet at $1 to the amount they enter. User also enters      *");
        System.out.println("*                        fixed bet amount at the start(only one chance) for all the games.      *");
        System.out.println("*                        Repeats 50 times or till the user's money is less than the fiex amount.*");
        System.out.println("*                                                                                               *");
        System.out.println("* The resuts are calculated and displayed.                                                      *");
        System.out.println("*                                                                                               *");
        System.out.println("* BEWARE, DONT TRY THIS STUNT AT A REAL CASINO.                                                 *");
	    System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
	    return ;
	}
	
	
    // UI slots
	private static int numberOfSlots(Scanner scanner) {
		while (true) {
			try {
				System.out.print("\nPlease, enter number of slots [2-200]: ");
				int value = Integer.parseInt(scanner.nextLine());
				if (value < 2 || value > 200) {
					throw new NumberFormatException();
				}
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. A valid input will be 2 or 30 or 200. Please, try again.");
			}
		}
	}

    // UI 0 slots
	private static int numberOfZeroSlots(Scanner scanner) {
		while (true) {
			try {
				System.out.print("Please, enter number of '0' slots [0-2]: ");
				int value = Integer.parseInt(scanner.nextLine());
				if (value < 0 || value > 2) {
					throw new NumberFormatException();
				}
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. A valid input will be 0 or 1 or 2.Please, try again.");
			}
		}
	}

    //UI visits
	private static int numberOfVisits(Scanner scanner) {
		while (true) {
			try {
				System.out.print("How many times do you want to visit the casino [1-100000]: ");
				int value = Integer.parseInt(scanner.nextLine());
				if (value < 1 || value > 100000) {
					throw new NumberFormatException();
				}
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. A valid input will be 40 or 109 or 100000. Please, try again.");
			}
		}
	}

    // UI bet amount
	private static int amountSize(Scanner scanner) {
		while (true) {
			try {
				System.out.print("How many dollars you want to start with each time you simulate a visit to the casino [1-1000000]: ");
				int value = Integer.parseInt(scanner.nextLine());
				if (value < 1 || value > 1000000) {
					throw new NumberFormatException();
				}
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. A valid input will be 1 or 999 or 30. Please no decimal value. Please, try again.");
			}
		}
	}

    // UI strategy
	private static int getStrategyNum(Scanner scanner) {
		while (true) {
			try {
				System.out.println("Which strategy do you want to use?");
				System.out.println("1. Martingale");
				System.out.println("2. Random bet");
				System.out.println("3. Fixed bet");
				int value = Integer.parseInt(scanner.nextLine());
				if (value < 1 || value > 3) {
					throw new NumberFormatException();
				}
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please, try again.");
			}
		}
	}

    // UI fixed bet amount
	private static int getFixedBet(Scanner scanner, int max) {
		while (true) {
			try {
				System.out.print("What is your bet for fixed bet strategy [ $1 - $" + max + " ]: ");
				int value = Integer.parseInt(scanner.nextLine());
				if (value < 1 || value > max) {
					throw new NumberFormatException();
				}
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please, try again.");
			}
		}
	}

    // Backend layer 1
	private static int visitCasino(int amount, int strategy, int slots, int zeros, int fixedBet) {
		int currentAmount = amount;
		int currentBet;
		switch (strategy) { 
			case 1:
				currentBet = 1;
				while (currentAmount > 0) {
					currentAmount -= currentBet;
					if (playRouletteOnce(slots, zeros)) {  //invoke game and check if lost
						currentAmount += 2 * currentBet; //double the bet
						return currentAmount;
					}

					currentBet = Math.min(2 * currentBet, currentAmount);
				}
				return 0;
			case 2:
				Random random = new Random();
				for (int i = 0; i < 50 && currentAmount > 0; i++) { //bet till 50 times if happens
					currentBet = random.nextInt(currentAmount) + 1;  // bet random amount from $1 to what user input in step 4.
					currentAmount -= currentBet;
					if (playRouletteOnce(slots, zeros)) { //invoke game and check if lost
						currentAmount += 2 * currentBet; 
					}
				}
				return currentAmount;

			case 3:
				for (int i = 0; i < 50 && currentAmount > 0; i++) {
					currentBet = fixedBet;
					currentAmount -= currentBet;
					if (playRouletteOnce(slots, zeros)) { //invoke game and check if lost
						currentAmount += 2 * currentBet; 
					}
				}
				return currentAmount;

			default:
				throw new IllegalStateException();
		}
	}
    
    // Backend layer 1.1
	private static boolean playRouletteOnce(int slots, int zeros) {
		int spinResult = makeSpin(slots, zeros); // invoke spinning to get random number slot
		// we are winning (true is returned) iff:
		// 1. result is not zeros: spinResult <= slots
		// 2. result is Black (even)
		return spinResult <= slots && spinResult % 2 == 0;
	}
    
    // Backend layer 1.1.1
	private static int makeSpin(int slots, int zeros) {
		Random random = new Random();
		return random.nextInt(slots + zeros) + 1;
	}
}
