
import java.util.ArrayList;


/**
 * Created by Franklin on 12/11/2015.
 */
public class Opponent {

    private char[][] gameBoard;
    private Difficulty difficulty; //determines possible word length
    private ArrayList<String> foundWords = new ArrayList<>();
    private int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static final int NUM_DIRECTIONS = 8;

    private Dictionary dictionary;

    public Opponent(Board board, Dictionary d) {
        this(board.board, d);
    }

    public Opponent(Board board, Difficulty difficulty, Dictionary d) {
        this(board.board, difficulty, d);
    }

    public Opponent(char[][] board, Dictionary d) {
        this(board, Difficulty.EASY, d); //Default difficulty;
    }

    public Opponent(char[][] board, Difficulty difficulty, Dictionary d) {
        this.gameBoard = board;
        this.difficulty = difficulty;
        this.dictionary = d;
    }

    public boolean updateBoard(Board board) {
        this.gameBoard = board.board;
        foundWords.clear();
        return true;
    }

    public boolean updateDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
        return true;
    }

    public boolean newGame(Board board, Difficulty difficulty) {
        return updateBoard(board) && updateDifficulty(difficulty);
    }

    public void play() {

        //Start the word search at each letter.
        for (int x = 0; x < gameBoard.length; x++) {
            for (int y = 0; y < gameBoard.length; y++) {
                ArrayList<Pair> visited = new ArrayList<Pair>();
                recursiveWordSearch(x, y, "", visited);
            }
        }
    }

    private void recursiveWordSearch(int x, int y, String word, ArrayList<Pair> visited) {
        //Concatenate gameBoard[x][y] to word.
        if (visited.contains(new Pair(x, y))) return; //already visited piece, return.
        else visited.add(new Pair(x, y)); //add it to the visited coordinate list.

        word += gameBoard[x][y];
        word = word.toLowerCase();
        //Add to visited place.
        //Check to see if word is a valid word (check against your word list).
        if (dictionary.isWord(word) && !foundWords.contains(word)) foundWords.add(word); //If word, add to wordList
        else if (!dictionary.contains(word)) {
            visited.remove(visited.size() - 1); //pretend it never happened.
            return;
        }
    /*Check word list to see if any words contain current prefix. If not,
     then there's no point in continuing further (return). IE if AQZ isn't the
     start of any word at all in the list, no reason to keep adding letters, it's
     never going to make a word.  */

        //Otherwise recursively call this method moving left/right/up/down
        if (x + 1 != gameBoard.length) {
            recursiveWordSearch(x + 1, y, word, visited); //move right
            if (y + 1 != gameBoard.length) recursiveWordSearch(x + 1, y + 1, word, visited); //move to top right
            if (y - 1 != -1) recursiveWordSearch(x + 1, y - 1, word, visited); //move to bottom right
        }
        if (y + 1 != gameBoard.length) {
            recursiveWordSearch(x, y + 1, word, visited); //move up
            if (x - 1 != -1) recursiveWordSearch(x - 1, y + 1, word, visited); //move top left

        }
        if (x - 1 != -1) {
            recursiveWordSearch(x - 1, y, word, visited); //move left
            if (y - 1 != -1) recursiveWordSearch(x - 1, y - 1, word, visited); //move bottom left
        }
        if (y - 1 != -1) recursiveWordSearch(x, y - 1, word, visited); //move down

        visited.remove(visited.size()-1);
    /*You'll want to make sure that x-1, x+1, y-1 and y+1 are valid values before
     sending them. */


    }

    public ArrayList<String> returnWords(){
        return foundWords;
    }

    private class Pair {
        int x, y;

        private Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pair)) return false;
            Pair p = (Pair) obj;
            return (p.x == this.x && p.y == this.y);
        }
    }

}
