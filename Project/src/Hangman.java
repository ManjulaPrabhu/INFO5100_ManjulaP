import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Hangman {
    testGame tg = new testGame();
    List<Character> alphabets = new ArrayList<>();
    List<Character> guessedLetters = new ArrayList<>();
    Random wordGenerator = new Random();
    String throwWord;

    /**
     * This constructor initializes the ArrayList with a set of 15 words and initializes the Alphabets list
     **/
    Hangman(ArrayList<String> words) {
        words.add("acme");
        words.add("aware");
        words.add("calif");
        words.add("breed");
        words.add("amateur");
        words.add("dacoity");
        words.add("diurnal");
        words.add("gruntle");
        words.add("marquee");
        words.add("calif");
        words.add("embezzling");
        words.add("accusation");
        words.add("zootherapy");
        words.add("collate");
        words.add("entertainment");
        initializaAlphabetList();
    }

    /**
     * This method initializes the Alphabet List with alphabets from a-z
     **/
    void initializaAlphabetList() {
        for (char ch = 'a'; ch <= 'z'; ch++) {
            alphabets.add(ch);
        }
    }

    /**
     * This method chooses a random word from the List of words
     **/
    String chooseWord(ArrayList<String> words) {
        int randomword = wordGenerator.nextInt(words.size());
        throwWord = words.get(randomword);
        return throwWord;

    }

    /**
     * This method validates whether the player has entered the correct letter in the word and
     * calls the appropriate function to perform further operation
     **/
    String handleGuess(char input, String currentStageWord) {
        for (char c = 'a'; c <= 'z'; c++) {
            if (c == input) {
                alphabets.remove(Character.valueOf(c));
                break;
            } else {
                continue;
            }
        }

        if (throwWord.indexOf(input) >= 0) {
            tg.lengthCounter++;
            guessedLetters.add(input);
            currentStageWord = displayWord(input, currentStageWord);
        } else if (!guessedLetters.contains(input)) {

            tg.chanceCount++;
            guessedLetters.add(input);
            printHangman(tg.chanceCount);

        }
        System.out.println("Remaining list of alphabets to use");
        for (char fromList : alphabets) {
            System.out.print(fromList + "\t");
        }
        System.out.println("\nUsed List of alphabets");
        for (char usedList : guessedLetters) {
            System.out.print(usedList + "\t");
        }
        System.out.println("\n" + currentStageWord);
        return currentStageWord;

    }


    /**
     * If the player has entered a wrong Character this method prints the Hangman in sequence based on the count of chances taken by the player
     **/

    private void printHangman(int chanceCount) {
        List<String> humanParts = Arrays.asList(
                "---------------" + "\n|             |" + "\n|             0" + "\n|" + "\n|" + "\n|" + "\n|" + "\n|" + "\n|--------------",
                "---------------" + "\n|             |" + "\n|             0" + "\n|             |" + "\n|" + "\n|" + "\n|" + "\n|" + "\n|--------------",
                "---------------" + "\n|             |" + "\n|             0" + "\n|             |" + "\n|          ---" + "\n|" + "\n|" + "\n|" + "\n|--------------",
                "---------------" + "\n|             |" + "\n|             0" + "\n|             |" + "\n|          --- ---" + "\n|" + "\n|" + "\n|" + "\n|--------------",
                "---------------" + "\n|             |" + "\n|             0" + "\n|             |" + "\n|          --- ---" + "\n|            /" + "\n|           /" + "\n|" + "\n|" + "\n|--------------",
                "---------------" + "\n|             |" + "\n|             0" + "\n|             |" + "\n|          --- ---" + "\n|            / \\ " + "\n|           /   \\ " + "\n|" + "\n|" + "\n|--------------",
                "---------------" + "\n|             |" + "\n|             0" + "\n|             |" + "\n|          --- ---" + "\n|            / \\ " + "\n|           /   \\ " + "\n|         --" + "\n|" + "\n|--------------",
                "---------------" + "\n|             |" + "\n|             0" + "\n|             |" + "\n|          --- ---" + "\n|            / \\ " + "\n|           /   \\ " + "\n|         --     --" + "\n|--------------");
        System.out.println(humanParts.get(chanceCount - 1));


    }

    /**
     * This method replaces "-" by the user input character wherever the character is in the String
     **/
    private String displayWord(char input, String currentStageWord) {
        StringBuilder sb = new StringBuilder(currentStageWord);
        for (int i = 0; i < throwWord.length(); i++) {
            if (throwWord.charAt(i) == input) {
                sb.setCharAt(i, input);
            }
        }
        System.out.println(currentStageWord = sb.toString());
        return currentStageWord;
    }

}
 
