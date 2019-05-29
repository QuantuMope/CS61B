/**
 * Class that checks if two char inputs are off by one int value.
 *
 * @author Andrew Choi
 * Date: 05/17/2019
 */

public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y){
        return (Math.abs(x - y) == 1);
    }
}
