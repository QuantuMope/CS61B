package hw2;
import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationTest {

    @Test
    public void percolatesTest() {
        Percolation example = new Percolation(3);
        assertFalse(example.percolates());
        example.open(0,0);
        example.open(1,0);
        example.open(2,0);
        assertTrue(example.percolates());
    }
}
