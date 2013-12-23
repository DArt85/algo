import java.util.Arrays;

/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class Brute {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
		PointReader ptRead = new PointReader(new In(args[0]));
		int N = ptRead.getSize();
		Point[] pts = new Point[N];
		Point[] sortPts = new Point[N];
		int index = 0;
		for (Point pt : ptRead) {
			pts[index] = sortPts[index] = pt;
			pts[index++].draw();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				for (int k = j + 1; k < N; k++) {
					if (pts[i].slopeTo(pts[j]) != pts[i].slopeTo(pts[k])) continue;
					for (int l = k + 1; l < N; l++) {
						if (pts[i].slopeTo(pts[j]) == pts[i].slopeTo(pts[l])) {
							Point linePts[] = new Point[]{pts[i], pts[j], pts[k], pts[l]};
							Arrays.sort(linePts);
							StdOut.printf("%s -> %s -> %s -> %s\n", linePts[0], linePts[1], linePts[2], linePts[3]);
							linePts[0].drawTo(linePts[linePts.length - 1]);
						}
					}
				}
			}
		}
	}
}
