import java.util.Iterator;

/**
 * 
 */

/**
 * @author ardobryn
 *
 */
public class PointReader implements Iterable<Point> {

	private In m_input;
	private int m_size;
	
	private class PointIterator implements Iterator<Point> {

		private int m_left = m_size;
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			return (m_left > 0);
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Point next() {
			m_left--;
			return (new Point(m_input.readInt(), m_input.readInt()));
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException("removal is not supported");
		}
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Point> iterator() {
		return new PointIterator();
	}

	public PointReader(In input) {
		m_input = input;
		m_size = input.readInt();
	}
	
	public int getSize() {
		return m_size;
	}
}
