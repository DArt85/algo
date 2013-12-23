/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class HeapSort<T extends Comparable<T>> extends AbstractSort<T> {
	
	HeapSort(T[] data) {
		m_data = data;
	}
	
	/* (non-Javadoc)
	 * @see AbstractSort#sort()
	 */
	@Override
	public void sort() {
		for (int i = m_data.length/2; i >= 1; i--) sink(i, m_data.length);
		int i = m_data.length;
		while (i > 1) {
			swap(0, i-1);
			sink(1, --i);
		}
	}
	
	private void sink(int index, int max) {
		int j = 2*index;
		while (j <= max) {
			if ((j < max) && !lt(j, j-1)) j++;
			if (!lt(j-1, index-1)) swap(index-1, j-1);
			else break;
			index = j; j *= 2;
		}
	}

	/* (non-Javadoc)
	 * @see AbstractSort#name()
	 */
	@Override
	public String name() {
		return "Heap sort";
	}

}
