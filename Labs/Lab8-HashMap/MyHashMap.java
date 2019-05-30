import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Custom HashMap Class.
 * Utilizes an ArrayList with LinkedLists as chains in case of collision.
 * Each LinkedList pertains to a certain hashcode index and contains
 * Entries that have a key and value. Entries with the same key are overwritten.
 * Uses a HashSet as a key iterator.
 * @author Andrew Choi
 * Date: 05/29/2019
 */

public class MyHashMap<K, V> implements Map61B<K, V>{

    private int maxSize;
    private double loadFactor;
    private int size;
    private ArrayList<LinkedList<Entry<K, V>>> HashMap;
    private HashSet<K> keys = new HashSet<>();

    /** The base unit of each LinkedList. */
    private class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /** Helper function to add empty LinkedLists to each ArrayList index. */
    private void LinkedListInit() {
        for (int i = 0; i < this.maxSize; i++) {
            this.HashMap.add(i, new LinkedList<>());
        }
    }
    public MyHashMap() {
        this.maxSize = 16;
        this.loadFactor = 0.75;
        this.size = 0;
        this.HashMap = new ArrayList<>(this.maxSize);
        LinkedListInit();
    }

    public MyHashMap(int initialSize) {
        this.maxSize = initialSize;
        this.loadFactor = 0.75;
        this.size = 0;
        this.HashMap = new ArrayList<>(this.maxSize);
        LinkedListInit();
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.maxSize = initialSize;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.HashMap = new ArrayList<>(this.maxSize);
        LinkedListInit();
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        this.maxSize = 16;
        this.loadFactor = 0.75;
        this.size = 0;
        this.HashMap = new ArrayList<>(this.maxSize);
        LinkedListInit();
        keys = new HashSet<>();
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return this.keys.contains(key);
    }

    /** Hash function that takes an object's hashcode and returns the modulus.
     *  Dependent on the maxSize which allows for proper resizing. */
    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % maxSize;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        if (this.keys.contains(key)) {
            for (Entry entry : this.HashMap.get(hash(key))) {
                if (entry.key.equals(key)) {
                    return (V) entry.value;
                }
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /** Doubles the capacity of the data structure if N/M >= loadFactor.
     *  Remaps the previous entries using a temporary MyHashMap.  */
    private void resize() {
        if ((double) size() / maxSize >= loadFactor) {
            MyHashMap<K, V> temp = new MyHashMap<>(this.maxSize * 2);
            for (K key : keys) {
                temp.put(key, this.get(key));
            }
            this.HashMap = temp.HashMap;
            this.maxSize *= 2;
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (!containsKey(key)) {
            resize();
            this.HashMap.get(hash(key)).add(new Entry<>(key, value));
            size += 1;
        } else {
            for (Entry<K, V> entry : this.HashMap.get(hash(key))) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    break;
                }
            }
        }
        this.keys.add(key);
    }

    /** Uses the HashSet iterator to iterate over keys. */
    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet(){
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key){
        throw new UnsupportedOperationException("Remove is non supported in this class.");
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value){
        throw new UnsupportedOperationException("Remove is non supported in this class.");
    }
}
