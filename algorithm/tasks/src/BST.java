import java.util.Iterator;

/**
 * 
 */

/**
 * @author Tigra
 *
 */
public class BST<Key extends Comparable<Key>, Value> extends Helper<Key> implements Iterable<Key> {

	class Node {
		private Key key;
		private Value val;
		private Node left;
		private Node right;
		private int count;
		
		Node(Key k, Value v) {
			key = k;
			val = v;
			left = right = null;
			count = 0;
		}
	}
	
	private class BSTIterator implements Iterator<Key> {

		private Node current;
		
		BSTIterator() {
			current = root;
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return (current == null);
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Key next() {
			
			return null;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("removal from BST iterator is not supported");
			
		}
	}
	
	private Node root;
	
	public BST() {
		root = null;
	}	
	
	private Node put(Node n, Key k, Value v) {
		if (n == null) return new Node(k, v);
		int cmp = k.compareTo(n.key);
		if (cmp < 0) {
			n.left = put(n.left, k, v);
		} else if (cmp > 0) {
			n.right = put(n.right, k, v);
		} else {
			n.val = v;
		}
		n.count++;
		return n;
	}
	
	public void put(Key k, Value v) {
		root = put(root, k, v);
		if (root.count == 0) root.count++;
	}
	
	public Value get(Key k) {
		Node n = root;
		while (n != null) {
			int cmp = k.compareTo(n.key); 
			if (cmp < 0) {
				n = n.left;
			} else if (cmp > 0) {
				n = n.right;
			} else {
				break;
			}
		}
		return ((n == null) ? null : n.val);
	}
	
	public void delete(Key k) {
		Node n = root;
		while (n != null) {
			int cmp = k.compareTo(n.key);
			if (cmp < 0) {
				n = n.left;
			} else if (cmp > 0) {
				n = n.right;
			} else {
				// TODO: need clear rules here
				break;
			}
		}
	}
		
	public int size() {
		return (root == null) ? 0 : root.count;
	}
	
	private int rank(Node n, Key k) {
		if (n == null) return 0;
		int cmp = k.compareTo(n.key);
		if (cmp > 0) {
			
		} else if (cmp < 0) {
			
		} else {
			
		}
		return 0;
	}
	
	public int rank(Key k) {
		return rank(root, k);
	}
	
	public int floor(Key k) {
		Node n = minMax(root, k, false);
		return (n == null) ? null : n.count;
	}
	
	private Node minMax(Node n, Key k, boolean isMax) {
		if (n == null) return n;
		
		Node t;
		int cmp = k.compareTo(n.key);
		if (cmp > 0) {
			t = minMax(n.right, k, isMax);
			return (!isMax && (t == null)) ? n : t;
		} else if (cmp < 0) {
			t = minMax(n.left, k, isMax);
			return (isMax && (t == null)) ? n : t;
		} else {
			return n;
		}
	}
	
	public Key ceiling(Key k) {
		Node n = minMax(root, k, true);
		return (n == null) ? null : n.key;
	}
		
	public Iterator<Key> iterator() {
		return new BSTIterator();
	}
}
