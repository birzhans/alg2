import java.io.*;
import java.util.*;

public class Tries {
    private static final int R = 26;
    private Node root = new Node();

    private static class Node {
    private Object value;
    private Node[] next = new Node[26];
    }

    public void put(String key, int value) {
        root = put(root, key, value, 0);
    }

    private Node put(Node x, String key, int value, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) { x.value = value; return x; }
        char c = key.charAt(d);
        x.next[c - 97] = put(x.next[c - 97], key, value, d+1);
        return x;
    }
    
    public Object get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return x.value;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c-97], key, d+1);
    }
    
    private void find(String prefix) {
        Queue<String> queue = new LinkedList<>();
        Node x = get(root, prefix, 0);
        collect(x, prefix, queue);
        System.out.println(queue.size());
    }
    
    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null) return;
        if (x.value != null) q.add(prefix);
        for (char c = 0; c < R ; c++) {
            collect(x.next[c], prefix + c, q);
        }
    }


    public static void main(String[] args) {
        Tries t = new Tries();
        
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        
        for (int i = 0; i < n; i++) {
            String operation = scan.next();
            String word = scan.next();
            
            if (operation.equals("add")) { t.put(word, i); } 
            else { t.find(word); }
        }
    }
}