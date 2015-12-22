/**
 * Created by Franklin on 12/11/2015.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Dictionary class is designed to scan and accumulate the
 */
public class Dictionary {
    private Scanner scanner;
    private boolean scanned = false;
    private Trie dictionary = new Trie();

    public Dictionary() {
        try {
            scanner = new Scanner(new File("wordsEN.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scan();
        }
    }

    private void scan() {
        if (!scanned) {
            while (scanner.hasNextLine()) {
                String next = scanner.nextLine();
                if (!next.isEmpty()) dictionary.insert(next);
            }
            scanned = true;
        }
    }

    public boolean contains(String word){
        return dictionary.contains(word);
    }

    public boolean isWord(String word){
        return (dictionary.isWord(word));
    }

    public static void main(String args[]) throws FileNotFoundException {
        Dictionary test = new Dictionary();
        test.scan();
        System.out.println("test: " + test.isWord("test"));
        System.out.println("hello: " + test.isWord("hello"));
        System.out.println("tent: " + test.isWord("tent"));
        System.out.println("tenting: " + test.isWord("tenting"));
        System.out.println("fhgillh: " + test.isWord("fhgillh"));

        Scanner scannerCheck = new Scanner(new File("wordsEN.txt"));
        boolean success = true;
        while(scannerCheck.hasNextLine()){
            String next = scannerCheck.nextLine();
            if(!next.isEmpty()) success = test.isWord(next);
        }
        System.out.println(success);
    }
}
