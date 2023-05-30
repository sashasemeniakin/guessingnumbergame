import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //picks random
        Random random = new Random();
        //scanner
        Scanner scanner = new Scanner(System.in);

        //they have to guess 3 numbers out of 5
        //generate 5 numbers (the array)
        int[] randomNumbers = new int[5];
        for(int i=0; i<randomNumbers.length; i++) {
            randomNumbers[i] = random.nextInt(100) +1;
            System.out.println(randomNumbers[i]);
        }

        //store guessContainer in array list
        List<RandomGuess> guessList = new ArrayList<RandomGuess>();
        for(int i=0; i<randomNumbers.length; i++) {
            //object instantiation
            guessList.add(new RandomGuess(randomNumbers[i]));
        }

        //rounds won starts with 0
        int roundsWon = 0;
        for(int i=0; i<guessList.size(); i++) {
            RandomGuess currentGuess = guessList.get(i);

            //says the round and adds 1 to what the previous round was
            System.out.println("Welcome to round " + (i +1));
            System.out.println("Number to guess " + currentGuess.randomNumber);
            //for every one of guesses you get 5 tries
            boolean roundSuccessful = false;
            for (int tryCount = 0; tryCount < 5; tryCount++) {
                //method
                roundSuccessful = playRound(scanner, currentGuess, roundsWon);
                if(roundSuccessful) {
                    roundsWon++;
                }
            }
            if(!roundSuccessful) {
                //if the rounds won is less than 3 than the person lost
                if(roundsWon < 3) {
                    System.out.println("Sorry, you lost!  Better luck next time!");
                } else {
                    System.out.println("Sorry, you lost this round!");
                }
            }
            //if the number of rounds won is equal to 3 then break out of loop
            if(roundsWon == 3) {
                break;
            }
        }

    }
    //returns only true or false (boolean)
    public static boolean playRound(Scanner scanner, RandomGuess currentGuess, int roundsPreviouslyWon) {
        System.out.println("Please enter your guess. (number between 1 and 100)");
        int guess = scanner.nextInt();
        while (guess < 1 || guess > 100) {
            //keep looping until there's a valid guess number between 1 and 100
            System.out.println("guess needs to be between 1 and 100. please enter your guess again.");
            guess = scanner.nextInt();
        }
        //how to win the game
        if (currentGuess.isCorrect(guess)) {
            roundsPreviouslyWon++;
            if (roundsPreviouslyWon < 3) {
                System.out.println("Congratulations, you won this round!");
            } else {
                System.out.println("Congratulations, you won the game!");
            }
           return true;
            //this is if the number guessed is incorrect
        } else {
            if (currentGuess.isHigher(guess)) {
                System.out.println("oops! that is incorrect. The number is lower than that, please guess again.");
            } else {
                System.out.println("oops! that is incorrect. The number is higher than that, guess again.");
            }
        }
        return false;
    }
}