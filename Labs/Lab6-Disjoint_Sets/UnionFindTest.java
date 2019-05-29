import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {

    @Test
    public void testSizeOf() {
        UnionFind test = new UnionFind(10);
        test.union(1, 2);
        test.union(2, 3);
        test.union(2, 3);
        test.union(4, 5);
        test.union(4, 3);
        test.union(8, 9);
        assertEquals(5, test.sizeOf(1));
        assertEquals(2, test.sizeOf(9));
        assertEquals(1, test.sizeOf(7));
    }

    @Test
    public void testConnected() {
        UnionFind test = new UnionFind(10);
        test.union(1, 2);
        test.union(2, 3);
        test.union(2, 3);
        test.union(4, 5);
        test.union(4, 3);
        test.union(8, 9);
        assertTrue(test.connected(2, 5));
        assertFalse(test.connected(4, 9));
    }

    @Test
    public void testPathCompression() {
        UnionFind test = new UnionFind(22);
        for (int i = 0; i < 10; i++) {
            test.union(i, i+1);
        }
        test.union(18, 19);
        test.union(18, 15);
        test.union(15, 14);
        int expectedParent1 = 1;
        int expectedParent2 = 19;
        assertEquals(expectedParent1, test.parent(7));
        assertEquals(expectedParent1, test.parent(9));
        assertEquals(expectedParent2, test.parent(15));
    }
}
