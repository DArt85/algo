import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * 
 */

/**
 * @author DArt
 *
 */
public class KdTree {

	class Node {
		private Point2D pt;
		private Node left;
		private Node right;
		private int level;
		private RectHV rect;
		
		Node(Point2D p, int lvl, RectHV rt) {
			pt = p;
			left = null;
			right = null;
			level = lvl;
			rect = rt;
		}
	}
	
	private Node root;
	private int count; 
	
	public KdTree() {
		root = null;
		count = 0;
	}
	
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public int size() {
		return count;
	}

	private Node insert(Node n, Point2D p, int lvl) {
		if (n == null) {
			RectHV rt;
			if (lvl == 0) rt = new RectHV(0, 0, 1, 1);
			else {
				rt = null;
			}
			return new Node(p, lvl, rt);
		}
		Comparator<Point2D> cmp = (lvl % 2 == 0) ? Point2D.X_ORDER : Point2D.Y_ORDER; 
		if (cmp.compare(p, n.pt) > 0) {
			n.right = insert(n.right, p, n.level+1);
		} else {
			n.left = insert(n.left, p, n.level+1);
		}
		return n;
	}
	
	public void insert(Point2D p) {
		if (p == null) throw new NullPointerException("insert() called with a null key");;
		root = insert(root, p, 0); // first pane split is vertical
		count++;
	}
	
	public boolean contains(Point2D p) {
		Node n = root;
		while (n != null) {
			int cmp = (n.level % 2 == 0) ? Point2D.X_ORDER.compare(p, n.pt) : Point2D.Y_ORDER.compare(p, n.pt); 
			if (cmp > 0) {
				n = n.right;
			} else {
				if (p.equals(n.pt)) return true;
				n = n.left;
			}
		}
		return false;
	}
	
	private void draw(Node n, Node prnt) {
		if (n == null) return;
		n.pt.draw();
		draw(n.left, n);
		draw(n.right, n);
	}
	
	public void draw() {
		if (isEmpty()) throw new NoSuchElementException("2dTree is empty");
		draw(root, null);
	}
	
	public Iterable<Point2D> range(RectHV rect) {
		return null;
	}
	
	public Point2D nearest(Point2D p) {
		return null;
	}	
}
