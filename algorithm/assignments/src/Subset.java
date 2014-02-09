/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class Subset {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int sampleSize = Integer.parseInt(args[0]);
		if (sampleSize > 0) {
			RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String inp;
			while (!StdIn.isEmpty()) {
				inp = StdIn.readString();
				rq.enqueue(inp);
			}

			for (int i = 0; i < sampleSize; i++) {
				StdOut.println(rq.dequeue());
			}
		}
	}

}
