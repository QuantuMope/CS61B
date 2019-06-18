import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stopwatch;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

/**
 * Tests for QuickSort and MergeSort.
 * Both tests are identical aside from random Integers generated.
 * @author Andrew Choi
 * Date: 06/17/2019
 */

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> quickSortTest = new Queue<>();
        quickSortTest.enqueue("Thomas");
        quickSortTest.enqueue("Brandon");
        quickSortTest.enqueue("Andrew");
        quickSortTest.enqueue("Carson");
        quickSortTest.enqueue("Zeke");
        Queue<String> sorted = QuickSort.quickSort(quickSortTest);
        assertEquals(quickSortTest.size(), sorted.size());
        assertTrue(isSorted(sorted));

        Random rand = new Random();
        Queue<Integer> secondQuickTest = new Queue<>();
        for (int i = 0; i < 50; i++) {
            int n = rand.nextInt(51);
            secondQuickTest.enqueue(n);
        }
        Stopwatch sw1 = new Stopwatch();
        Queue<Integer> sorted2 = QuickSort.quickSort(secondQuickTest);
        System.out.println("QuickSort time is " + sw1.elapsedTime());
        assertEquals(secondQuickTest.size(), sorted2.size());
        assertTrue(isSorted(sorted2));
    }

    @Test
    public void testMergeSort() {
        Queue<String> mergeSortTest = new Queue<>();
        mergeSortTest.enqueue("Thomas");
        mergeSortTest.enqueue("Brandon");
        mergeSortTest.enqueue("Andrew");
        mergeSortTest.enqueue("Carson");
        mergeSortTest.enqueue("Zeke");
        Queue<String> sorted = MergeSort.mergeSort(mergeSortTest);
        assertEquals(mergeSortTest.size(), sorted.size());
        assertTrue(isSorted(sorted));

        Random rand = new Random();
        Queue<Integer> secondMergeTest = new Queue<>();
        for (int i = 0; i < 50; i++) {
            int n = rand.nextInt(51);
            secondMergeTest.enqueue(n);
        }
        Stopwatch sw2 = new Stopwatch();
        Queue<Integer> sorted2 = MergeSort.mergeSort(secondMergeTest);
        System.out.println("MergeSort time is " + sw2.elapsedTime());
        assertEquals(secondMergeTest.size(), sorted2.size());
        assertTrue(isSorted(sorted2));
    }

    @Test
    public void timeComparisonTest() {
        Random rand = new Random();
        Queue<Integer> testQueue = new Queue<>();
        for (int i = 0; i < 50; i++) {
            int n = rand.nextInt(51);
            testQueue.enqueue(n);
        }
        Stopwatch sw1 = new Stopwatch();
        Queue<Integer> sorted1 = MergeSort.mergeSort(testQueue);
        System.out.println("MergeSort time is " + sw1.elapsedTime());
        Stopwatch sw2 = new Stopwatch();
        Queue<Integer> sorted2 = QuickSort.quickSort(testQueue);
        System.out.println("QuickSort time is " + sw2.elapsedTime());
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
