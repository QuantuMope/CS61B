public class Palindrome {

    /** Transforms string to a Deque of characters and returns it.
     */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> output = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            output.addFirst(word.charAt(i));
        }
        return output;
    }

}
