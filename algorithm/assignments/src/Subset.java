/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class Subset {

	private static void printSubset(int sampleSize, int sizeConstraint) {
		RandomizedQueue<String> rq = new RandomizedQueue<String>();
		String inp;
		do  {
			inp = StdIn.readString();
			if (!inp.isEmpty()) rq.enqueue(inp);
			StdOut.printf("size: %d\n", rq.size());
			if ((sampleSize > 0) && (sizeConstraint < rq.size())) {
				//StdOut.println(rq.dequeue());
				rq.dequeue();
				//sampleSize--;
			}
		} while (!StdIn.isEmpty());
		
		for (int i = 0; i < sampleSize; i++) {
			StdOut.println(rq.dequeue());
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int sampleSize = Integer.parseInt(args[0]);
		if (sampleSize > 0) {
			/*RandomizedQueue<String> rq = new RandomizedQueue<String>();
			String inp;
			do  {
				inp = StdIn.readString();
				if (!inp.isEmpty()) rq.enqueue(inp);
			} while (!StdIn.isEmpty());
			
			for (int i = 0; i < sampleSize; i++) {
				StdOut.println(rq.dequeue());
			}*/
			printSubset(sampleSize, sampleSize);
		}
	}

}
