/**
 * @author ardobryn
 * @file   PercolationStats.java
 */

/**
 * Implementation of statistical experiments with percolation through square grid.
 */
public class PercolationStats {
	
	private int m_gridDim;
	private int m_numExp;
	private double[] m_openSitesFrac;
	private double m_meanVal;
	
	public PercolationStats(int N, int T) {
		if (N <= 0) throw new IllegalArgumentException("Grid size should be > 0!");
		if (T <= 0) throw new IllegalArgumentException("Number of experiments should be > 0!");
		
		m_gridDim = N;
		m_numExp = T;
		m_openSitesFrac = new double[T];
	}
	
	public double mean() {
		int curX;
		int curY;
		int openSitesCount;
		Percolation percExp;
		
		m_meanVal = 0;
		if (m_gridDim != 1) {
			for (int i = 0; i < m_numExp; i++) {
				openSitesCount = 0;
				percExp = new Percolation(m_gridDim);
				while (!percExp.percolates()) {
					do {
						curX = StdRandom.uniform(1, m_gridDim + 1);
						curY = StdRandom.uniform(1, m_gridDim + 1);
					} while (percExp.isOpen(curX,  curY));
					percExp.open(curX,  curY);
					openSitesCount++;
				}
				m_openSitesFrac[i] = (double)openSitesCount / (m_gridDim * m_gridDim); 
				m_meanVal += m_openSitesFrac[i];
			}
		}
		
		m_meanVal /= m_numExp;
		return m_meanVal;
	}
		
	public double stddev() {
		if (m_numExp == 1) return Double.NaN;
		
		double diffSum = 0;
		for (double val : m_openSitesFrac) diffSum += (val - m_meanVal) * (val - m_meanVal);
		return diffSum / (m_numExp - 1);
	}
	
	public static void main(String[] args) {
		if (args.length < 2) {
			StdOut.printf("Not enough arguments!\n");
			return;
		}
		
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats pstat = new PercolationStats(N, T);
		
		double mean = pstat.mean();
		double stddev = pstat.stddev();
		double interv = 1.96 * stddev / Math.sqrt(T);
		
		StdOut.printf("mean                    = %f\n", mean);
		StdOut.printf("stddev                  = %f\n", stddev);
		StdOut.printf("95%% confidence interval = %f, %f\n", mean - interv, mean + interv);
	}
}
