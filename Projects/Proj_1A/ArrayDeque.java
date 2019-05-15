public class ArrayDeque<Type> {

    private Type[] items;
    private int size;
    private int nextFirst = 3;
    private int nextLast = 4;

    public ArrayDeque(){
        items = (Type[]) new Object[8];
        size = 0;
    }
    public void addFirst(Type x){
        items[nextFirst] = x;
        nextFirst -= 1;
        size += 1;
    }

    public void addLast(Type x){
        if addLast
        items[nextLast] = x;
        nextLast += 1;
        size += 1;
    }


    public static void main(String[] args){

    }
}
