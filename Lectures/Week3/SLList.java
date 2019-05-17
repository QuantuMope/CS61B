public class SLList<inputType> implements List61B<inputType>{

    private class Node {
        public inputType value;
        public Node next;

        public Node(inputType f, Node r){
            value = f;
            next = r;
        }

    }
    private Node sentinel = new Node(null, null);
    public int size;

    public SLList(){
        size = 0;
    }

    public SLList(inputType x){
        sentinel.next = new Node(x, null);
        size = 1;
    }

    // Adds x to the front of the list.
    @Override
    public void addFirst(inputType x){
        sentinel.next = new Node(x, sentinel.next);
        size += 1;
    }

    // Gets the first value in the list.
    @Override
    public inputType getFirst() {
        if (sentinel.next != null) {
            return sentinel.next.value;
        }
        return null;
    }

    @Override
    public void addLast(inputType x){
        size += 1;
        Node p = sentinel;

        while (p.next != null){
            p = p.next;
        }
        p.next = new Node(x, null);
    }

    @Override
    public inputType getLast(){
        return null;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public inputType get(int x){
        Node p = sentinel.next;
        if (x < size()){
            for (int i = 0; i < x; i ++){
                p = p.next;
            }
            return p.value;
        }
        return null;
    }

    @Override
    public inputType removeLast(){
        return null;
    }

    @Override
    public inputType removeFirst(){
        return null;
    }

    /** Override implemented print method due to its inefficiency in SLLists. */
    @Override
    public void print(){
        for (Node p = sentinel.next; p != null; p = p.next){
            System.out.print(p.value + " ");
        }
    }

    public static void main (String[] args){
        SLList<Integer> L = new SLList<>(10);
        L.addFirst(10);
        L.addFirst(5);
        System.out.println(L.get(4));
        L.print();


    }
}