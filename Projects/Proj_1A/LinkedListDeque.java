public class LinkedListDeque<Type> {

    public class Node{
        public Type item;
        public Node prev;
        public Node next;

        public Node(Type i, Node f, Node b){
            item = i;
            next = f;
            prev = b;
        }
    }

    private int size;
    public Node sentinel = new Node(null, null, null);

    public boolean isEmpty(){
        return (size == 0);
    }

    public int size(){
        return size;
    }

    public LinkedListDeque(){
        // Have sentinel point to a non null value. x is a temporary Node that will be eaten up
        // by the garbage collector as soon as a legitimate Node is added.
        size = 0;
    }


    // @source Professor Hug.
    public LinkedListDeque(LinkedListDeque<Type> other){
        sentinel = new Node(null, null,null);
        size = 0;
        for (int i = 0; i < other.size(); i++){
            addLast(other.get(i));
        }
    }

    private void addEmptyList(Type x){
        Node i = new Node(x, sentinel, sentinel);
        sentinel.next = i;
        sentinel.prev = i;
        size += 1;
    }

    public void addFirst(Type item){
        // If adding to an empty list.
        if (isEmpty()){
            addEmptyList(item);
            return;
        }
        // If more than one object in list.
        Node x = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = x;
        sentinel.next = x;
        size += 1;
    }

    public void addLast(Type item){
        // If adding to an empty list.
        if (isEmpty()){
            addEmptyList(item);
            return;
        }
        // If more than one object in the list.
        Node x = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = x;
        sentinel.prev = x;
        size += 1;
    }

    public Type removeFirst(){
        if (isEmpty()){
            return null;
        }
        // Save the return value before Node is erased.
        Type value = sentinel.next.item;
        Node p = sentinel.next;
        // Make sentinel point to the Node after the deleted one.
        sentinel.next = p.next;
        // Make Node after deleted point back to sentinel.
        p.next.prev = sentinel;
        size -=1;
        return value;
    }

    public Type removeLast(){
        if (isEmpty()){
            return null;
        }
        Type value = sentinel.prev.item;
        Node p = sentinel.prev;
        sentinel.prev = p.prev;
        p.prev.next = sentinel;
        size -=1;
        return value;
    }

    public Type get(int x){
        if (x >= size){
            return null;
        }
        Node p = sentinel.next;
        int i = 0;
        while (i < x){
            p = p.next;
            i++;
        }
        return p.item;
    }

    public Type getRecursive(int x){
        if (x >= size){
            return null;
        }
        return recursiveHelp(sentinel.next, x);
    }

    public Type recursiveHelp(Node p, int x){
        if (x == 0){
            return p.item;
        }
        return recursiveHelp(p.next, x-1);
    }

    public void printDeque(){
        if (isEmpty()){
            System.out.println("/n");
            return;
        }
        Node p = sentinel.next;
        while (p != sentinel){
            System.out.print(p.item);
            System.out.print(' ');
            p = p.next;
        }
    }


    public static void main(String[] args) {
        int x = 82;
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        test.addFirst(4);
        test.addFirst(3);
        test.addLast(5);
        test.addLast(6);
        test.addLast(7);
        test.addLast(8);
        test.removeFirst();
        // List contains 4 5 6 7 8.
        System.out.println(test.get(2));
        // Should be 6.
        System.out.println(test.getRecursive(4));
        // Should be 8.

    }


}
