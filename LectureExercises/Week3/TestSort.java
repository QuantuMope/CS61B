import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the Sort class. */
public class TestSort{

    /** use @org.junit.Test to signal that follow method is a test.
     *  Remove static and delete main().
     *  This will allow for a neat interface when testing.
     */

    /** Test the Sort.sort method. */
    @Test
    public void testSort(){
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};
        Sort.sort(input);

        /* Making adhoc code like this for testing is tedious. Therefore, use JUnit tests.
        for (int i = 0; i < input.length; i += 1){
            if (!input[i].equals(expected[i])){
                System.out.println("Mismatch in position " + i + ", expected: " + expected[i] + ", but got: " +  input[i]);
            }
        }
         */

        assertArrayEquals(expected, input);

    }

    /** Test the Sort.findSmallest method. */
    @Test
    public void testFindSmallest(){
        // Test 1
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;
        int actual = Sort.findSmallest(input, 0);

        assertEquals(expected, actual);

        // Test 2
        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 1;
        int actual2 = Sort.findSmallest(input2, 0);

        assertEquals(expected2, actual2);

    }

    /** Test the Sort.swap method. */
    @Test
    public void testSwap(){
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        Sort.swap(input, a, b);

        assertArrayEquals(expected, input);
    }

}