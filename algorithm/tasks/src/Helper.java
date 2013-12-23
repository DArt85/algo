/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class Helper<T extends Comparable<T>> {
	
	T[] m_data;
	int m_compares = 0;
	int m_swaps = 0;
	
	boolean lt(int ipos, int jpos) {
		m_compares++;
		return (m_data[ipos].compareTo(m_data[jpos]) < 0);
	}
	
	boolean lt(T a, T b) {
		m_compares++;
		return (a.compareTo(b) < 0);
	}
	
	boolean lte(int ipos, int jpos) {
		m_compares++;
		return (m_data[ipos].compareTo(m_data[jpos]) <= 0);
	}
	
	void swap(int ipos, int jpos) {
		T tmp = m_data[ipos];
		m_data[ipos] = m_data[jpos];
		m_data[jpos] = tmp;
		m_swaps++;
	}
	
	public void printData() {
		for (int i = 0; i < m_data.length - 1; i++) StdOut.print(m_data[i] + ", ");
		StdOut.print(m_data[m_data.length - 1] + "\n");
	}
	
	public int getCompares() {
		return m_compares;
	}
	
	public int getSwaps() {
		return m_swaps;
	}
}
