public class SLList<inputType>{

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
    public void addFirst(inputType x){
        sentinel.next = new Node(x, sentinel.next);
        size += 1;
    }

    // Gets the first value in the list.
    public inputType getFirst() {
        if (sentinel.next != null) {
            return sentinel.next.value;
        }
        return null;
    }


    public void addLast(inputType x){
        size += 1;
        Node p = sentinel;

        while (p.next != null){
            p = p.next;
        }
        p.next = new Node(x, null);
    }

//    private static int size(Node p){
//        if (p.value == null){
//            return 0;
//        }
//        else if (p.next == null){
//            return 1;
//        }
//        return 1 + size(p.next);
//    }
//
//    public int size(){
//        return size(first);
//    }

    public int size(){
        return size;
    }

    public static void main (String[] args){
        SLList<Integer> L = new SLList<>(10);
        L.addFirst(10);
        L.addFirst(5);
        System.out.println(L.getFirst());
        L.addLast(20);
        int x = L.size();
        System.out.println(L.size());

        // Create an empty list.
        SLList<Integer> X = new SLList<>();
        System.out.println(X.size());
        X.addLast(20);
        System.out.println(X.size());


    }
}