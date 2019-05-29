package es.datastructur.synthesizer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Andrew Choi
 * Date: 05/20/2019
 */

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = last = fillCount = 0;
    }

    /**
     * Returns the size of the buffer.
     */
    public int capacity() {
        return rb.length;
    }

    /**
     * Returns the number of items currently in the buffer.
     */
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow.");
        }
        if (last == capacity()){
            last = 0;
        }
        rb[last] = x;
        last += 1;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow.");
        }
        if (first == capacity()) {
            first = 0;
        }
        T temp = rb[first];
        rb[first] = null;
        first += 1;
        fillCount -= 1;
        return temp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow.");
        }
        if (first == capacity()) {
            first = 0;
        }
        return rb[first];
    }

    /**
     * Create an iterator method to allow the ArrayRingBuffer to be Iterable.
     */
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int pos;
        public ArrayRingBufferIterator() {
            pos = first;
        }
        public boolean hasNext(){
            return rb[pos] != null;
        }

        public T next() {
            if (hasNext()) {
                T temp = rb[pos];
                pos += 1;
                if (pos == capacity()) {
                    pos = 0;
                }
                return temp;
            }
            return null;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null){
            return false;
        }
        if (other.getClass() != this.getClass()){
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (fillCount != o.fillCount()) {
            return false;
        }
        if (!this.toString().equals(other.toString())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            if (x == null) {
                continue;
            }
            listOfItems.add(x.toString());
        }
        return String.join(", ", listOfItems);
    }

    public static void main(String[] args) {
        ArrayRingBuffer<Integer> test = new ArrayRingBuffer<>(4);
        test.enqueue(3);
        test.enqueue(4);
        ArrayRingBuffer<Integer> test2 = new ArrayRingBuffer<>(4);
        test2.enqueue(3);
        test2.enqueue(4);
        test2.dequeue();
        test2.dequeue();
        test2.enqueue(3);
        test2.enqueue(4);
        String t1 = test.toString();
        String t2 = test2.toString();
    }

}