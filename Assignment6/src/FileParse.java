import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileParse {
    public static void main(String[] argh) {
        String filepath;
        System.out.println("Enter the path");
        Scanner s = new Scanner(System.in);
        filepath = s.next();
        File file = new File(filepath);
        parse(file);

    }

    public static void parse(File file) {
        RandomAccessFile input = null;
        String line;

        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            return;
        } catch (IOException e) {
            System.err.println("Caught IOException:" + e.getMessage());
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (IOException e) {
                System.err.println("Caught IOException:" + e.getMessage());
            }
        }


    }

}

