public class SLList{

    private static class IntNode{
        public int node;
        public IntNode next;

        public IntNode(int f, IntNode r){
            node = f;
            next = r;
        }

    }
    private IntNode sentinel = new IntNode(63, null);
    public int size;

    public SLList(){
        size = 0;
    }

    public SLList(int x){
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    // Adds x to the front of the list.
    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }

    // Gets the first value in the list.
    public int getFirst() {
        if (sentinel.next != null) {
            return sentinel.next.node;
        }
        return 0;
    }


    public void addLast(int x){
        size += 1;
        IntNode p = sentinel;

        while (p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x, null);
    }

//    private static int size(IntNode p){
//        if (p.node == null){
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
        SLList L = new SLList(10);
        L.addFirst(10);
        L.addFirst(5);
        System.out.println(L.getFirst());
        L.addLast(20);
        int x = L.size();
        System.out.println(L.size());

        // Create an empty list.
        SLList X = new SLList();
        System.out.println(X.size());
        X.addLast(20);
        System.out.println(X.size());


    }
}