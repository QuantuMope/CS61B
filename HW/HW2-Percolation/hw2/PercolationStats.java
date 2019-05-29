package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 *
 * @author Andrew Choi
 * Date: 05/26/2019
 */

public class PercolationStats {

    private double[] trials;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        Percolation percolation;
        int randomOpenRow;
        int randomOpenCol;
        trials = new double[T];
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("Inputs are not a valid integer.");
        }
        for (int reps = 0; reps < T; reps++) {
            percolation = pf.make(N);
            while (!percolation.percolates()) {
                randomOpenRow = StdRandom.uniform(N);
                randomOpenCol = StdRandom.uniform(N);
                percolation.open(randomOpenRow, randomOpenCol);
            }
            trials[reps] = percolation.numberOfOpenSites();
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(trials);
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(trials);
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - (1.96 * stddev())/Math.sqrt(((double) trials.length));
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + (1.96 * stddev())/Math.sqrt(((double) trials.length));
    }

    public static void main(String[] args) {
        int gridSize = 40;
        int trials = 1000;
        PercolationFactory pf = new PercolationFactory();
        PercolationStats test = new PercolationStats(gridSize, trials, pf);
        System.out.println(String.format("For a grid %s x %s after %s trials:", gridSize, gridSize, trials));
        System.out.println("The mean is: " + test.mean());
        System.out.println("The standard deviation is: " + test.stddev());
        System.out.println("The 95% lower confidence interval is: " + test.confidenceLow());
        System.out.println("The 95% high confidence interval is: " + test.confidenceHigh());
    }
}
