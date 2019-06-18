import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Random;

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
        Queue<Integer> sorted2 = QuickSort.quickSort(secondQuickTest);
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
        Queue<Integer> sorted2 = MergeSort.mergeSort(secondMergeTest);
        assertEquals(secondMergeTest.size(), sorted2.size());
        assertTrue(isSorted(sorted2));
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
