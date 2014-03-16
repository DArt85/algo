/**
 * 
 */

/**
 * @author Tigra
 *
 */
public class GaussianFunction implements GeneratingFunction {

	private double median;
	private double sigma;
	private double[] values;
	
	public GaussianFunction(int N, double med, double sigm) {
		median = med;
		sigma = sigm;
		double delta = 2*sigma*Math.log((double)N)/N*Math.log(2.0);
		values = new double[N];
		final double f = 1/(Math.sqrt(2*Math.PI)*sigma);
		int count = 0;
		StdOut.printf("Delta %f, f = %f\n", delta, f);
		for (double rng = (median - sigma + delta/2); rng < (median + sigma); rng += delta) {
			StdOut.printf("Func %f\n", Math.pow(Math.E, -(rng - median)*(rng - median)/(2*sigma*sigma)));
			int num = (int)(N*f*Math.pow(Math.E, -(rng - median)*(rng - median)/(2*sigma*sigma)));
			StdOut.printf("Range [%f, %f] - %d values\n", rng - delta/2, rng + delta/2, num);
			for (int i = 0; i < num; i++, count++) {
				values[count] = Math.random()*delta + rng - delta/2;
				StdOut.printf("value[%d] = %f\n", count, values[count]);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see GeneratingFunction#getValue()
	 */
	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see GeneratingFunction#getMax()
	 */
	@Override
	public double getMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see GeneratingFunction#getMin()
	 */
	@Override
	public double getMin() {
		// TODO Auto-generated method stub
		return 0;
	}

	public static void main(String[] args) {
		//GaussianFunction gf = new GaussianFunction(10, 0.5, 0.2);
		StdOut.print("Done\n");
	}
}
