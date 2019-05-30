import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashMap<K, V> implements Map61B<K, V>{

    private int maxSize;
    private double loadFactor;
    private int size;
    private ArrayList<LinkedList<Entry<K, V>>> HashMap;
    private HashSet<K> keys = new HashSet<>();

    private class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    public MyHashMap() {
        this.maxSize = 16;
        this.loadFactor = 0.75;
        this.size = 0;
        this.HashMap = new ArrayList<>(this.maxSize);
    }

    public MyHashMap(int initialSize) {
        this.maxSize = initialSize;
        this.loadFactor = 0.75;
        this.size = 0;
        this.HashMap = new ArrayList<>(this.maxSize);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.maxSize = initialSize;
        this.loadFactor = loadFactor;
        this.size = 0;
        this.HashMap = new ArrayList<>(this.maxSize);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7FFFFFFF) % maxSize;
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        this.maxSize = 16;
        this.loadFactor = 0.75;
        this.HashMap = new ArrayList<>(this.maxSize);
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return (this.HashMap.get(hash(key)) != null);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    private void resize() {
        if ((double) size() / maxSize >= loadFactor) {
            //this.maxSize *= 2;
            ArrayList<Entry<K, V>> temp = new ArrayList<>(this.maxSize *=2);
            for (K key : keys) {
                temp.add(hash(key), )
            }
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (!containsKey(key)) {
            this.HashMap.add(hash(key), new LinkedList<>());
            this.HashMap.get(hash(key)).add(new Entry<>(key, value));
            size += 1;
        } else {

            this.HashMap.set(hash(key), new Entry<>(key, value));
        }
        this.keys.add(key);
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

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }
}
