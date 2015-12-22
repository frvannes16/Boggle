import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by Franklin on 12/11/2015.
 */
public class Board {
    public static final int DEFAULTSIZE = 4;
    private int SIZE = DEFAULTSIZE;
    public char[][] board;
    private Random random = new Random();
    private JFrame frame;


    public Board(){
        this(DEFAULTSIZE);
    }

    public Board(int size){
        this.SIZE = size;
        board = new char[size][size];
        init();
    }

    private void init(){

        //Make the jFrame
        frame = new JFrame("Unbeatable Boggle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(SIZE, SIZE);
        frame.setLayout(layout);


        JLabel[][] elementHolder = new JLabel[SIZE][SIZE];

        //Generate the letters.
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                char c = (char) (random.nextInt(90-65) + 65); //Letter between A and Z
                board[x][y] = c;
                elementHolder[x][y] =  new JLabel(String.valueOf(c));
            }
        }

        //Add the letters to the jFrame grid.

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                frame.getContentPane().add(elementHolder[y][x]);
            }
        }

        //add spacing between letters.
        layout.setHgap(20);
        layout.setVgap(20);

        //add a border spacing


        //add the letters to the frame.
        frame.pack();
        frame.setVisible(true);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                sb.append(board[x][y]);
                sb.append("  ");
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    public void printBoard(){
        System.out.println(this);
    }
}
