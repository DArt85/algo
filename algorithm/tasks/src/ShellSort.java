/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class ShellSort<T extends Comparable<T>> extends AbstractSort<T> {

	ShellSort(T[] data) {
		m_data = data;
	}
	
	/* (non-Javadoc)
	 * @see AbstractSort#sort()
	 */
	@Override
	public void sort() {
		int hSize = 1;
		while (hSize < m_data.length / 3) hSize = 3 * hSize + 1;
		while (hSize >= 1) {
			for (int i = 0; i < m_data.length; i++) {
				for (int j = i; j >= hSize; j -= hSize) {
					if (lt(j, j - hSize)) swap(j, j - hSize);
					else break;
				}
			}
			hSize /= 3;
		}
	}

	/* (non-Javadoc)
	 * @see AbstractSort#name()
	 */
	@Override
	public String name() {
		return "Shell sort";
	}

}
