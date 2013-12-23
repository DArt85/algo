/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {
	
	MergeSort(T[] data) {
		m_data = data;
	}
	
	private boolean isSorted(T[] arr, int low, int high) {
		boolean rc = true;
		for (int i = low; i < high; i++) {
			if (!lt(arr[i], arr[i + 1])) {
				rc = false;
				break;
			}
		}
		return rc;
	}
	
	private void merge(T[] auxArr, int low, int mid, int high) {
		assert isSorted(m_data, low, mid);
		assert isSorted(m_data, mid + 1, high);
		
		for (int i = low; i <= high; i++) {
			auxArr[i] = m_data[i];
		}
		
		int i = low;
		int j = mid + 1;
		for (int ind = low; ind <= high; ind++) {
			if (i > mid) {
				m_data[ind] = auxArr[j++];
			} else if (j > high) {
				m_data[ind] = auxArr[i++];
			} else if (lt(auxArr[i], auxArr[j])) {
				m_data[ind] = auxArr[i++];
			} else {
				m_data[ind] = auxArr[j++];
			}
			m_swaps++;
		}
	}
	
	private void sort(T[] auxArr, int low, int high) {
		if (low >= high) return;
		int mid = low + (high - low) / 2;
		sort(auxArr, low, mid);
		sort(auxArr, mid + 1, high);
		if (lt(m_data[mid], m_data[mid + 1])) return;
		merge(auxArr, low, mid, high);
	}
	
	/* (non-Javadoc)
	 * @see AbstractSort#sort()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sort() {
		T[] auxArr = (T[])new Comparable[m_data.length];
		sort(auxArr, 0, m_data.length - 1);
	}

	/* (non-Javadoc)
	 * @see AbstractSort#name()
	 */
	@Override
	public String name() {
		return "Merge sort";
	}
}
