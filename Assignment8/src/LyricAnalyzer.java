import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LyricAnalyzer {
    private static HashMap<String, ArrayList<Integer>> lyricMap = new HashMap<>();


    public static void read(File file) throws IOException {
        String line;
        int wordPosition = 1;
        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);
        while ((line = br.readLine()) != null) {
            String[] wordTokens = line.split(" ");

            for (int i = 0; i < wordTokens.length; i++) {
                if (i == wordTokens.length - 1) {
                    add(wordTokens[i], -wordPosition);
                } else {
                    add(wordTokens[i], wordPosition);
                }
                wordPosition++;
            }
        }
    }

    private static void add(String lyricWord, int wordPosition) {
        ArrayList<Integer> wordPositionList = new ArrayList<>();
        if (lyricMap.containsKey(lyricWord)) {
            ArrayList associatedList = lyricMap.get(lyricWord);
            associatedList.add(wordPosition);
        } else {
            wordPositionList.add(wordPosition);
            lyricMap.put(lyricWord, wordPositionList);
        }
    }


    public static void displayWords() {
        Set<String> wordSet = lyricMap.keySet();
        List<String> wordList = new ArrayList(wordSet);
        Collections.sort(wordList);
        for (String eachWord : wordList) {
            System.out.print(eachWord + ":");
            String position = lyricMap.get(eachWord).toString();
            position = position.replace("[", "");
            position = position.replace("]", "");
            System.out.println(position);
        }

    }

    public static void writeLyrics(File file) throws IOException {
        int arraySize = 0;
        FileWriter fwriter = new FileWriter(file);
        Set<String> lyricSet = lyricMap.keySet();
        int sizeCount = 0;
        Iterator iteratorObject = lyricSet.iterator();
        while (sizeCount < lyricSet.size()) {
            ArrayList<Integer> eachList = lyricMap.get(iteratorObject.next());
            arraySize += eachList.size();
            sizeCount++;
        }
        String[] lyricArray = new String[arraySize + 1];
        for (int i = 1; i < lyricArray.length; i++) {
            lyricArray[i] = "";
        }

        for (String lyricToBeAdded : lyricSet) {
            Iterator itrObject = lyricMap.get(lyricToBeAdded).iterator();
            while (itrObject.hasNext()) {
                int index = (int) itrObject.next();
                if (index < 0) {
                    index *= (-1);
                    lyricArray[index] = lyricToBeAdded + " " + "\r\n";
                } else {
                    lyricArray[index] = lyricToBeAdded + " ";
                }
            }
        }
        for (int j = 1; j < lyricArray.length; j++) {
            fwriter.write(lyricArray[j]);
        }
        fwriter.close();
    }

    public static int count() {
        return lyricMap.size();
    }

    public static String mostFrequentWord() {
        int largestList = 1;
        String mostFrequentWord = "";
        Set<String> lyricSet = lyricMap.keySet();
        lyricSet.stream().sorted().collect(Collectors.toList());
        for (String eachLyric : lyricSet) {
            ArrayList<Integer> eachList = lyricMap.get(eachLyric);
            if (eachList.size() > largestList) {
                largestList = eachList.size();
                if (!eachLyric.equals(mostFrequentWord)) {
                    mostFrequentWord = mostFrequentWord.replace(mostFrequentWord, eachLyric);
                }
            } else {
                continue;
            }
        }
        return mostFrequentWord;

    }

    public static void main(String[] args) throws IOException {
        File fileRead = new File("E:\\IdeaProjects\\Java_Assignment8\\src\\test2.txt");
        File fileWrite = new File("E:\\IdeaProjects\\Java_Assignment8\\src\\test2Result.txt");
        read(fileRead);
        displayWords();
        System.out.println("The total number of unique words in the map are : " + count());
        writeLyrics(fileWrite);
        System.out.println("The most Frequently used word is " + mostFrequentWord());
    }


}
