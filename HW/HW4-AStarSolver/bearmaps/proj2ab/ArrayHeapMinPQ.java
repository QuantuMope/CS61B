package bearmaps.proj2ab;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashSet;

/**
 * A Min Priority Queue that uses an ArrayList data structure resembling a binary tree.
 * @author Andrew Choi
 * Date: 06/02/2019
 */

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private int size;
    private ArrayList<Entry<T>> heap;
    private int index;
    private HashSet<T> keys;

    private class Entry<T> implements Comparable<Entry>{
        private T item;
        private double priority;
        private Entry(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
        T getItem() {
            return item;
        }

        private double getPriority() {
            return priority;
        }

        private void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(Entry other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((Entry) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

    public ArrayHeapMinPQ() {
        size = 0;
        heap = new ArrayList<>(3);
        heap.add(null);
        index = 1;
        keys = new HashSet<>();
    }


    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority){
        if (contains(item)) { throw new IllegalArgumentException("Item already exists in the PQ."); }
        keys.add(item);
        heap.add(index, new Entry(item, priority));
        swim(index);
        index++;
        size++;
    }

    /* Helper method that swaps an Entry with its parent if
     * it has a lower value until it reaches an appropriate spot. */
    private void swim(int k) {
        if (k == 1) { return; }
        if (heap.get(parent(k)).getPriority() > (heap.get(k).getPriority())) {
            swap(k);
            swim(parent(k));
        }
    }

    /* Helper method to swap indexed Entry with its parent. */
    private void swap(int k) {
        Entry temp = heap.get(k);
        heap.set(k, heap.get(parent(k)));
        heap.set(parent(k), temp);
    }

    /* Helper method that swaps an Entry with its smaller child
     * until it reaches an appropriate spot. */
    private void sink(int k) {
        if (k*2 > size() || (k*2+1 > size() && heap.get(k*2).getPriority() >= heap.get(k).getPriority())) {
            return;
        } else if (k*2+1 > size() && heap.get(k*2).getPriority() < heap.get(k).getPriority()) {
            swap(k*2);
            return;
        }
        if (heap.get(k*2).getPriority() <= heap.get(k*2+1).getPriority() &&
            heap.get(k*2).getPriority() < heap.get(k).getPriority()) {
            swap(k*2);
            sink(k*2);
        } else if (heap.get(k*2).getPriority() > heap.get(k*2+1).getPriority() &&
                   heap.get(k*2+1).priority < heap.get(k).getPriority()) {
            swap(k*2+1);
            sink(k*2+1);
        }
    }

    /* Helper method to obtain the parent of the indexed Entry. */
    private int parent(int k) { return k/2; }

    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) { return keys.contains(item); }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest(){
        if (size == 0) { throw new NoSuchElementException(); }
        return heap.get(1).getItem();
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        index -= 1;
        size -= 1;
        T temp = heap.get(1).getItem();
        keys.remove(temp);
        heap.set(1, null);
        heap.set(1, heap.get(index));
        heap.set(index, null);
        sink(1);
        return temp;
    }

    /* Returns the number of items in the PQ. */
    public int size() { return size; }

    private int indOf(T elem) {
        return heap.indexOf(new Entry(elem, 0));
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        if (!contains(item)) { throw new NoSuchElementException(item + " doesn't exist in PQ."); }
        int location = indOf(item);
        heap.get(location).setPriority(priority);
        swim(location);
        sink(location);
    }
}
