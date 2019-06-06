package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.Random;

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

    }

    @Test
    public void inOrderTest() {
        int testSize = 500;
        NaivePointSet testNaive;
        KDTree testKD;
        ArrayList<Point> input = new ArrayList<>(45000);
        for (double x = 0; x < testSize; x+=1.75) {
            for (double y = 0; y < testSize; y+=1.75) {
                input.add(new Point(x, y));
            }
        }

        // Naive inorder nearest Test.
        Stopwatch sw1 = new Stopwatch();
        testNaive = new NaivePointSet(input);
        System.out.println("Naive in order add total time elapsed: " + sw1.elapsedTime() +  " seconds.");
        sw1 = new Stopwatch();
        for (double x = 0; x < testSize; x+=2.5) {
            for (double y = 0; y < testSize; y+=2.5) {
                testNaive.nearest(x, y);
            }
        }
        System.out.println("Naive in order nearest time elapsed: " + sw1.elapsedTime() +  " seconds.");

        // KDTree Inorder Test.
        Stopwatch sw2 = new Stopwatch();
        testKD = new KDTree(input);
        System.out.println("KDTree in order add time elapsed: " + sw2.elapsedTime() +  " seconds.");
        sw2 = new Stopwatch();
        for (double x = 0; x < testSize; x+=2.5) {
            for (double y = 0; y < testSize; y+=2.5) {
                testKD.nearest(x, y);
            }
        }
        System.out.println("KDTree inorder nearest time elapsed: " + sw2.elapsedTime() +  " seconds.");

        // Testing that nearest works properly.
        Point expected;
        Point actual;
        for (double x = 0; x < testSize; x+=2.5) {
            for (double y = 0; y < testSize; y+=2.5) {
                expected = testNaive.nearest(x, y);
                actual = testKD.nearest(x, y);
                assertEquals(expected, actual);
            }
        }
    }

    @Test
    public void randomTest() {
        int testSize = 500;
        Random r = new Random();
        double randomX;
        double randomY;
        ArrayList<Point> input = new ArrayList<>(45000);
        for (double x = 0; x < testSize; x+=1.75) {
            for (double y = 0; y < testSize; y+=1.75) {
                randomX = testSize * r.nextDouble();
                randomY = testSize * r.nextDouble();
                input.add(new Point(randomX, randomY));
            }
        }
        Stopwatch sw1 = new Stopwatch();
        NaivePointSet testNaive = new NaivePointSet(input);
        System.out.println("Naive random add time elapsed: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        KDTree testKD = new KDTree(input);
        System.out.println("KDTree random add time elapsed: " + sw2.elapsedTime() +  " seconds.");

        // Naive Random Test.
        sw1 = new Stopwatch();
        for (double x = 0; x < testSize; x+=2.5) {
            for (double y = 0; y < testSize; y+=2.5) {
                randomX = testSize * r.nextDouble();
                randomY = testSize * r.nextDouble();
                testNaive.nearest(randomX, randomY);
            }
        }
        System.out.println("Naive random nearest time elapsed: " + sw1.elapsedTime() +  " seconds.");

        // KDTree Random Test.
        sw2 = new Stopwatch();
        for (double x = 0; x < testSize; x+=2.5) {
            for (double y = 0; y < testSize; y+=2.5) {
                randomX = testSize * r.nextDouble();
                randomY = testSize * r.nextDouble();
                testKD.nearest(randomX, randomY);
            }
        }
        System.out.println("KDTree random nearest time elapsed: " + sw2.elapsedTime() +  " seconds.");

        // Testing that nearest works properly.
        Point expected;
        Point actual;
        for (double x = 0; x < testSize; x+=2.5) {
            for (double y = 0; y < testSize; y+=2.5) {
                expected = testNaive.nearest(x, y);
                actual = testKD.nearest(x, y);
                assertEquals(expected, actual);
            }
        }
    }





    @Test
    public void pruningTest() {
        Point ins1 = new Point(2, 3);
        Point ins2 = new Point(4, 2);
        Point ins3 = new Point(4, 5);
        Point ins4 = new Point(3, 3);
        Point ins5 = new Point(1, 5);
        Point ins6 = new Point(4, 4);
        Point ins7 = new Point(2.1, 8);
        NaivePointSet testNaive = new NaivePointSet(List.of(ins1, ins2, ins3, ins4, ins5, ins6, ins7));
        KDTree testKD = new KDTree(List.of(ins1, ins2, ins3, ins4, ins5, ins6, ins7));
        Point expected = testNaive.nearest(0, 8);
        Point actual = testKD.nearest(0, 8);
        assertEquals(expected, actual);

        Point ins8 = new Point(2.05, 8);
        testNaive = new NaivePointSet(List.of(ins1, ins2, ins3, ins4, ins5, ins6, ins8, ins7));
        testKD = new KDTree(List.of(ins1, ins2, ins3, ins4, ins5, ins6, ins8, ins7));
        expected = testNaive.nearest(0, 8);
        actual = testKD.nearest(0, 8);
        assertEquals(expected, actual);
    }

}
