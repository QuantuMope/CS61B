package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 *
 * @author Andrew Choi
 * Date: 05/26/2019
 */

public class Percolation {
    private class Square {
        private boolean open = false;

        public Square() {}

        public Square(String input) {
            if (input.equals("TOP")) {
                open = true;
            }
        }
    }
    private Square[][] grid;
    private WeightedQuickUnionUF quickUnion;
    private WeightedQuickUnionUF percolationChecker;
    private int lengthWidth;
    private int topIndex;
    private int bottomIndex;
    private int openSpots;

    // Create N-by-N grid with all sites initially blocked.
    // Allowed to take N squared time.
    public Percolation(int N) {
        openSpots = 0;
        grid = new Square[N][N];
        lengthWidth = N;
        topIndex = N*N;
        bottomIndex = N*N +1;
        // The +2 spots in the QuickUnion are for the top and bottom connector nodes.
        // Use a percolationChecker that mirrors QuickUnion with the addition of a
        // bottom node connector to prevent backwash.
        quickUnion = new WeightedQuickUnionUF(N*N + 2);
        percolationChecker = new WeightedQuickUnionUF(N*N + 2);
        for (int column = 0; column < N; column++) {
            quickUnion.union(topIndex, column);
            percolationChecker.union(topIndex, column);
            percolationChecker.union(bottomIndex, calc(N-1, column));
        }
        for (int row = 0; row < N; row++) {
            for (int column = 0; column < N; column++) {
                grid[row][column] = new Square();
            }
        }
    }

    // Helper method to translate coordinates to a single int value.
    private int calc(int row, int col) {
        if (row > lengthWidth || col > lengthWidth) {
            throw new IllegalArgumentException("Coordinates are out of range.");
        }
        return lengthWidth * row + col;
    }

    // Helper method to check for open adjacent connections and connect them.
    private void connectOpens(int row, int col) {
        if (col + 1 < lengthWidth) {
            if (grid[row][col+1].open) {
                quickUnion.union(calc(row, col), calc(row, col + 1));
                percolationChecker.union(calc(row, col), calc(row, col + 1));
            }
        }
        if (col - 1 > -1) {
            if (grid[row][col-1].open) {
                quickUnion.union(calc(row, col), calc(row, col - 1));
                percolationChecker.union(calc(row, col), calc(row, col - 1));
            }
        }
        if (row + 1 < lengthWidth) {
            if (grid[row+1][col].open) {
                quickUnion.union(calc(row, col), calc(row + 1, col));
                percolationChecker.union(calc(row, col), calc(row + 1, col));
            }
        }
        if (row - 1 > -1) {
           if (grid[row-1][col].open) {
               quickUnion.union(calc(row, col), calc(row-1, col));
               percolationChecker.union(calc(row, col), calc(row-1, col));
           }
        }
    }

    // Open the site (row, col) if it is not open.
    public void open (int row, int col) {
        if (!isOpen(row, col)) {
            openSpots += 1;
            grid[row][col].open = true;
            connectOpens(row, col);
        }

    }

    // Is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grid[row][col].open;
    }

    // Is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return quickUnion.connected(calc(row, col), topIndex) && grid[row][col].open;
    }

    // Return number of open sites.
    public int numberOfOpenSites() {
        return openSpots;
    }

    // Does the system percolate?
    public boolean percolates() {
        return percolationChecker.connected(topIndex, bottomIndex);
    }

    public static void main(String[] args) {
    }

}
