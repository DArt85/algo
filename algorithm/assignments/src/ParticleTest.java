/**
 * 
 */

/**
 * @author a.dobrynin
 *
 */
public class ParticleTest {
	
	public static void main(String[] args) {
		ParticleGenerator gnr = new ParticleGenerator();
		gnr.setGeneratingFunction(ParticleGenerator.Cfg.Radius, new RandomFunction(0.005, 0.01));
		gnr.setGeneratingFunction(ParticleGenerator.Cfg.Speed, new RandomFunction(0, 0.5));
		gnr.setGeneratingFunction(ParticleGenerator.Cfg.Coords, new RandomFunction(0.01, 0.99));
		
		Particle[] pts = new Particle[20];
		for (int i = 0; i < 20; i++) {
			pts[i] = new Particle();
			gnr.makeParticle(pts[i]);
		}
		
		StdDraw.setScale(0, 1);
		while (true) {
			StdDraw.clear();
			for (Particle pt : pts) {
				pt.move(0.1);
				pt.draw();
			}
		}
	}
}
