import java.util.Iterator;
import java.util.Set;

/**
 * Map represented by a binary search tree.
 * @author Andrew Choi
 * Date: 05/25/2019
 */

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V> {

    private class Node {
        private V value;
        private K key;
        private Node left;
        private Node right;
        private Node(K k, V val) {
            key = k;
            value = val;
        }
    }

    private int size;
    private Node root;

    public BSTMap() {
        root = null;
        size = 0;
    }

    public BSTMap(K key, V value) {
        root = new Node(key, value);
        size = 1;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        if (size() == 0) {
            size += 1;
            root = new Node(key, value);
            return;
        }
        insertHelp(key, value, root);
    }

    /* put method recursive helper */
    private Node insertHelp(K key, V value, Node curr) {
        if (curr == null) {
            size += 1;
            return new Node(key, value);
        }
        if (key.compareTo(curr.key) < 0) {
            curr.left = insertHelp(key, value, curr.left);
        } else if (key.compareTo(curr.key) > 0) {
            curr.right = insertHelp(key, value, curr.right);
        }
        return curr;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        return containsKeyHelper(key, root);
    }

    /* containsKey method recursive helper */
    private boolean containsKeyHelper(K key, Node curr) {
        boolean found = false;
        if (curr == null) {
            return false;
        }
        if (key.compareTo(curr.key) < 0) {
            found = containsKeyHelper(key, curr.left);
        } else if (key.compareTo(curr.key) > 0) {
            found = containsKeyHelper(key, curr.right);
        } else {
            return true;
        }
        return found;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        return getHelp(key, root);
    }

    /* get method recursive helper */
    private V getHelp(K key, Node curr) {
        V value;
        if (curr == null) {
            return null;
        }
        if (key.compareTo(curr.key) < 0) {
            value = getHelp(key, curr.left);
        } else if (key.compareTo(curr.key) > 0) {
            value = getHelp(key, curr.right);
        } else {
            value = curr.value;
        }
        return value;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() { return size; }

    /* Prints BSTMap elements in order of increasing key. */
    public void printInOrder() {
        printHelper(root);
    }

    /* printInOrder method recursive helper */
    private void printHelper(Node curr) {
        if (curr == null) {
            return;
        }
        printHelper(curr.left);
        System.out.println(curr.key);
        printHelper(curr.right);
    }


    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException("keyset is not supported with this class.");
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException("remove is not supported with this class.");
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException("remove is not supported with this class.");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("iterator is not supported with this class.");
    }
}
