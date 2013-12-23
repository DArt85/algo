import java.util.Comparator;

/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class BinaryHeap<Key extends Comparable<Key>> extends Helper<Key> {

	public enum Order {
		Max,
		Min
	}
	
	private final Comparator<Integer> MIN_ORDER = new Comparator<Integer>() {
    	public int compare(Integer i1, Integer i2) {
    		return m_data[i1].compareTo(m_data[i2]);
		}
    };
    
    private final Comparator<Integer> MAX_ORDER = new Comparator<Integer>() {
    	public int compare(Integer i1, Integer i2) {
    		return m_data[i2].compareTo(m_data[i1]);
		}
    };
	
	private int count;
	private int size;
	private Comparator<Integer> order;

	@SuppressWarnings("unchecked")
	BinaryHeap(int capacity, Order ord) {
		count = 0;
		size = capacity;
		m_data = (Key[])new Comparable[capacity + 1];
		order = (ord == Order.Max) ? MAX_ORDER : MIN_ORDER;
	}

	public boolean isEmpty() {
		return (count == 0);
	}
	
	public void insert(Key k) {
		if (count == size) resize(2*size);
		m_data[++count] = k;
		int i = count;
		while ((i > 1) && (order.compare(i/2, i) > 0)) {
			swap(i/2, i);
			i /= 2;
		}
	}
	
	public Key remove() throws Exception {
		if (count == 0) throw new Exception("no more elements");
		Key res = m_data[1];
		swap(1, count--);
		int i = 1, j = 2;
		while (j <= count) {
			if ((j < count) && (order.compare(j, j+1) > 0)) j++;
			if (order.compare(i, j) > 0) swap(i, j);
			else break;
			i = j; j *= 2;
		}
		m_data[count+1] = null;
		if (count <= size/4) resize(size/2);
		return res;
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int sz) {
		Key[] tmp = m_data;
		size = sz;
		m_data = (Key[])new Comparable[sz + 1];
		for (int i = 1; i <= count; i++) m_data[i] = tmp[i];
		tmp = null;
	}
}
