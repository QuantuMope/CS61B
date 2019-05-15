// Tests the Sort class.
public class TestSort{
    /** Test the Sort.sort method. */
    public static void testSort(){
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

        org.junit.Assert.assertArrayEquals(expected, input);

    }

    /** Test the Sort.findSmallest method. */
    public static void testFindSmallest(){
        // Test 1
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;
        int actual = Sort.findSmallest(input, 0);

        org.junit.Assert.assertEquals(expected, actual);

        // Test 2
        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 1;
        int actual2 = Sort.findSmallest(input2, 0);

        org.junit.Assert.assertEquals(expected2, actual2);

    }

    /** Test the Sort.swap method. */
    public static void testSwap(){
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;
        String[] expected = {"an", "have", "i", "egg"};

        Sort.swap(input, a, b);

        org.junit.Assert.assertArrayEquals(expected, input);
    }

    public static void main(String[] args){
        //testSort();.
        testFindSmallest();
        testSwap();
        testSort();
    }
}