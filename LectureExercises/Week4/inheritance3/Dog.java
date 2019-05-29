import java.util.Comparator;

public class Dog implements Comparable<Dog> {
    public String name;
    private int size;

    public Dog(String n, int s) {
        name = n;
        size = s;
    }

    /** Returns negative number if this dog is less than the dog pointed at by o, and so forth. */
    @Override
    public int compareTo(Dog uddaDog) {
        return this.size - uddaDog.size;
    }



    public void bark() {
        System.out.println(name + " says: bark");
    }

    /** Create private Comparator class for comparing Dog names.
     * To use, create a Comparator object and use its specific compare function. */
    private static class NameComparator implements Comparator<Dog> {
        public int compare(Dog a, Dog b) {
            return a.name.compareTo(b.name);
        }
    }
    public static Comparator<Dog> getNameComparator() {
        return new NameComparator();
    }
}