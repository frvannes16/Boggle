/**
 * Created by Franklin on 12/11/2015.
 */
public enum Difficulty {
    EASY(3), MEDIUM(5), HARD(10), INSANE(20);
    public int wordLength;
    Difficulty(int maxWordLength) {
        wordLength = maxWordLength;
    }
}
