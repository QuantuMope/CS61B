package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Inputs are not a valid integer.");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return 2.2;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return 2.2;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return 2.2;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return 2.2;
    }
}
