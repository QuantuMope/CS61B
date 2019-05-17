/**
 * Class that checks if two char inputs are off by n int value.
 *
 * @author Andrew Choi
 * Date: 05/17/2019
 */

public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int x){
        this.N = x;
    }

    @Override
    public boolean equalChars(char x, char y){
        return (Math.abs(x - y) == N);
    }


}
