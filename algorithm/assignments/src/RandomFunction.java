import java.util.Random;

/**
 * 
 */

/**
 * @author Tigra
 *
 */
public class RandomFunction implements GeneratingFunction {

	private Random rnd;
	private double range;
	private double minV;
	private double maxV;
	
	public RandomFunction(double min, double max) {
		rnd = new Random();
		range = max - min;
		minV = min;
		maxV = max;
	}
	
	/* (non-Javadoc)
	 * @see GeneratingFunction#getValue()
	 */
	@Override
	public double getValue() {
		return (minV + range * rnd.nextDouble());
	}

	/* (non-Javadoc)
	 * @see GeneratingFunction#getMax()
	 */
	@Override
	public double getMax() {
		return maxV;
	}

	/* (non-Javadoc)
	 * @see GeneratingFunction#getMin()
	 */
	@Override
	public double getMin() {
		return minV;
	}

}
