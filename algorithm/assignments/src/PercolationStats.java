/**
 * @author ardobryn
 * @file   PercolationStats.java
 */

/**
 * Implementation of statistical experiments with percolation through square grid.
 */
public class PercolationStats {

	private int gridDim;
	private int numExp;
	private double[] openSitesFrac;
	private double meanVal;
	
	public PercolationStats(int N, int T) {
		if (N <= 0) throw new IllegalArgumentException("Grid size should be > 0!");
		if (T <= 0) throw new IllegalArgumentException("Number of experiments should be > 0!");
		
		gridDim = N;
		numExp = T;
		openSitesFrac = new double[T];
		meanVal = 0;
	}
	
	public double mean() {
		int curX;
		int curY;
		int openSitesCount;
		Percolation percExp;
		
		meanVal = 0;
		if (gridDim != 1) {
			for (int i = 0; i < numExp; i++) {
				openSitesCount = 0;
				percExp = new Percolation(gridDim);
				while (!percExp.percolates()) {
					do {
						curX = StdRandom.uniform(1, gridDim + 1);
						curY = StdRandom.uniform(1, gridDim + 1);
					} while (percExp.isOpen(curX,  curY));
					percExp.open(curX,  curY);
					openSitesCount++;
				}
				openSitesFrac[i] = (double) openSitesCount / (gridDim * gridDim); 
				meanVal += openSitesFrac[i];
			}
		}
		
		meanVal /= numExp;
		return meanVal;
	}
		
	public double stddev() {
		if (numExp == 1) return Double.NaN;
		if (meanVal == 0) meanVal = mean();
		
		double diffSum = 0;
		for (double val : openSitesFrac) diffSum += (val - meanVal) * (val - meanVal);
		return Math.sqrt(diffSum / (numExp - 1));
	}
	
	public double confidenceLo() {
		if (meanVal == 0) meanVal = mean();
		double stddev = stddev();
		double interv = 1.96 * stddev / Math.sqrt(numExp);
		return meanVal - interv;
	}
	
	public double confidenceHi() {
		if (meanVal == 0) meanVal = mean();
		double stddev = stddev();
		double interv = 1.96 * stddev / Math.sqrt(numExp);
		return meanVal + interv;
	}
	
	public static void main(String[] args) {
		if (args.length < 2) {
			StdOut.printf("Not enough arguments!\n");
			return;
		}

		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats pstat = new PercolationStats(N, T);

		StdOut.printf("mean                    = %f\n", pstat.mean());
		StdOut.printf("stddev                  = %f\n", pstat.stddev());
		StdOut.printf("95%% confidence interval = %f, %f\n", pstat.confidenceLo(), pstat.confidenceHi());
	}
}
