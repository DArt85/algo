/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

	public enum Partition {
		Standard,
		Dijkstra,
		DualPivot
	}
	
	private Partition m_partMethod;

	public QuickSort(T[] data) {
		m_data = data;
		m_partMethod = Partition.Standard;
	}
	
	public void setPartition(Partition method) {
		m_partMethod = method;
	}
	
	public T select(int k) {
		if (k >= m_data.length) return null;
		int low = 0, high = m_data.length - 1;
		while (high > low) {
			int j = partition(low, high);
			if (j > k) high = j - 1;
			else if (j < k) low = j + 1;
		}
		return m_data[k];
	}
	
	private int partition(int low, int high) {
		int i = low, j = high + 1;
		while (true) {
			while ((i < high) && lt(++i, low));
			while ((j > low) && lt(low, --j));
			
			if (i >= j) {
				swap(j, low);
				break;
			} else {
				swap(i, j);
			}
		}
		return j;
	}

	private void sort(int low, int high) {
		if (high <= low) return;
		int j = partition(low, high);
		sort(low, j - 1);
		sort(j + 1, high);
	}

	private void dijkstraSort(int low, int high) {
		if (low >= high) return;
		int i = low + 1, lt = low, gt = high;
		T val = m_data[low];
		while (i <= gt) {
			int cmp = val.compareTo(m_data[i]); 
			if (cmp > 0) swap(i++, lt++);
			else if (cmp < 0) swap(i, gt--);
			else i++;
			m_compares++;
		}
		
		dijkstraSort(low, lt - 1);
		dijkstraSort(gt + 1, high);
	}
	
	private void dualPivotSort(int low, int high) {
		if (low >= high) return;
		if (lt(high, low)) swap(low, high);
		int i = low + 1, lt = low + 1, gt = high - 1;
		while (i <= gt) {
			if (lt(i, low)) swap(i++, lt++);
			else if (lt(high, i)) swap(i, gt--);
			else i++;
		}
		swap(low, --lt);
		swap(high, ++gt);
		
		dualPivotSort(low, lt - 1);
		dualPivotSort(lt + 1, gt - 1);
		dualPivotSort(gt + 1, high);
	}
	
	/* (non-Javadoc)
	 * @see AbstractSort#sort()
	 */
	@Override
	public void sort() {
		switch (m_partMethod) {
		case Standard:
			sort(0, m_data.length - 1);
			break;
		case Dijkstra:
			dijkstraSort(0, m_data.length - 1);
			break;
		case DualPivot:
			dualPivotSort(0, m_data.length - 1);
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see AbstractSort#name()
	 */
	@Override
	public String name() {
		return "Quick sort";
	}
}
