/**
 * 
 */

/**
 * @author Tigra
 *
 */
public class CollisionEvent implements Comparable<CollisionEvent> {

	private int count1, count2;

	public double time;
	public Particle p1, p2;
	
	public CollisionEvent(double t, Particle a, Particle b) {
		time = t;
		p1 = a;
		p2 = b;
		count1 = (a != null) ? a.getCollisionsCount() : 0;
		count2 = (b != null) ? b.getCollisionsCount() : 0;
	}
	
	public int compareTo(CollisionEvent e) {
		return (int)(time - e.time);
	}
	
	public boolean isValid() {
		boolean res = true;
		if (p1 != null) res &= (count1 == p1.getCollisionsCount());
		if (p2 != null) res &= (count2 == p2.getCollisionsCount());
		return res;
	}
}
