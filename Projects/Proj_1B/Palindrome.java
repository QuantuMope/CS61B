public class Palindrome {

    /** Transforms string to a Deque of characters.
     *
     * @param word (String)
     * @return output (Deque)
     */
    public Deque<Character> wordToDeque(String word){
        Deque<Character> output = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++){
            output.addFirst(word.charAt(i));
        }
        return output;
    }

}
