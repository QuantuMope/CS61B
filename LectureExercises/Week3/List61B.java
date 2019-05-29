public interface List61B<inputType> {
    public void addFirst(inputType x);
    public void addLast(inputType x);
    public inputType getFirst();
    public inputType getLast();
    public inputType get(int i);
    public inputType removeLast();
    public inputType removeFirst();
    public int size();

    /** Prints out the entire List. */
    default public void print(){
        for (int i = 0; i < size(); i+=1){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}






