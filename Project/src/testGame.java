import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class testGame {
    static int chanceCount;
    static int lengthCounter;
    static String randomlySelectedWord = "";
    static String currentStageWord = "";

    public static void main(String[] args) {
        int playerOption;

        Scanner in = new Scanner(System.in);
        System.out.println("HANGMAN");
        System.out.println("1.Play");
        System.out.println("2.Terminate");

        playerOption = in.nextInt();
        switch (playerOption) {
            case 1:
                playGame();
                break;
            case 2:
                exit(0);
        }
        // gameWon(currentStageWord,randomlySelectedWord);
    }

    /**
     * This method randomly throws a word, gets input from player and calls
     * corresponding methods to handle further steps in the game
     **/
    private static void playGame() {
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> playedWords = new ArrayList<>();
        char input;
        chanceCount = 0;

        Scanner s = new Scanner(System.in);

        Hangman game = new Hangman(words);
        randomlySelectedWord = game.chooseWord(words);
        if (playedWords.contains(randomlySelectedWord)) {
            randomlySelectedWord = game.chooseWord(words);
        } else {
            int wordLength = randomlySelectedWord.length();

            int i = 0;
            do {
                currentStageWord += randomlySelectedWord.replaceAll(randomlySelectedWord, "-");
                i++;
            } while (i < randomlySelectedWord.length());
            System.out.println(currentStageWord);
            while (true) {
                System.out.println("Enter a character");
                input = s.next().charAt(0);
                currentStageWord = game.handleGuess(input, currentStageWord);
                if ((chanceCount == 8) || gameWon(currentStageWord, randomlySelectedWord)) {
                    gameOver();
                }
            }
        }
        playedWords.add(randomlySelectedWord);
    }

    /**
     * This method will return 'True' or 'False' depending whether the player has guessed the complete word correctly or not
     **/
    private static boolean gameWon(String currentStageWord, String randomlySelectedWord) {
        return currentStageWord.equals(randomlySelectedWord) ? true : false;
    }

    /**
     * This method exits the game
     **/
    private static void gameOver() {
        if (chanceCount == 8) {
            System.out.println("You have lost the game!");
            System.out.println("The word was - " + randomlySelectedWord);
        } else
            System.out.println("You win!");
        exit(0);
    }

}

