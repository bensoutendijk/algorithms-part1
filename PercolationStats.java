/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int n;
    private double[] stats;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Trials or N cannot be less than or equal to 0.");

        this.n = n;

        this.stats = new double[trials];

        for (int i = 0; i < trials; i++) {
            stats[i] = this.simulate();
        }
    }

    private double simulate() {
        Percolation p = new Percolation(n);
        double result = 0.0;

        while (!p.percolates()) {
            int row = StdRandom.uniform(n) + 1;
            int col = StdRandom.uniform(n) + 1;

            p.open(row, col);
        }


        int openSites = p.numberOfOpenSites();

        result = (0.0 + openSites) / (n * n);

        return result;
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(stats.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(stats.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);


        PercolationStats percolationStats = new PercolationStats(n, trials);

        System.out.println(String.format("mean = %s", percolationStats.mean()));
        System.out.println(String.format("stddev = %s", percolationStats.stddev()));


        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", "
                                   + percolationStats.confidenceHi() + "]");
    }
}
