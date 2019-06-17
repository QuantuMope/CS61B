import edu.princeton.cs.algs4.Queue;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> quickSortTest = new Queue<>();
        quickSortTest.enqueue("Thomas");
        quickSortTest.enqueue("Brandon");
        quickSortTest.enqueue("Andrew");
        quickSortTest.enqueue("Carson");
        quickSortTest.enqueue("Zeke");
        QuickSort.quickSort(quickSortTest);
        assertTrue(isSorted(quickSortTest));
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
