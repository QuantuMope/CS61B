public class Sort {

    public static void sort(String[] x){
        //Find the smallest item.
        // Move it to the front.
        // Selection sort the rest. (recursion?)
        sort(x, 0);

    }

    /** Sorts x starting at position start.
     * Recursive helper for sort method. */
    private static void sort(String[] x, int start){
        if (start == x.length){
            return;
        }
        int smallestIndex = findSmallest(x, start);
        swap(x, start, smallestIndex);
        sort(x, start + 1);
    }


    /** Swap item a with b. */
    // public for testing purposes.
    public static void swap(String[]x, int a, int b){
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }


    /** Return the index of the smallest String in x, starting from start. */
    // public for testing purposes.
    public static int findSmallest(String[] x, int start){
        int smallestIndex = start;
        for (int i = start; i < x.length; i += 1){
            if (x[i].compareTo(x[smallestIndex]) < 0){
                smallestIndex = i;
            }
        }
        return smallestIndex;
    }


}
