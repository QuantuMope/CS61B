/**
 * ArrayDeque class.
 * Circular array list with Deque characteristics.
 * @author Andrew Choi
 * Date: 05/17/2019
 */

public class ArrayDeque<Type> implements Deque<Type>{

    private Type[] items;
    private int size;
    private int nextFirst = 2;
    private int nextLast = 3;

    public ArrayDeque(){
        items = (Type[]) new Object[8];
        size = 0;
    }

//    // @source Professor Hug.
//    public ArrayDeque(ArrayDeque<Type> other){
//        size = 0;
//        for (int i = 0; i < other.size(); i++){
//            addLast(other.get(other.first=i));
//        }
//    }

    public int arraySize(){
        return items.length;
    }

    /** Return size of the list. */
    @Override
    public int size(){
        return size;
    }

    /** Add an item to the front of the list. */
    @Override
    public void addFirst(Type x){
        if (size == items.length){
            if (nextFirst == -1){
                resizeLoopFirst(size * 2);
            } else {
                resize(size * 2);
            }
        }
        if (nextFirst == -1 && nextLast != items.length){
            items[items.length-1] = x;
            nextFirst = items.length-2;
            size += 1;
            return;
        }
        items[nextFirst] = x;
        nextFirst -= 1;
        size += 1;
    }

    /** Add an item to the back of the list. */
    @Override
    public void addLast(Type x){
        if (size == items.length){
            if (nextLast == items.length){
                resizeLoopLast(size * 2);
                items[nextLast] = x;
            } else {
                resize(size * 2);
            }
        }
        else if (nextLast == items.length && nextFirst != (nextLast-1)){
            items[0] = x;
            nextLast = 1;
            size += 1;
            return;
        }
        items[nextLast] = x;
        nextLast += 1;
        size += 1;
    }

    /** Remove and return the first item of the list. */
    @Override
    public Type removeFirst(){
        removeSpace();
        Type value;
        if ((nextFirst+1) == items.length-1){
            value = items[items.length-1];
            items[items.length-1] = null;
            nextFirst = -1;
            size -= 1;
            return value;
        }
        value = items[nextFirst+1];
        items[nextFirst+1] = null;
        nextFirst += 1;
        size -= 1;
        return value;
    }

    /** Remove and return the last item of the list. */
    @Override
    public Type removeLast(){
        removeSpace();
        Type value;
        if ((nextLast - 1) == 0){
            value = items[0];
            items[0] = null;
            nextLast = items.length;
            size -= 1;
            return value;
        }
        value = items[nextLast-1];
        items[nextLast-1] = null;
        nextLast -= 1;
        size -= 1;
        return value;
    }

    /**
     * Resize helper functions.
     * @param refactor
     */
    private void resize(int refactor){
        Type[] new_items = (Type[]) new Object[refactor];
        System.arraycopy(items, nextFirst+1, new_items, new_items.length-(items.length-(nextFirst+1)), items.length-(nextFirst+1));
        System.arraycopy(items, 0, new_items, 0, nextLast);
        nextFirst += refactor/2;
        items = new_items;
    }
    private void resizeLoopLast(int refactor){
        Type[] new_items = (Type[]) new Object[refactor];
        System.arraycopy(items, nextFirst+1, new_items, new_items.length-(items.length-(nextFirst+1)), items.length-(nextFirst+1));
        nextFirst += refactor/2;
        nextLast = 0;
        items = new_items;
    }
    private void resizeLoopFirst(int refactor){
        Type[] new_items = (Type[]) new Object[refactor];
        System.arraycopy(items, 0, new_items, 0, nextLast);
        nextFirst = new_items.length-1;
        items = new_items;
    }

    /** Removes half of array space if usage factor becomes 0.25 or below. */
    private void removeSpace(){
        double uf_size = (double) size;
        double usage_factor = uf_size / items.length;
        if (usage_factor <= 0.25){
            Type[] new_items = (Type[]) new Object[items.length/2];
            if (nextLast > nextFirst){
                System.arraycopy(items, nextFirst+1, new_items, 0, size);
                nextFirst = -1;
                nextLast = nextFirst + size + 2;
                items = new_items;
                return;
            }
            System.arraycopy(items, 0, new_items, 0, nextLast);
            System.arraycopy(items, nextFirst+1, new_items, new_items.length-(items.length-(nextFirst+1)), items.length-(nextFirst+1));
            nextFirst -= items.length/2;
            items = new_items;
        }
    }

    /** Return the item at the xth index in the list. */
    @Override
    public Type get(int i){
        if (i > size){
            return null;
        }
        if (i+nextFirst+1 > items.length){
            return items[(i-(items.length-nextFirst-1))];
        }
        return items[i+nextFirst+1];
    }

    /** Print the contents of the list. */
    @Override
    public void printDeque(){
        for (int i = 0; i < items.length; i++){
            System.out.print(items[i]);
            System.out.print(" ");
        }
    }


    public static void main(String[] args){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 5; i++){
            test.addFirst(i);
            test.addLast(i);
        }
        test.removeFirst();
        test.removeFirst();
        test.removeLast();
    }
}
