/**
 * Deque Interface for LinkedListDeque and ArrayDeque classes.
 * @author Andrew Choi
 * Date: 05/17/2019
 */

public interface Deque<Type> {


    /** Return true if list is empty. */
    default boolean isEmpty(){
        return (size() == 0);
    }

    /** Return size of the list. */
    int size();

    /** Add an item to the front of the list. */
    void addFirst(Type x);

    /** Add an item to the back of the list. */
    void addLast(Type x);

    /** Remove and return the first item of the list. */
    Type removeFirst();

    /** Remove and return the last item of the list. */
    Type removeLast();

    /** Return the item at the xth index in the list. */
    Type get(int x);

    /** Print the contents of the list. */
    void printDeque();


}
