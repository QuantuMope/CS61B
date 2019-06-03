package bearmaps;

import org.junit.Test;

import java.lang.reflect.Array;
import edu.princeton.cs.algs4.Stopwatch;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void removeSmallestTest() {
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        test.add(5,5);
        test.add(7,7);
        test.add(8,8);
        test.add(4,4);
        test.add(19,19);
        test.add(2,2);
        assertEquals(2, (int) test.removeSmallest());
        assertEquals(4, (int) test.removeSmallest());
        assertEquals(5, (int) test.removeSmallest());
        assertFalse(test.contains(5));
        assertEquals(7, (int) test.removeSmallest());
        assertTrue(test.contains(19));
        assertEquals(8, (int) test.removeSmallest());
        assertEquals(19, (int) test.removeSmallest());
        test.add(8,8);
        assertEquals(8, (int) test.getSmallest());
    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> test = new ArrayHeapMinPQ<>();
        test.add("first",1);
        test.add("second",2);
        test.add("third",3);
        test.add("fourth",4);
        test.add("fifth",5);
        test.add("sixth",6);
        test.changePriority("fourth", 0.5);
        assertEquals("fourth", test.removeSmallest());
        assertEquals("first", test.getSmallest());
        test.changePriority("third", 1.5);
        test.removeSmallest();
        assertEquals("third", test.getSmallest());
    }

    @Test
    public void timerTest() {
        NaiveMinPQ<Integer> naive = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> proper = new ArrayHeapMinPQ<>();

        Stopwatch sw1 = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            naive.add(i, i);
        }
        for (int i = 0; i < 10000; i+=2) {
            naive.changePriority(i, i*10);
        }
//        for (int i = 0; i < 10000; i++) {
//            naive.removeSmallest();
//        }
        System.out.println("Total time elapsed: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        for (int i = 0; i < 10000; i++) {
            proper.add(i, i);
        }
        for (int i = 0; i < 10000; i+=2) {
            proper.changePriority(i, i*10);
        }
//        for (int i = 0; i < 10000; i++) {
//            proper.removeSmallest();
//        }
        System.out.println("Total time elapsed: " + sw2.elapsedTime() +  " seconds.");
    }


}
