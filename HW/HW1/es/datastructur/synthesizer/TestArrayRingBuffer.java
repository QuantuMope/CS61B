package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> test = new ArrayRingBuffer<>(4);
        test.enqueue(7);
        test.enqueue(3);
        test.enqueue(1);
        test.dequeue();
        assertEquals(2, test.fillCount());
        assertEquals(3, (int) test.peek());
    }
}
