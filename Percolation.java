/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private boolean[] sites;
    private WeightedQuickUnionUF uf;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("N cannot be less than or equal to 0");

        int numberOfSites = 2 + n * n;

        this.n = n;
        this.sites = new boolean[numberOfSites];

        // create a single union-find structure to represent the grid
        this.uf = new WeightedQuickUnionUF(numberOfSites);

        for (int i = 0; i < numberOfSites; i++) {
            if (i == 0 || i == numberOfSites - 1) {
                this.sites[i] = true;
            }

            else {
                this.sites[i] = false;
            }
        }
    }

    // gets the index of the site within the grid
    private int getIndex(int row, int col) {
        return (this.n * (row - 1)) + (col - 1) + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1)
            throw new IllegalArgumentException("Row or column cannot be less than 1");

        if (row > this.n || col > this.n)
            throw new IllegalArgumentException("Row or column cannot be greater than N");

        if (this.isOpen(row, col)) {
            return;
        }

        int index = this.getIndex(row, col);

        // Check site above
        if (row == 1) { // Is top row, then connect to virtual top site
            this.uf.union(index, 0);
        }
        else if (this.isOpen(row - 1, col)) {
            this.uf.union(index, this.getIndex(row - 1, col));
        }

        // Check site below
        if (row == n) { // Is bottom row, then connect to virtual bottom site
            this.uf.union(index, this.sites.length - 1);
        }
        else if (this.isOpen(row + 1, col)) {
            this.uf.union(index, this.getIndex(row + 1, col));
        }


        // Check site to left
        if (col != 1 && this.isOpen(row, col - 1)) {
            this.uf.union(index, this.getIndex(row, col - 1));
        }

        // Check site to right
        if (col != n && this.isOpen(row, col + 1)) {
            this.uf.union(index, this.getIndex(row, col + 1));
        }

        this.sites[index] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1)
            throw new IllegalArgumentException("Row or column cannot be less than 1");

        if (row > this.n || col > this.n)
            throw new IllegalArgumentException("Row or column cannot be greater than N");

        int index = this.getIndex(row, col);
        return this.sites[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1)
            throw new IllegalArgumentException("Row or column cannot be less than 1");

        if (row > this.n || col > this.n)
            throw new IllegalArgumentException("Row or column cannot be greater than N");

        int initialSite = 0;
        int destinationSite = this.getIndex(row, col);

        return this.uf.find(initialSite) == this.uf.find(destinationSite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;

        for (int i = 1; i < this.sites.length - 1; i++) {
            if (this.sites[i])
                count++;
        }

        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        int initialSite = 0;
        int destinationSite = n * n + 1;

        return this.uf.find(initialSite) == this.uf.find(destinationSite);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
