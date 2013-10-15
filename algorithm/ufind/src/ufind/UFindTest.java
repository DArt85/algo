/**
 * 
 */
package ufind;

import edu.princeton.cs.introcs.StdOut;

/**
 * @author Tigra
 *
 */
public class UFindTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleUFind suf = new SimpleUFind(10, 10);
		suf.plotGrid(0.6);
		StdOut.printf("Connected (1, 2) and (4, 5) ? %s", suf.isConnected(1, 2, 9, 9));
	}
}
