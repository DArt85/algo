/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSort<T> {

	public enum SortMethod {
		BubbleSort,
		ShakerSort,
		OddEvenSort,
		BrushSort,
		GnomeSort
	}
	
	private SortMethod m_meth;
	
	public BubbleSort(T[] data) {
		m_data = data;
		m_meth = SortMethod.BubbleSort;
	}
	
	public void setSortMethod(SortMethod method) {
		m_meth = method;
	}
	
	/* (non-Javadoc)
	 * @see AbstractSort#sort()
	 */
	@Override
	public void sort() {
		switch (m_meth) {
		case BubbleSort:
			bubbleSort();
			break;
		case ShakerSort:
			shakerSort();
			break;
		case OddEvenSort:
			oddEvenSort();
			break;
		case BrushSort:
			brushSort();
			break;
		case GnomeSort:
			gnomeSort();
			break;
		default:
			break;
		}
	}

	private void bubbleSort() {
		int end = m_data.length;
		int swaps = -1;
		while ((end-- > 0) && (m_swaps > swaps)) {
			swaps = m_swaps;
			for (int i = 0; i < end; i++)
				if (lt(i + 1, i)) swap(i, i + 1);
		}
	}
	
	private void shakerSort() {
		int start = 0;
		int end = m_data.length - 1;
		while (start < end) {
			int swaps = m_swaps;
			for (int i = start; i < end; i++)
				if (lt(i + 1, i)) swap(i, i + 1);
			end--;
			if (swaps == m_swaps) break;
			swaps = m_swaps;
			for (int i = end; i > start; i--)
				if (lt(i, i - 1)) swap(i, i - 1);
			start++;
			if (swaps == 0) break;
		}
	}
	
	private void oddEvenSort() {
		int swaps = -1;
		final int end = m_data.length - 1;
		while (m_swaps > swaps) {
			swaps = m_swaps;
			for (int i = 0; i < end; i += 2)
				if (lt(i + 1, i)) swap(i, i + 1);
			for (int i = 1; i < end; i += 2)
				if (lt(i + 1, i)) swap(i, i + 1);
		}
	}
	
	private void brushSort() {
		final double scaleFactor = 1.247;
		int step = (int)(m_data.length / scaleFactor);
		while (step > 0) {
			for (int i = 0; i < (m_data.length - step); i++)
				if (lt(i + step, i)) swap(i, i + step);
			step = (int)(step / scaleFactor);
		}
	}
	
	private void gnomeSort() {
		int pos = 1;
		while (pos < m_data.length) {
			if (lte(pos - 1, pos)) pos++;
			else {
				swap(pos - 1, pos);
				if (pos > 1) pos--;
			}
		}
	}

	/* (non-Javadoc)
	 * @see AbstractSort#name()
	 */
	@Override
	public String name() {
		return "Bubble sort";
	}
}
