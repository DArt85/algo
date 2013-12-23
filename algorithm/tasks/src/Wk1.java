/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class Wk1 {
	
	public static final int DOUBLING_ESTIMATE = 1;
	
	private static double doublingEstimate(int st, int steps, boolean verbose) {
		int n = st;
		Stopwatch timer = new Stopwatch();
		double startTime = 0;
		double prevRunTime = 0;
		double curRunTime = 0;
		double lgRatio = 0;
		// dummy run to fix settling
		Timing.trial(n, 777280);
		for (int i = 0; i < steps; i++) {
			startTime = timer.elapsedTime();
			Timing.trial(n, 777280);
			curRunTime = timer.elapsedTime() - startTime;
			if (prevRunTime != 0) {
				lgRatio = Math.log(curRunTime / prevRunTime) / Math.log(2);
			}
			if (verbose) {
				StdOut.printf("Step %d: input size %d, time %.3f ms, lg ratio %.2f\n", i, n, curRunTime, lgRatio);
			}
			prevRunTime = curRunTime;
			n *= 2;
		}
		
		return lgRatio;
	}
	
	public static int run(int task) {
		int rc = 0;
		switch (task) {
		case DOUBLING_ESTIMATE:
			doublingEstimate(250, 6, true);
			break;
		default:
			StdOut.printf("No such task in week 1: %d\n", task);
			rc = task;
		}
		
		return rc;
	}
}
