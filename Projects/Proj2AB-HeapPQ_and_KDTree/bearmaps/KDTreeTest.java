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
        for (double x = 0; x < 100; x+=1.75) {
            for (double y = 0; y < 100; y+=1.75) {
                input.add(new Point(x, y));
            }
        }
        Stopwatch sw1 = new Stopwatch();
        testNaive = new NaivePointSet(input);
        System.out.println("Naive add total time elapsed: " + sw1.elapsedTime() +  " seconds.");
        sw1 = new Stopwatch();
        for (double x = 0; x < 100; x+=1.13) {
            for (double y = 0; y < 100; y += 1.13) {
                testNaive.nearest(x, y);
            }
        }
        Point expected = testNaive.nearest(76.25, 24.76);
        System.out.println("Naive nearest time elapsed: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        testKD = new KDTree(input);
        System.out.println("KDTree add time elapsed: " + sw2.elapsedTime() +  " seconds.");
        sw2 = new Stopwatch();
        for (double x = 0; x < 100; x+=1.13) {
            for (double y = 0; y < 100; y += 1.13) {
                testKD.nearest(x, y);
            }
        }
        Point actual = testKD.nearest(76.25, 24.76);
        System.out.println("KDTree nearest time elapsed: " + sw2.elapsedTime() +  " seconds.");

        assertEquals(expected, actual);
    }

    @Test
    public void pruningTest() {
        Point ins1 = new Point(2, 3);
        Point ins2 = new Point(4, 2);
        //Point ins3 = new Point(4, 5);
        //Point ins4 = new Point(3, 3);
        Point ins5 = new Point(1, 5);
        Point ins6 = new Point(4, 4);
        Point ins7 = new Point(2.1, 8);
        NaivePointSet testNaive = new NaivePointSet(List.of(ins1, ins2, ins5, ins6, ins7));
        KDTree testKD = new KDTree(List.of(ins1, ins2, ins5, ins6, ins7));
        Point expected = testNaive.nearest(0, 8);
        Point actual = testKD.nearest(0, 8);
        assertEquals(expected, actual);

        Point ins8 = new Point(2.05, 8);
//        testNaive = new NaivePointSet(List.of(ins1, ins2, ins3, ins4, ins5, ins6, ins8, ins7));
//        testKD = new KDTree(List.of(ins1, ins2, ins3, ins4, ins5, ins6, ins8, ins7));
        expected = testNaive.nearest(0, 8);
        actual = testKD.nearest(0, 8);
        assertEquals(expected, actual);
    }

}
