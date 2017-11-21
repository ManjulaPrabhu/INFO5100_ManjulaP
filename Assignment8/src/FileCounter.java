import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileCounter {
    private int characterCount, wordCount, lineCount;

    public FileCounter() {
        this.characterCount = 0;
        this.wordCount = 0;
        this.lineCount = 0;
    }

    void read(Scanner in) throws IOException {
        while (in.hasNextLine()) {
            lineCount++;
            String line = in.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != ' ' && line.charAt(i) != '\n')
                    characterCount++;
            }
            wordCount += new StringTokenizer(line).countTokens();
        }
    }

    int getCharacterCount() {
        return characterCount;
    }

    int getWordCount() {
        return wordCount;
    }

    int getLineCount() {
        return lineCount;
    }
}
