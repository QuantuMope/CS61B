import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * A Trie data structure that utilizes a hash map as its mapping structure.
 * @author Andrew Choi
 * Date: 06/03/2019
 */


public class MyTrieSet implements TrieSet61B {

    private Node root = new Node(false);

    private class Node {
        private boolean isKey;
        private HashMap<Character, Node> links;

        public Node(boolean blue) {
            isKey = blue;
            links = new HashMap<>();
        }
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root.links = new HashMap<>();
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {  return false; }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.links.containsKey(c)) { return false; }
            curr = curr.links.get(c);
        }
        return curr.isKey;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) { return; }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.links.containsKey(c)) {
                curr.links.put(c, new Node(false));
            }
            curr = curr.links.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        ArrayList<String> output = new ArrayList<>();
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.links.containsKey(c)) { return output; }
            curr = curr.links.get(c);
        }
        keysWithPrefixHelper(prefix, output, curr);
        return output;
    }

    private void keysWithPrefixHelper(String s, List<String> output, Node curr) {
        for (char entry : curr.links.keySet()) {
            if (curr.links.get(entry).isKey) {
                output.add(s+entry);
            }
            Node next = curr.links.get(entry);
            keysWithPrefixHelper(s+entry, output, next);
        }
    }


    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        MyTrieSet test = new MyTrieSet();
        test.add("test");
        test.add("tears");
        test.add("tomato");
        test.add("creature");
        test.add("pringles");
        List testList = test.keysWithPrefix("t");
    }
}
