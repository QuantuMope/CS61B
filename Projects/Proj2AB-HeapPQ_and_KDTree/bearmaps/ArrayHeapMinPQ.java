package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashSet;

/**
 *
 * @author Andrew Choi
 * Date: 06/02/2019
 */

public class ArrayHeapMinPQ<T extends Comparable<T>> implements ExtrinsicMinPQ<T> {

    private int size;
    private ArrayList<Entry<T>> heap;
    private int index;
    private HashSet<T> keys;

    private class Entry<T> {
        private T item;
        private double priority;
        public Entry(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQTest() {
        size = 0;
        heap = new ArrayList<>();
        index = 1;
        keys = new HashSet<>();
    }


    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority){
        if (contains(item)) { throw new IllegalArgumentException("Item already exists in the PQ."); }
        keys.add(item);
        heap.set(index, new Entry(item, priority));
        swim(index);
        index++;
        size++;
    }

    private void swim(int k) {
        if (heap.get(parent(k)).priority > (heap.get(k).priority)) {
            Entry temp = heap.get(k);
            heap.set(k, heap.get(parent(k)));
            heap.set(parent(k), temp);
            swim(parent(k));
        }
    }

    private int parent(int k) {
        return k/2;
    }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return keys.contains(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest(){
        if (size == 0) { throw new NoSuchElementException(); }
        return heap.get(1).item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        return null;
    }

    /* Returns the number of items in the PQ. */
    public int size() {
        return size;
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {

    }
}
