/**
 * 
 */

/**
 * @author Tigra
 *
 */
public class PointSET {

	private SET<Point2D> pTree;
	
	public PointSET() {
		pTree = new SET<Point2D>();
	}
	
	public boolean isEmpty() {
		return pTree.isEmpty();
	}
	
	public int size() {
		return pTree.size();
	}
	
	public void insert(Point2D p) {
		pTree.add(p);
	}
	
	public boolean contains(Point2D p) {
		return pTree.contains(p);
	}
	
	public void draw() {
		for (Point2D p : pTree) p.draw();
	}
	
	public Iterable<Point2D> range(RectHV rect) {
		Queue<Point2D> res = new Queue<Point2D>();
		for (Point2D pt : pTree) {
			if ((pt.x() <= rect.xmax()) && (pt.x() >= rect.xmin()) &&
				(pt.y() <= rect.ymax()) && (pt.y() >= rect.ymin())) {
				res.enqueue(pt);
			}	
		}
		return res;
	}
	
	public Point2D nearest(Point2D p) {
		double dist;
		Point2D n = null;
		double distMin = Double.MAX_VALUE;
		for (Point2D pt : pTree) {
			dist = p.distanceTo(pt);
			if (dist < distMin) {
				distMin = dist;
				n = pt;
			}
		}
		return n;
	}
}
