import java.util.Iterator;

/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class RandomizedQueue<T> implements Iterable<T> {

	private class RandomizedQueueIterator implements Iterator<T> {

		int current;
		int[] order;
		
		RandomizedQueueIterator() {
			current = 0;
			order = new int[m_size];
			for (int i = 0; i < m_size; i++) {
				order[i] = StdRandom.uniform(m_size);
			}
		}
		
		/* 
		 * 
		 */
		@Override
		public boolean hasNext() {
			return (current < m_size);
		}

		/* 
		 * 
		 */
		@Override
		public T next() {
			return m_valArr[order[current++]];
		}

		/* 
		 * 
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("removal from queue iterator is not supported");
		}
		
	}
	
	/* 
	 * 
	 */
	@Override
	public Iterator<T> iterator() {
		return new RandomizedQueueIterator();
	}
	
	private T[] m_valArr;
	private int m_size;
	
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		m_valArr = (T[])new Object[2];
		m_size = 0;
	}
	
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public int size() {
		return m_size;
	}
	
	public void enqueue(T val) {
		if (val == null) throw new NullPointerException("can't have null-pointers in queue");
		if (m_size == m_valArr.length) resize(2 * m_size);
		m_valArr[m_size++] = val;
	}
	
	public T dequeue() {
		T rVal = null;
		if (isEmpty()) throw new java.util.NoSuchElementException("queue is empty");
		int index = StdRandom.uniform(m_size);
		rVal = m_valArr[index];
		m_valArr[index] = m_valArr[m_size - 1];
		m_valArr[m_size - 1] = null;
		m_size--;
		if (m_size == m_valArr.length / 4) resize(m_valArr.length / 2);
		return rVal;
	}
	
	public T sample() {
		if (isEmpty()) throw new java.util.NoSuchElementException("queue is empty");
		return m_valArr[StdRandom.uniform(m_size)];
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		T[] newArr = (T[])new Object[newSize];
		for (int i = 0; i < m_size; i++) {
			newArr[i] = m_valArr[i];
		}
		m_valArr = newArr;
	}
}
