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
	private double mass;
	
	private int cCount;
	
	public Particle() {
		radius = -1;
		vX = -1; vY = -1;
		rX = -1; rY = -1;
		mass = -1;
		cCount = 0;
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
	
	public void setMass(double m) {
		mass = m;
	}
	
	/*
	 * Getters
	 */
	
	public int getCollisionsCount() {
		return cCount;
	}
	
	/*
	 * Dynamics
	 */
	
	public void move(double dt) {
		rX += vX*dt;
		rY += vY*dt;
	}
	
	public void draw() {
		StdDraw.filledCircle(rX, rY, radius);
	}
	
	public void bounceOff(Particle p) {
		double dvx = vX - p.vX;
		double dvy = vY - p.vY;
		double drx = rX - p.rX;
		double dry = rY - p.rY;
		double dvdr = dvx*drx + dvy*dry;
		double r = radius + p.radius;
		double J = 2*mass*p.mass*dvdr/(r*r*(mass + p.mass));
		vX += J*drx/mass;
		vY += J*dry/mass;
		p.vX -= J*drx/p.mass;
		p.vY -= J*dry/p.mass;
		cCount++;
		p.cCount++;
	}
	
	public void bounceOffVWall() {
		vX = -vX;
		cCount++;
	}
	
	public void bounceOffHWall() {
		vY = - vY;
		cCount++;
	}
	
	/*
	 * Collision prediction
	 */
	
	public double timeToHit(Particle p) {
		double dvx = vX - p.vX;
		double dvy = vY - p.vY;
		double drx = rX - p.rX;
		double dry = rY - p.rY;
		double dvdr = dvx*drx + dvy*dry;
		if (dvdr >= 0) return Double.POSITIVE_INFINITY;
		double dv2 = dvx*dvx + dvy*dvy;
		double dr2 = drx*drx + dry*dry;
		double r = radius + p.radius;
		double D = dvdr*dvdr - dv2*(dr2 - r*r);
		if (D < 0) return Double.POSITIVE_INFINITY;
		return -(dvdr + Math.sqrt(D))/dv2;
	}
	
	public double timeToHitVWall() {
		if (vX > 0) return (1 - rX - radius) / vX;
		else if (vX < 0) return (radius - rX) / vX;
		else return Double.POSITIVE_INFINITY;
	}
	
	public double timeToHitHWall() {
		if (vY > 0) return (1 - rY - radius) / vY;
		else if (vY < 0) return (radius - rY) / vY;
		else return Double.POSITIVE_INFINITY;
	}
}
