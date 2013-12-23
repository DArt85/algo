import java.util.Iterator;


/**
 * 
 */

/**
 * @author ardobryn
 * @param <T>
 *
 */
public class Deque<T> implements Iterable<T> {

	private class Node {
		T value;
		Node next;
		
		Node(T val) {
			value = val;
			next = null;
		}
	}
	
	private class DequeIterator implements Iterator<T> {

		private Node m_current;
		
		DequeIterator() {
			m_current = m_first;
		}
		
		/* 
		 */
		@Override
		public boolean hasNext() {
			return (m_current != null);
		}

		/* 
		 */
		@Override
		public T next() {
			T rVal = m_current.value;
			m_current = m_current.next;
			return rVal;
		}

		/* 
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("removal from stack iterator is not supported");
		}
	}
	
	private Node m_first;
	private Node m_last;
	private int m_count;
	
	public Deque() {
		m_first = null;
		m_last = null;
		m_count = 0;
	}
	
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public int size() {
		return m_count;
	}
	
	public void addFirst(T val) {
		checkIfNull(val);
		Node top = new Node(val);
		top.next = m_first;
		m_first = top;
		if (m_count == 0) {
			m_last = top;
		}
		m_count++;
	}
	
	public void addLast(T val) {
		checkIfNull(val);
		Node btm = new Node(val);
		btm.next = m_last;
		m_last = btm;
		if (m_count == 0) {
			m_first = btm;
		}
		m_count++;
	}
	
	public T removeFirst() {
		T rVal = null;
		if (m_count > 0) {
			rVal = m_first.value;
			m_first = m_first.next;
			m_count--;
			if (m_count == 0) m_last = m_first; 
		} else {
			throw new java.util.NoSuchElementException("deque is empty");
		}
		return rVal;
	}
	
	public T removeLast() {
		T rVal = null;
		if (m_count > 0) {
			rVal = m_last.value;
			m_last = m_last.next;
			m_count--;
			if (m_count == 0) m_first = m_last; 
		} else {
			throw new java.util.NoSuchElementException("deque is empty");
		}
		return rVal;
	}
	
	/* 
	 * 
	 */
	@Override
	public Iterator<T> iterator() {
		return new DequeIterator();
	}
	
	private void checkIfNull(T val) {
		if (val == null) throw new NullPointerException("can't have null-pointers in deque");
	}
}
