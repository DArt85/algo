import java.util.Arrays;

/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class Fast {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
		PointReader ptRead = new PointReader(new In(args[0]));
		Point[] pts = new Point[ptRead.getSize()];
		Point[] sortPts = new Point[ptRead.getSize()];
		int index = 0;
		for (Point pt : ptRead) {
			pts[index] = sortPts[index] = pt;
			pts[index++].draw();
		}
		
		for (Point pt : pts) {
			Arrays.sort(sortPts, pt.SLOPE_ORDER);
			int matchCount = 1;
			double slp = pt.slopeTo(sortPts[0]); // first point is the origin itself
			for (int i = 1; i < sortPts.length; i++) {
				if (slp == pt.slopeTo(sortPts[i])) {
					matchCount++;
					continue;
				} else {
					if (matchCount >= 3) printIfInOrder(pt, sortPts, i, matchCount);
				}
				slp = pt.slopeTo(sortPts[i]);
				matchCount = 1;
			}
			if (matchCount >= 3) printIfInOrder(pt, sortPts, sortPts.length, matchCount);
		}
	}
	
	public static void printIfInOrder(Point pt, Point[] pts, int index, int count) {
		Arrays.sort(pts, index - count, index);
		if (pt.compareTo(pts[index - count]) <= 0) {
			pt.drawTo(pts[index - 1]);
			StdOut.printf("%s -> ", pt);
			for (int j = index - count; j < (index - 1); j++)
				StdOut.printf("%s -> ", pts[j]);
			StdOut.printf("%s\n", pts[index - 1]);
		}
	}
}
