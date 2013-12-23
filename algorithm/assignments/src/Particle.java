/**
 * 
 */

/**
 * @author a.dobrynin
 *
 */
public class Particle {

	private double radius;
	private double vX, vY;
	private double rX, rY;
	
	public Particle() {
		radius = -1;
		vX = -1; vY = -1;
		rX = -1; rY = -1;
	}
	
	/*
	 * Setters
	 */
	
	public void setRadius(double r) {
		radius = r;
	}
	
	public void setSpeed(double vx, double vy) {
		vX = vx;
		vY = vy;
	}
	
	public void setCoords(double x, double y) {
		rX = x;
		rY = y;
	}
	
	public void move(double dt) {
		// check for collision with walls
		if ((rX + vX*dt < radius) || (rX + vX*dt > 1 - radius)) vX = -vX;
		if ((rY + vY*dt < radius) || (rY + vY*dt > 1 - radius)) vY = -vY;
		rX += vX*dt;
		rY += vY*dt;
	}
	
	public void draw() {
		StdDraw.filledCircle(rX, rY, radius);
	}
}
