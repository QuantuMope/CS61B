package es.datastructur.synthesizer;

public interface BoundedQueue<T> {

    /** Return the size of the buffer. */
    int capacity();

    /** Return the number of items currently in the buffer. */
    int fillCount();

    /** Add item x to the end. */
    void enqueue(T x);

    /** Delete and return item from the front. */
    T deque();

    /** Return (but do not delete) item from the front. */
    T peek();

    /** Checks to see whether or not the buffer is empty.
     * i.e. fillCount equals zero? */
    default boolean isEmpty() {
        return true;
    }

    /** Checks to see if the buffer is full.
     * i.e. fillCount is same as capacity? */
    default boolean isFull() {
        return true;
    }

}
