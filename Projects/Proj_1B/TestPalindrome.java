/**
 * Unit tests for isPalindrome with no CharacterComparator input.
 * @author Andrew Choi
 * Date: 05/17/2019
 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        assertTrue(palindrome.isPalindromeRec("racecar"));
        assertFalse(palindrome.isPalindromeRec("cat"));
        assertTrue(palindrome.isPalindromeRec("redivider"));
        assertFalse(palindrome.isPalindromeRec("tuberculosis"));
        assertTrue(palindrome.isPalindromeRec("noon"));
        assertTrue(palindrome.isPalindromeRec("tattarrattat"));
        assertTrue(palindrome.isPalindrome("tattarrattat"));
    }

    @Test
    public void testIsPalindromeCC(){
        OffByOne cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("summer", cc));
        assertTrue(palindrome.isPalindrome("adeb", cc));
        assertFalse(palindrome.isPalindrome("mountain", cc));
    }
}