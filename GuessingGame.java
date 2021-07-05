import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    static final Scanner input = new Scanner(System.in);
    static final Random random = new Random();

    static ArrayList<Integer> scores = new ArrayList<Integer>();

    static boolean guessCorrect = false;

    static void checkGuess(int guess, int number) {
        if (number == guess) {
            int tries = scores.get(scores.size() - 1);

            System.out.println("----------");
            System.out.println("Congratulations! You've guessed it! +1 point");

            String tryNumber = tries == 1 ? "try" : "tries";
            System.out.println("You took " + tries + " " + tryNumber + "!");

            System.out.println("Score: " + scores.size());

            double total = 0;
            for (int i = 0; i < scores.size(); i++) {
                total += scores.get(i);
            }
            double avg = Math.round(total / scores.size() * 100) / 100.0;

            System.out.println("Average Number of Tries: " + avg);
            System.out.println("----------");

            guessCorrect = true;
        } else if (number > guess) {
            System.out.println("Guess higher! +1 try");
        } else {
            System.out.println("Guess lower! +1 try");
        }
    }

    static void playGame() {
        scores.add(1);
        final int number = random.nextInt(10) + 1;

        System.out.println("I am thinking of an integer from 1 through 10.");

        for (;;) {
            System.out.print("Input your guess here >>> ");

            try {
                int guess = input.nextInt();

                if (guess < 1 || guess > 10) {
                    System.out.println("Invalid range! Please input an integer from 1 through 10.");
                } else {
                    checkGuess(guess, number);
                    if (guessCorrect) {
                        guessCorrect = false;
                        break;
                    }
                    int tries = scores.get(scores.size() - 1);
                    ++tries;
                    scores.set(scores.size() - 1, tries);
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input an integer!");
                input.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        playGame();
        for (;;) {
            System.out.print("Would you like to play again? Please input either 'Y' or 'N' >>> ");

            input.nextLine();
            String again = input.nextLine();

            while (!(again.equals("N") || again.equals("Y"))) {
                System.out.print("Sorry, I didn't catch that. Please input either 'Y' or 'N' >>> ");
                again = input.nextLine();
            }
            if (again.equals("N")) {
                break;
            }
            playGame();
        }

        input.close();
    }
}