/**
 * 
 */

/**
 * @author a.dobrynin
 *
 */
public class ParticleTest {
	
	public enum Statistics {
		Random,
		Gaussian
	}
	
	private ParticleGenerator pgen;
	private MinPQ<CollisionEvent> pq;
	private double time;
	private Particle[] pts;
	
	public ParticleTest(int N) {
		pgen = new ParticleGenerator();
		pq = new MinPQ<CollisionEvent>();
		pts = new Particle[N];
		time = 0;
	}
	
	public void init(Statistics stat) {
		// initialize particles
		pgen.setGeneratingFunction(ParticleGenerator.Cfg.Mass, genFuncFactory(stat, new double[]{0, 1}));
		pgen.setGeneratingFunction(ParticleGenerator.Cfg.Radius, genFuncFactory(stat, new double[]{0.005, 0.01}));
		pgen.setGeneratingFunction(ParticleGenerator.Cfg.Speed, genFuncFactory(stat, new double[]{-0.1, 0.1}));
		pgen.setGeneratingFunction(ParticleGenerator.Cfg.Coords, genFuncFactory(stat, new double[]{0.01, 0.99}));
		
		for (int i = 0; i < pts.length; i++) {
			pts[i] = new Particle();
			pgen.makeParticle(pts[i]);
		}
		
		// initialize graphics
		StdDraw.setScale(0, 1);
		StdDraw.show(0);
	}
	
	public void simulate() {
		pq.insert(new CollisionEvent(time, null, null));
		for (Particle p : pts) predict(p);
		
		while (!pq.isEmpty()) {
			CollisionEvent e = pq.delMin();
			StdOut.printf("Event at %f\n", e.time);
			if (!e.isValid()) {StdOut.printf("Event is not valid\n", e.time); continue;}
			
			Particle pa = e.p1;
			Particle pb = e.p2;
			
			// update positions of all particles because we know that there is no collisions before the event
			for (Particle p : pts) p.move(e.time - time);
			time = e.time;
			
			if ((pa != null) && (pb != null)) pa.bounceOff(pb);
			else if ((pa == null) && (pb != null)) {pb.bounceOffHWall();StdOut.printf("Bounce off horizontal wall\n");}
			else if ((pa != null) && (pb == null)) {pa.bounceOffVWall();StdOut.printf("Bounce off vertical wall\n");}
			else redraw();
			
			if (pa != null) predict(pa);
			if (pb != null) predict(pb);
		}
	}
	
	private void predict(Particle p) {
		for (Particle pt : pts) {
			double dt = p.timeToHit(pt);
			if (dt < Double.POSITIVE_INFINITY) {
				pq.insert(new CollisionEvent(time + dt, p, pt));
			}
		}
		pq.insert(new CollisionEvent(time + p.timeToHitHWall(), null, p));
		pq.insert(new CollisionEvent(time + p.timeToHitVWall(), p, null));
	}
	
	private void redraw() {
		StdOut.printf("redraw(time=%f)\n", time);
		StdDraw.clear();
		for (Particle pt : pts) pt.draw();
		StdDraw.show(20);
		pq.insert(new CollisionEvent(time + 0.1, null, null));
	}
	
	private GeneratingFunction genFuncFactory(Statistics stat, double[] params) {
		GeneratingFunction gfunc = null;
		switch (stat) {
		case Random:
			if (params.length == 2) {
				gfunc = new RandomFunction(params[0], params[1]);
			}
			break;
		case Gaussian:
			throw new UnsupportedOperationException("Gaussian statistics is not implemented");
		default:
			throw new IllegalArgumentException("unknown statistics type");
		}
		return gfunc;
	}
	
	public static void main(String[] args) {
		ParticleTest pt = new ParticleTest(50);
		pt.init(Statistics.Random);
		pt.simulate();
	}
}
