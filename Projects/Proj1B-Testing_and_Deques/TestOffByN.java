/**
 * Unit tests for isPalindrome with a CharacterComparator OffByN as an input.
 * @author Andrew Choi
 * Date: 05/17/2019
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByN = new OffByN(5);

    // Your tests go here.

    /** Test to see if OffByN's equalChars method works.
     */
    @Test
    public void testEqualChars(){
        assertTrue(offByN.equalChars('a', 'f'));
        assertFalse(offByN.equalChars('e', 'r'));
        assertTrue(offByN.equalChars('A', 'F'));
        assertFalse(offByN.equalChars('q', 's'));
    }
}