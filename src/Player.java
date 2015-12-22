import java.util.ArrayList;

/**
 * Created by Franklin on 12/12/2015.
 */
public class Player {

    private ArrayList<String> acceptedWords = new ArrayList<>();

    public void addAcceptedWord(String word){
        if(!acceptedWords.contains(word)) acceptedWords.add(word);
    }

    public ArrayList<String> returnWords(){
        return acceptedWords;
    }

}
