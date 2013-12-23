/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {
	
	SelectionSort(T[] data) {
		m_data = data;
	}
	
	/* (non-Javadoc)
	 * @see AbstractSort#sort(java.lang.Object[])
	 */
	@Override
	public void sort() {
		for (int i = 0; i < m_data.length; i++) {
			for (int j = i + 1; j < m_data.length; j++) 
				if (lt(j, i)) swap(i, j);
		}
	}

	/* (non-Javadoc)
	 * @see AbstractSort#name()
	 */
	@Override
	public String name() {
		return "Selection sort";
	}
	
}
