import java.util.Comparator;

/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class Point implements Comparable<Point> {
		
	// compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
    	public int compare(Point p1, Point p2) {
    		int rc;
    		double slp1 = slopeTo(p1);
    		double slp2 = slopeTo(p2);
    		if (slp1 < slp2) {
    			rc = -1;
    		} else if (slp1 > slp2) {
    			rc = 1;
    		} else {
    			rc = 0;
    		}
			return rc;
		}
    };
    
 // compare points by slope
    public final Comparator<Point> ANGLE_ORDER = new Comparator<Point>() {
    	public int compare(Point p1, Point p2) {
    		int rc;
    		double slp1 = angle(p1);
    		double slp2 = angle(p2);
    		if (slp1 < slp2) {
    			rc = -1;
    		} else if (slp1 > slp2) {
    			rc = 1;
    		} else {
    			rc = 0;
    		}
			return rc;
		}
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        StdDraw.point(x, y);
    }

    public int X() { return x; }
    public int Y() { return y; }
    
    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
    	double res;
    	if (((that.x - x) == 0) && ((that.y - y) == 0)) {
    		res = Double.NEGATIVE_INFINITY;
    	} else if ((that.x - x) == 0) {
			res = Double.POSITIVE_INFINITY;
		} else if ((that.y - y) == 0) {
			if (y != 0) res = (y - y)/y;
			else res = 0;
		} else {
			res = ((double)(that.y - y))/(that.x - x);
		}
		
		return res;
    }
    
    public double angle(Point that) {
    	double res;
    	if (((that.x - x) == 0) && ((that.y - y) == 0)) {
    		res = Double.NEGATIVE_INFINITY;
    	} else if ((that.y - y) == 0) {
			res = Double.POSITIVE_INFINITY;
		} else {
			res = ((double)(x - that.x))/(that.y - y);
		}
		
		return res;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        int rc;
        if ((y < that.y) || (y == that.y) && (x < that.x)) {
        	rc = -1;
        } else if ((y == that.y) && (x == that.x)) {
        	rc = 0;
        } else {
        	rc = 1;
        }
    	return rc;
    }

    // return string representation of this point
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
