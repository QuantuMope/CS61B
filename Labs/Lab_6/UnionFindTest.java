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
}
