import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    /**
     * Test for ArrayDeque get method & resizing.
     */
    @Test
    public void getTest(){
        // Test for when last loops around with no resizing.
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 5; i++){
            test.addFirst(i);
            test.addLast(i);
        }
        test.removeFirst();
        test.removeFirst();
        test.removeLast();
        // Tests get method.
        int actual = test.get(5);
        assertEquals(2, actual);
        assertNull(test.get(7));
        int actual3 = test.get(2);
        assertEquals(0, actual3);

    }
    /**
     * Test for ArrayDeque's proper monitoring of usage factor.
     */
    @Test
    public void usageFactorTest(){
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 17; i++){
            test.addLast(i);
        }
        for (int i = 0; i < 14; i++){
            test.removeLast();
        }
        int actual4 = test.arraySize();
        assertEquals(8, actual4);
        test.addFirst(300);
        int actual5 = test.get(0);
        assertEquals(300, actual5);
    }


}
