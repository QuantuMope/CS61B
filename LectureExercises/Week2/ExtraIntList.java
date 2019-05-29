public class ExtraIntList{

    public static IntList incrList(IntList L, int x){
        int size = L.size();
        IntList Q = new IntList(L.getRec(size-1) + x, null);
        for (int i = 1; i < L.size(); i++){
            Q = new IntList(L.getRec(size-1-i) + x, Q);
        }
        return Q;
    }

    public static IntList dincrList(IntList L, int x){
        IntList Q = L;
        IntList placeholder = L;
        for (int i = 0; i < L.size(); i++){
            placeholder.first = placeholder.first + x;
            placeholder = placeholder.rest;
        }
        return Q;
    }

    public static void main(String[] args){
        IntList L = new IntList(92, null);
        L = new IntList(43, L);
        L = new IntList(3, L);

        IntList new_L = incrList(L, 2);
        System.out.println("incrList Test.");
        System.out.println("==============================");
        System.out.println("This is the original IntList.");
        for (int i = 0; i < L.size(); i++){
            System.out.println(L.getRec(i));
        }
        System.out.println("This is the incremented IntList.");
        for (int i = 0; i < L.size(); i++){
            System.out.println(new_L.getRec(i));
        }
        System.out.println("The original list shown below should be unchanged.");
        for (int i = 0; i < L.size(); i++){
            System.out.println(L.getRec(i));
        }

        new_L = dincrList(L, 2);
        System.out.println("dincrList Test.");
        System.out.println("==============================");
        System.out.println("This is the original IntList.");
        for (int i = 0; i < L.size(); i++){
            System.out.println(L.getRec(i));
        }
        System.out.println("This is the incremented IntList.");
        for (int i = 0; i < L.size(); i++){
            System.out.println(new_L.getRec(i));
        }
        System.out.println("The original list should have changed as well.");
        for (int i = 0; i < L.size(); i++){
            System.out.println(L.getRec(i));
        }
    }
}
