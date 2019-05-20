import java.util.*;

public class ArraySet<T> implements Iterable<T>{

    // No resizing implemented.
    private int size;
    private T[] items = (T[]) new Object[100];

    public ArraySet() {
        size = 0;
    }

    /** Returns at iterator (aka seer) into ME */
    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T>{
        private int pos;
        public ArraySetIterator(){
            pos = 0;
        }

        public boolean hasNext(){
            return pos < size;
        }

        public T next(){
            T returnItem = items[pos];
            pos += 1;
            return returnItem;
        }
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++){
            if (items[i].equals(x)){
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. 
       Ignores null values that are added. */
    public void add(T x) {
        if (this.contains(x) || x == null){
            return;
        }
        items[size] = x;
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

//     //Use StringBuilder when creating strings through a for loop.
//     //Concatenating strings is very inefficient, takes quadratic time as string lengthens.
//     //Whereas, StringBuilder is linear.
//    @Override
//    public String toString(){
//        StringBuilder returnSB = new StringBuilder("{");
//        for (int i = 0; i < size-1; i++){
//            returnSB.append(items[i]);
//            returnSB.append(", ");
//        }
//        returnSB.append(items[size-1] + "}");
//        return returnSB.toString();
//    }

    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T x : this) {
            listOfItems.add(x.toString());
        }
        return String.join(", ", listOfItems);
    }

    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        ArraySet<Glerp> returnSet = new ArraySet<Glerp>();
        for (Glerp x : stuff) {
            returnSet.add(x);
        }
        return returnSet;
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArraySet<T> o = (ArraySet<T>) other;
        if (o.size() != this.size()){
            return false;
        }
        for (T item: this){
            if (!o.contains(item)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        ArraySet<String> s = new ArraySet<>();
//        s.add(null);
//        s.add("horse");
//        s.add("fish");
//        s.add("house");
//        s.add("fish");
//        System.out.println(s.contains("horse"));
//        System.out.println(s.size());
//
//        // In a HashSet, a null is a valid input into a String set.
//        // In our ArraySet, nulls are not allowed.
//        Set<Integer> s2 = new HashSet<>();
//        s2.add(null);
//        System.out.println(s2.contains(null));
//
//        // The code below shows how an enhanced for loop operates.
//        Iterator<Integer> seer = s2.iterator();
//        while (seer.hasNext()){
//            int i = seer.next();
//            System.out.println(i);
//        }

        // Make enhanced for loops work on ArraySets.
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(5);
        aset.add(23);
        aset.add(42);

//        Iterator<Integer> aseer = aset.iterator();
//        while (aseer.hasNext()){
//            int i = aseer.next();
//            System.out.println(i);
//        }

        // Must implement Iterable interface and have the necessary
        // Iterator<T> iterator() method for enhanced for loop to work properly.
        for (int i : aset){
            System.out.println(i);
        }

        // Println methods auto call the .toString() method, so it is not necessary to type out.
        System.out.println(aset);
        System.out.println(aset.toString());

        ArraySet<Integer> other = new ArraySet<>();
        other.add(5);
        other.add(23);
        other.add(42);

        System.out.println(aset.equals(other));

        ArraySet<String> asetofStrings = ArraySet.of("hi", "I'm", "here.");
        System.out.println(asetofStrings);


    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
