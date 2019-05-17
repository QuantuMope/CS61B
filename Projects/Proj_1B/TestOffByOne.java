/**
 * Unit tests for isPalindrome with a CharacterComparator OffByOne as an input.
 * @author Andrew Choi
 * Date: 05/17/2019
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.

    /** Test to see if OffByOne's equalChars method works.
     */
    @Test
    public void testEqualChars(){
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('e', 'r'));
        assertTrue(offByOne.equalChars('&', '%'));
        assertFalse(offByOne.equalChars('q', 'l'));
    }
}