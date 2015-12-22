import java.util.HashMap;

/**
 * Created by Franklin on 12/11/2015.
 */
public class Trie {
    //Used as the dictionary storage type.

    Node root = new Node();

    private class Node {
        private boolean isWord;
        HashMap<Character, Node> children = new HashMap<>(8);

        private Node() {
            this(false);
        }

        private Node(boolean isWord) {
            isWord = isWord;
        }


    }


    public void insert(String word) {
        insert(word, root);
    }

    private void insert(String word, Node root) {
        if (word.length() == 0){
            return;
        }
        char c = word.charAt(0);
        if (!root.children.containsKey(c)) {//If the next character in the string doesn't exist.
            if (word.length() == 1){
                root.children.put(c, new Node(true)); //last word.
            }
            //add it to the node's children.
            root.children.put(c, new Node());
        }
        Node nextNode = root.children.get(c); //next node is first char.
        if (word.length() - 1 == 0) {
            nextNode.isWord = true;
            return; //when you've ran out of characters to add, leave the tree.
        } else {
            insert(word.substring(1), nextNode); //proceed through the tree.}
        }
    }

    public boolean contains(String word){
        return contains(word, root);
    }

    private boolean contains(String word, Node root){
        Node nextNode = root.children.get(word.charAt(0));
        if(word.length() == 1) return (nextNode != null);
        else {
            return contains(word.substring(1), nextNode);
        }
    }

    public boolean isWord(char[] word){
        StringBuilder sb = new StringBuilder();
        for (char c : word) {
            sb.append(c);
        }
        return isWord(sb.toString());
    }

    public boolean isWord(String word) {
        return isWord(word, root);
    }

    private boolean isWord(String word, Node root) {

        char c = word.charAt(0);
        Node nextNode;
        if (root.children.containsKey(c)) {
            nextNode = root.children.get(c);
            if (word.length() != 1) {
                //we haven't reached the end point, so proceed.
                return isWord(word.substring(1), nextNode);
            } else {
                //reaching end conditions.
                if (nextNode.isWord) return true; //We've found a word.
                else if (!nextNode.isWord) return false; //No where else to isWord, not a word.
            }
        }
        return false;
    }


}
