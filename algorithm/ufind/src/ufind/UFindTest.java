/**
 * 
 */
package ufind;

import edu.princeton.cs.introcs.StdOut;

/**
 * @author Arthur D
 *
 */
public class UFindTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleUFind suf = new SimpleUFind(10, 10);
		suf.plotGrid(0.6);
		suf.writeToFile("grid0.bin");
		StdOut.printf("Connected (1, 2) and (4, 5) ? %s", suf.isConnected(1, 2, 9, 9));
	}
}
