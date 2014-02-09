import java.util.Iterator;

/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private class RandomizedQueueIterator implements Iterator<Item> {

		private int current;
		private int[] order;
		
		RandomizedQueueIterator() {
			current = 0;
			order = new int[size];
			for (int i = 0; i < size; i++) order[i] = i;
			StdRandom.shuffle(order);
		}
		
		/* 
		 * 
		 */
		@Override
		public boolean hasNext() {
			return (current < size);
		}

		/* 
		 * 
		 */
		@Override
		public Item next() {
			if (current == size) throw new java.util.NoSuchElementException("iterator is empty");
			return valArr[order[current++]];
		}

		/* 
		 * 
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("removal from queue iterator is not supported");
		}
		
	}
	
	private Item[] valArr;
	private int size;
	
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		valArr = (Item[]) new Object[2];
		size = 0;
	}
	
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	public int size() {
		return size;
	}
	
	public void enqueue(Item val) {
		if (val == null) throw new NullPointerException("can't have null-pointers in queue");
		if (size == valArr.length) resize(2 * size);
		valArr[size++] = val;
	}
	
	public Item dequeue() {
		if (isEmpty()) throw new java.util.NoSuchElementException("queue is empty");
		int index = StdRandom.uniform(size);
		Item rVal = valArr[index];
		valArr[index] = valArr[--size];
		valArr[size] = null;
		if ((size > 0) && (size == valArr.length/4)) resize(valArr.length/2);
		return rVal;
	}
	
	public Item sample() {
		if (isEmpty()) throw new java.util.NoSuchElementException("queue is empty");
		return valArr[StdRandom.uniform(size)];
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int newSize) {
		assert newSize > size;
		Item[] newArr = (Item[]) new Object[newSize];
		for (int i = 0; i < size; i++) {
			newArr[i] = valArr[i];
		}
		valArr = newArr;
	}
	
	public static void main(String[] args) {
		RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
		for (int i : new int[]{0,10,20,30,40}) rq.enqueue(i);
		for (int i : rq) StdOut.println(i);
	}
}
