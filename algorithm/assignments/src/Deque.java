import java.util.Iterator;


/**
 * 
 */

/**
 * @author ardobryn
 * @param <Item>
 *
 */
public class Deque<Item> implements Iterable<Item> {

	private class Node {
		private Item value;
		private Node next;
		private Node prev;
		
		Node(Item val) {
			value = val;
			next = null;
			prev = null;
		}
	}
	
	private class DequeIterator implements Iterator<Item> {

		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public Item next() {
			if (current == null) throw new java.util.NoSuchElementException("iterator is empty");
			Item rVal = current.value;
			current = current.next;
			return rVal;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("removal from dequeue iterator is not supported");
		}
	}
	
	private Node first = null;
	private Node last = null;
	private int count = 0;

	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public int size() {
		return count;
	}
	
	public void addFirst(Item val) {
		checkIfNull(val);
		Node oldfirst = first;
		first = new Node(val);
		first.next = oldfirst;
		if (isEmpty()) last = first;
		else oldfirst.prev = first;
		count++;
	}
	
	public void addLast(Item val) {
		checkIfNull(val);
		Node oldlast = last;
		last = new Node(val);
		last.prev = oldlast;
		if (isEmpty()) first = last;
		else oldlast.next = last;
		count++;
	}
	
	public Item removeFirst() {
		if (isEmpty()) throw new java.util.NoSuchElementException("deque is empty");
		Item rVal = first.value;
		first = first.next;
		count--;
		if (isEmpty()) last = null;
		else first.prev = null;
		return rVal;
	}
	
	public Item removeLast() {
		if (isEmpty()) throw new java.util.NoSuchElementException("deque is empty");
		Item rVal = last.value;
		last = last.prev;
		count--;
		if (isEmpty()) first = null;
		else last.next = null;
		return rVal;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}
	
	private void checkIfNull(Item val) {
		if (val == null) throw new NullPointerException("can't have null-pointers in deque");
	}
	
	public static void main(String[] args) {
		Deque<Integer> dq = new Deque<Integer>();
		for (int i = 0; i < 50; i++) {
			if (StdRandom.random() > 0.1) {
				//dq.addFirst(i);
				dq.addLast(i);
			} else {
				//StdOut.println(dq.removeLast());
				StdOut.println(dq.removeFirst());
			}
		}
	}
}
