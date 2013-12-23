/**
 * 
 */

/**
 * @author a.dobrynin
 *
 */
public class ParticleGenerator {
		
	public enum Cfg {
		Radius,
		Speed,
		Coords
	}
		
	private GeneratingFunction gfRadius;
	private GeneratingFunction gfSpeed;
	private GeneratingFunction gfCoords;
	
	public ParticleGenerator() {
	}
	
	public void setGeneratingFunction(Cfg type, GeneratingFunction gf) {
		switch (type) {
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
		if (gfRadius == null) {
			throw new NullPointerException("particle generating function is not set");
		}
		p.setRadius(gfRadius.getValue());
		if (gfSpeed == null) {
			throw new NullPointerException("speed generating function is not set");
		}
		double vx = gfSpeed.getValue();
		double vy = gfSpeed.getMax();
		vy = Math.sqrt(vy*vy - vx*vx);
		p.setSpeed(vx,  vy);
		if (gfCoords == null) {
			throw new NullPointerException("coordinate generating function is not set");
		}
		p.setCoords(gfCoords.getValue(), gfCoords.getValue());
	}
}
