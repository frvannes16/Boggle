import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Franklin on 12/11/2015.
 */
public class BoggleClient {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        //Create the dictionary
        Dictionary dictionary = new Dictionary(); //Make dictionaries static?
        //Create the board
        Board testBoard = new Board(5);
        System.out.println("Which difficulty would you like?" +
                "\n1. Easy" +
                "\n2. Medium" +
                "\n3. Hard" +
                "\n4. Insane");

        Opponent opponent;
        Difficulty opponentDifficulty = Difficulty.EASY;
        while (scanner.hasNextLine()) {
            if (scanner.hasNextLine()) switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    opponentDifficulty = Difficulty.EASY;
                    break;
                case 2:
                    opponentDifficulty = Difficulty.MEDIUM;
                    break;
                case 3:
                    opponentDifficulty = Difficulty.HARD;
                    break;
                case 4:
                    opponentDifficulty = Difficulty.INSANE;
                    break;
                default:
                    System.out.println("Bad input, type 1, 2, 3, 4.");
                    continue;
            }
            break; //breaks on successful assignment of difficulty.
        }
        //create the opponent
        opponent = new Opponent(testBoard, opponentDifficulty, dictionary);

        Player p1 = new Player();

        testBoard.printBoard();

        opponent.play(); //parses through board and collects words.

        //Possible words in the boggle grid
        ArrayList<String> words = opponent.returnWords();

        System.out.println("Enter every word you can find. You have two minutes!");
        System.out.println("Go!");
        int seconds = 120;
        int milisecondsPerSecond = 1000000000;
        long timeLimit = seconds * milisecondsPerSecond;


        long startTime = System.nanoTime(); //to monitor time.

        String response = "";
        while (!response.equals("QUIT") && System.nanoTime() - startTime < timeLimit) {
            if (System.nanoTime() - startTime < timeLimit) {
                if (scanner.hasNextLine()) {
                    response = scanner.nextLine();
                    if (response.equals("QUIT")) break; //leave the loop on quit or end of 60 seconds
                }
                if (words.contains(response.toLowerCase())) {
                    //Add the word to the player list if appropriate.
                    if (!p1.addAcceptedWord(response.toLowerCase()))
                        System.out.println("Word already found.");
                } else System.out.println(response + " isn't a match.");
            }
        }
        System.out.println("Time is up!");
        System.out.println("Player found: " + p1.returnWords().size() + ". Computer found: " + opponent.returnWords().size() + ".");

        System.out.println(getResults(p1, opponent));
    }

    private static String getResults(Player player, Opponent opp) {
        ArrayList<String> userWords = player.returnWords();
        StringBuilder results = new StringBuilder("Opponent            User\n\n");
        ArrayList<String> oppWords = new ArrayList<>();
        if (opp.getDifficulty().equals(Difficulty.INSANE)) {
            oppWords = opp.returnWords(); //All of the words.
        } else {
            Random randomInt = new Random();
            for (String word : opp.returnWords()) {
                //add according to difficulty: determined by probability
                int probability = randomInt.nextInt(60);
                if (probability < opp.getDifficulty().wordLength) oppWords.add(word);
            }
        }

        //print all words found into two columns.
        for (int i = 0; i < (oppWords.size() >= userWords.size() ? oppWords : userWords).size(); i++) {
            if (i < oppWords.size()) {
                results.append(oppWords.get(i));
            }
            if (i < userWords.size()) {
                String space = " ";
                for (int j = 0; j < (i < oppWords.size() ? (20 - oppWords.get(i).length()) : 20); j++) {
                    space += " ";
                }
                results.append(space);
                results.append(userWords.get(i));
                results.append('\n');
            }
        }

        //Find conflicts and scores
        results.append('\n');
        int sameCount = 0;
        int oppScore = 0;
        for (String oppWord : oppWords) {
            if (userWords.contains(oppWord)) sameCount += oppWord.length();
            else oppScore += oppWord.length();
        }

        int userScore = 0;
        for (String userWord : userWords) {
            userScore += userWord.length();
        }
        userScore -= sameCount;

        results.append('\n');

        results.append("Opponent score: ");
        results.append(oppScore);
        results.append("     ");
        results.append("User score: ");
        results.append(userScore);

        results.append('\n');
        results.append(userScore >= oppScore ? userScore == oppScore ? "Draw" : "User wins!" : "Computer Wins!");

        return results.toString();
    }


}
