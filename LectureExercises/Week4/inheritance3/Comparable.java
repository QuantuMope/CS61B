/**
 * Created by jug on 2/7/18.
 */
public interface Comparable<T> {

    /** Return negative if this < o.
     *  Return 0 if this equals o.
     *  Return positive if this > o,
     */
    int compareTo(T o);


}
