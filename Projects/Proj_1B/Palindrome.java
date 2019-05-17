/**
 * Palindrome class with methods pertaining checking if a String in put is a palindrome.
 * @author Andrew Choi
 * Date: 05/17/2019
 */

public class Palindrome {

    /** Transforms string to a Deque of characters and returns it.
     */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> output = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            output.addLast(word.charAt(i));
        }
        return output;
    }

    /** Checks to see if a String input is a palindrome and returns a bool.
     */
    public boolean isPalindrome(String word){
        Palindrome palindrome = new Palindrome();
        Deque<Character> wordDeque = palindrome.wordToDeque(word);
        for (int i = 0; i < word.length()/2; i++){
            if (wordDeque.get(i) == wordDeque.get(word.length()-1-i)){
                assert true;
            } else {
                return false;
            }
        }
        return true;
    }

    /** Recursive implementation of isPalindrome.
     */
    public boolean isPalindromeRec(String word){
        Palindrome palindrome = new Palindrome();
        Deque<Character> wordDeque = palindrome.wordToDeque(word);
        return isPalindromeRecHelper(wordDeque);
    }

    /** Recursive helper of isPalindromeRec.
     */
    private boolean isPalindromeRecHelper(Deque x){
        if (x.size() == 1 || x.size() == 0){
            return true;
        }
        else if (x.removeFirst() == x.removeLast()){
            return isPalindromeRecHelper(x);
        }
        return false;
    }

    /** isPalindrome method with a character comparator.
     */
    public boolean isPalindrome(String word, CharacterComparator cc){
        Palindrome palindrome = new Palindrome();
        Deque<Character> wordDeque = palindrome.wordToDeque(word);
        return isPalindromeRecHelper(wordDeque, cc);
    }

    /** Recursive helper for isPalindrome method with a character comparator.
     */
    private boolean isPalindromeRecHelper(Deque x, CharacterComparator cc){
        if (x.size() == 1 || x.size() == 0){
            return true;
        }
        else if (cc.equalChars((char) x.removeFirst(), (char) x.removeLast())){
            return isPalindromeRecHelper(x, cc);
        }
        return false;
    }
}


