public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int width = grid[0].length;
        int height = grid.length;
        int size = width*height;
        UnionFind test = new UnionFind(size);
        int counter = 0;
        for (int[] row : grid) {
            for (int point : row) {

                if (point == 0) {
                    assert true;
                } else {
                    // code
                }
            }
        }
        return null;
    }
}
