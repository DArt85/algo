/**
 * 
 */

/**
 * @author a.dobrynin
 *
 */
public class ParticleGenerator {
		
	public enum Cfg {
		Mass,
		Radius,
		Speed,
		Coords
	}
	
	private GeneratingFunction gfMass;
	private GeneratingFunction gfRadius;
	private GeneratingFunction gfSpeed;
	private GeneratingFunction gfCoords;
	
	public ParticleGenerator() {
	}
	
	public void setGeneratingFunction(Cfg type, GeneratingFunction gf) {
		switch (type) {
		case Mass:
			gfMass = gf;
			break;
		case Radius:
			gfRadius = gf;
			break;
		case Speed:
			gfSpeed = gf;
			break;
		case Coords:
			gfCoords = gf;
			break;
		default:
			throw new IllegalArgumentException("wrong configuration type");
		}
	}
	
	public void makeParticle(Particle p) {
		if ((gfMass == null) || (gfRadius == null) || (gfSpeed == null) || (gfCoords == null)) {
			throw new NullPointerException("some particle generating function is not set");
		}
		p.setMass(gfMass.getValue());
		p.setRadius(gfRadius.getValue());
		double vx = gfSpeed.getValue();
		double vy = gfSpeed.getMax();
		vy = Math.sqrt(vy*vy - vx*vx);
		p.setSpeed(vx,  vy);
		p.setCoords(gfCoords.getValue(), gfCoords.getValue());
	}
}
