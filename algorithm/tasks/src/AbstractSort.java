/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public abstract class AbstractSort<T extends Comparable<T>> extends Helper<T> {
	
	public abstract void sort();	
	public abstract String name();
}
