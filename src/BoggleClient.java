import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Franklin on 12/11/2015.
 */
public class BoggleClient {
    public static void main(String args[]){
        //Create the dictionary
        Dictionary dictionary = new Dictionary(); //Make dictionaries static?
        //Create the board
        Board testBoard = new Board(5);
        Opponent opponent = new Opponent(testBoard, dictionary);
        Player p1 = new Player();

        testBoard.printBoard();

        opponent.play(); //parses through board and collects words.
        //words in the boggle grid
        ArrayList<String> words = opponent.returnWords();
        System.out.println("Enter every word you can find. You have 60 seconds!");
        System.out.println("Go!");
        long startTime = System.nanoTime();

        Scanner scanner = new Scanner(System.in);
        String response = "";
        while (!response.equals("QUIT") && System.nanoTime() - startTime < (long) 60 * 1000000000){
            if(scanner.hasNextLine()){
                response = scanner.nextLine();
                if (response.equals("QUIT")) break; //leave the loop on quit or end of 60 seconds
            }
            if (words.contains(response.toLowerCase())) p1.addAcceptedWord(response.toLowerCase());
            else System.out.println(response + " isn't a match.");
        }
        System.out.println("Time is up!");
        System.out.println("Player found: " + p1.returnWords().size() + ". Computer found: " + opponent.returnWords().size() + ".");


    }


}
