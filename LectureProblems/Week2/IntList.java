public class IntList{
    public int first;
    public IntList rest;

    public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    // Obtains the size of the IntList through recursion.
    public int size(){
        if (rest == null){
            return 1;
        }
        return 1 + this.rest.size();
    }

    // Returns ith item in the list using iteration.
    // Assumes item exists. Front item is 0th item.
    public int getIter(int i){
        IntList current = this;
        for (int count = 0; count < i; count++){
            current = current.rest;
        }
        return current.first;
    }

    // Returns ith item in the list using recursion.
    // Assumes item exists. Front item is 0th item.
    public int getRec(int i){
        if (i == 0){
            return this.first;
        }
        return rest.getRec(i-1);
    }


    public static void main(String[] args){
        // Create an IntList and feed new IntLists onto itself.
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        // Should print 3.
        System.out.println(L.size());

        // Should print 5.
        System.out.println(L.getIter(0));
        System.out.println(L.getRec(0));
        // Should print 10.
        System.out.println(L.getIter(1));
        System.out.println(L.getRec(1));
        // Should print 15.
        System.out.println(L.getIter(2));
        System.out.println(L.getRec(2));
    }
}
