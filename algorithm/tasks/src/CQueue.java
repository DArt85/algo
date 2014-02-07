import java.util.Iterator;

/**
 * @author Tigra
 * 
 * Circular queue implementation.
 */
public class CQueue<T> implements Iterable<T> {

	private class Node {
		T val;
		Node right;
		Node left;
		
		Node(T v) {
			val = v;
			right = null;
			left = null;
		}
	}
	
	private class CQueueIterator implements Iterator<T> {
		
		Node current;
		boolean isStarted;
		boolean isReversed;
				
		CQueueIterator(boolean reverse) {
			isReversed = reverse;
			isStarted = false;
			current = ((root != null) && isReversed) ? root.left : root;
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (current == null) return false;
			if (isReversed) return ((current != root.left) ^ !isStarted);
			else return ((root != null) && ((current != root) ^ !isStarted));
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public T next() {
			isStarted = true;
			T rv = current.val;
			current = (isReversed) ? current.left : current.right;
			return rv;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("removal from queue iterator is not supported");
		}
		
	}
	
	private Node root = null;
	private boolean itReverse = false;
	
	public void reverseIterator() {
		itReverse = true;
	}
	
	public void enqueue(T val) {
		Node n = new Node(val);
		if (root == null) {
			root = n;
			root.right = root;
			root.left = root;
		} else {
			n.left = root.left;
			n.right = root;
			root.left.right = n;
			root.left = n;
		}
	}
	
	public T dequeue() {
		T rs = null;
		if (root == null) {
			throw new java.util.NoSuchElementException("queue is empty");
		}
		rs = root.val;
		if (root.right == root) {
			root = null;
		} else {
			root.left.right = root.right;
			root.right.left = root.left;
			root = root.right;
		}
		return rs;
	}
	
	public boolean isEmpty() {
		return (root == null);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return new CQueueIterator(itReverse);
	}

	public static void main(String[] args) {
		CQueue<Integer> q = new CQueue<Integer>();
		for (int v : new int[]{0,1,2,3,4,5}) q.enqueue(v);
		//q.reverseIterator();
		while (!q.isEmpty()) {
			StdOut.printf("Dequeue first: %d\n", q.dequeue());
			for (int v : q) StdOut.println(v);
		}
	}
}
