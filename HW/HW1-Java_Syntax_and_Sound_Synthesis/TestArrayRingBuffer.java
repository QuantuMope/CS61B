package es.datastructur.synthesizer;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

/**
 * @author Andrew Choi
 * Date: 05/20/2019
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

    @Test
    public void equalsTest() {
        ArrayRingBuffer<Integer> test1 = new ArrayRingBuffer<>(4);
        test1.enqueue(4);
        test1.enqueue(7);
        test1.enqueue(9);
        ArrayRingBuffer<Integer> test2 = new ArrayRingBuffer<>(4);
        test2.enqueue(9);
        test2.enqueue(4);
        test2.enqueue(7);
        assertFalse(test1.equals(test2));
        test2.dequeue();
        test2.enqueue(9);
        assertTrue(test1.equals(test2));
    }

    @Test
    public void toStringTest() {
        ArrayRingBuffer<Integer> test1 = new ArrayRingBuffer<>(4);
        test1.enqueue(4);
        test1.enqueue(7);
        test1.enqueue(9);
        assertEquals("4, 7, 9", test1.toString());
        test1.dequeue();
        test1.enqueue(10);
        test1.dequeue();
        test1.enqueue(2);
        assertEquals("9, 10, 2", test1.toString());
    }
}
