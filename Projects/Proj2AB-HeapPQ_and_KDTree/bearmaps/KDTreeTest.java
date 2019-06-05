package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Stopwatch;

import static org.junit.Assert.assertEquals;

/**
 * Tests for KDTree.
 * @author Andrew Choi
 * Date: 06/04/2019
 */

public class KDTreeTest {

    @Test
    public void insertTest() {

        Point ins1 = new Point(2.5, 5.5);
        Point ins2 = new Point(3.0, 5.5);
        Point ins3 = new Point(3.0, 4.0);
        Point ins4 = new Point(1.0, 3.4);
        Point ins5 = new Point(6.7, 7.8);
        Point ins6 = new Point(2.5, 1.6);
        NaivePointSet testNaive = new NaivePointSet(List.of(ins1, ins2, ins3, ins4, ins5, ins6));
        KDTree testKD = new KDTree(List.of(ins1, ins2, ins3, ins4, ins5, ins6));
        assertEquals(testNaive.nearest(2.7, 3.6), testKD.nearest(2.7, 3.6));

        ArrayList<Point> input = new ArrayList<>(45000);
        for (double x = 0; x < 1000; x+=1.5) {
            for (double y = 0; y < 1000; y+=1.5) {
                input.add(new Point(x, y));
            }
        }
        testNaive = new NaivePointSet(input);
        testKD = new KDTree(input);

        Stopwatch sw1 = new Stopwatch();
        Point expected = testNaive.nearest(375.4, 124.76);
        System.out.println("Naive total time elapsed: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        Point actual = testKD.nearest(375.4, 124.76);
        System.out.println("KDTree total time elapsed: " + sw2.elapsedTime() +  " seconds.");

        assertEquals(expected, actual);
    }

}
