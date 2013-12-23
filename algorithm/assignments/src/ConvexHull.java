import java.util.Arrays;

/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class ConvexHull {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		
		PointReader ptRead = new PointReader(new In(args[0]));
		Point[] pts = new Point[ptRead.getSize()+1];
		int index = 0;
		for (Point pt : ptRead) {
			pts[index] = pt;
			pts[index++].draw();
		}
		
		// find min point (min y, but max x)
		Point minPt = pts[0];
		for (int i = 0; i < pts.length - 1; i++) {
			if (cmp(pts[i], minPt) < 0) minPt = pts[i];
		}
		pts[pts.length - 1] = minPt;
		
		Arrays.sort(pts, 0, pts.length - 1, minPt.ANGLE_ORDER);
		int count = 1;
		for (int i = 2; i < pts.length; i++) {
			while (isCCW(pts[count - 1], pts[count], pts[i]) <= 0) {
				if (count > 1) count--;
				else if (i == pts.length) break;
				else i++;
			}
			swap(pts, ++count, i);
		}
		for (int i = 0; i < count - 1; i++) pts[i].drawTo(pts[i+1]);
		pts[count-1].drawTo(pts[0]);
	}

	private static int isCCW(Point a, Point b, Point c) {
		double area = (b.X() - a.X())*(c.Y() - a.Y()) - (b.Y() - a.Y())*(c.X() - a.X());
		if (area > 0) return 1;
		else if (area < 0) return -1;
		else return 0;
	}
	
	private static int cmp(Point a, Point b) {
		if ((a.Y() < b.Y()) || (a.Y() == b.Y()) && (a.X() > b.X())) {
        	return -1;
        } else {
        	return 1;
        }
	}
	
	private static void swap(Point[] arr, int i, int j) {
		Point tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
