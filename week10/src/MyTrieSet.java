import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet {
    private final Node root;
    private static final int R = 128; // ASCII
    private static class Node {
        boolean isKey;
        Map<Character, Node> map;
        private Node() {
            isKey = false;
            map = new HashMap<>();
        }

    }

    public MyTrieSet() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        int length = word.length();
        for(int i=0; i<length; i++) {
            char c = word.charAt(i);
            if(!curr.map.containsKey(c)) { // add char to the Trie
                curr.map.put(c, new Node());
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    public boolean contains(String word) {
        Node curr = root;
        int n = word.length();
        for(int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if(!curr.map.containsKey(c)) {
                return false;
            }
            if(i == n - 1 && !curr.isKey) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return true;
    }


    public List<String> keysWithPrefix(String prefix) {
        List<String> list = new ArrayList<>();
        Node node = findNode(prefix);
        if(node == null) {
            return list;
        }

        for(char c : node.map.keySet()) {
            colHelper(prefix + c, list, node);
        }
        return list;
    }

    // return node in the Trie of the last character of given prefix
    // example, for prefix "sa", it returns the node whose map contains 'a'
    private Node findNode(String prefix) {
        int n = prefix.length();
        Node curr = root;
        for(int i=0; i<n; i++) {
            char c = prefix.charAt(i);
            if(!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        return curr;
    }

    // collecting all the keys in a Trie
    public List<String> collect() {
        List<String> list = new ArrayList<>();

        colHelper("", list, root);
        return list;
    }

    private void colHelper(String s, List<String> list, Node node) {
        if(node.isKey) {
            list.add(s);
        }
        for(char c : node.map.keySet()) {
            colHelper(s + c, list, node.map.get(c));
        }
    }

    public String longestPrefixOf(String word) {
        int n = word.length();
        StringBuilder prefix = new StringBuilder();
        Node curr = root;
        for(int i=0; i<n; i++) {
            char c = word.charAt(i);
            if(!curr.map.containsKey(c))
                break;
            curr = curr.map.get(c);
            prefix.append(c);
        }
        return prefix.toString();
    }

    public static void main(String[] args) {

    }
}