/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> {

	InsertionSort(T[] data) {
		m_data = data;
	}
	
	/* (non-Javadoc)
	 * @see AbstractSort#sort()
	 */
	@Override
	public void sort() {
		for (int i = 1; i < m_data.length; i++) {
			for (int j = i; j > 0; j--) {
				if (lt(j, j - 1)) {
					swap(j, j - 1);
				} else {
					break;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see AbstractSort#name()
	 */
	@Override
	public String name() {
		return "Insertion sort";
	}

}
