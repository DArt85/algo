/**
 * @author ardobryn
 * @file   Percolation.java
 */

/**
 * Weighted quick-union implementation for percolation problem. 
 */
public class Percolation {
	
	private int m_gridSize;
	private int[] m_connTree;
	private int[] m_siteSize;
	private boolean[] m_siteStates;
	
	public Percolation(int N) {
		m_gridSize = N;
		// +2 top and bottom virtual sites
		m_connTree = new int[N * N + 2];
		m_siteSize = new int[N * N + 2];
		m_siteStates = new boolean[N * N + 2];
		for (int i = 0; i < N * N + 2; i++) {
			m_connTree[i] = i;
			m_siteSize[i] = 1;
			m_siteStates[i] = false;
		}
		// open the top virtual site
		m_siteStates[0] = m_siteStates[m_gridSize * m_gridSize + 1] = true;
	}
	
	public void open(int i, int j) {
		int thisSite = flattenIndices(i, j);
		m_siteStates[thisSite] = true;
		
		int neighbours[] = new int[]{flattenIfValid(i - 1, j), flattenIfValid(i, j + 1),
									 flattenIfValid(i + 1, j), flattenIfValid(i, j - 1)};
		for (int index : neighbours) {
			if ((index != -1) && (m_siteStates[index] == true)) {
				union(thisSite, index);
			}
		}
	}
	
	public boolean isOpen(int i, int j) {
		return m_siteStates[flattenIndices(i, j)];
	}
	
	public boolean isFull(int i, int j) {
		return (getRoot(flattenIndices(i, j)) == getRoot(0));
	}
	
	public boolean percolates() {
		return (getRoot(0) == getRoot(m_gridSize * m_gridSize + 1));
	}
	
	private int flattenIfValid(int i, int j) {
		int index = -1;
		if (i == 0) {
			index = 0;
		} else if (i == (m_gridSize + 1)) {
			index = m_gridSize * m_gridSize + 1;
		} else if ((i > 0) && (i <= m_gridSize) && (j > 0) && (j <= m_gridSize)) {
			index = (i - 1) * m_gridSize + j;
		}
		return index;
	}
	
	private int flattenIndices(int i, int j) {
		if ((i <= 0) || (i > m_gridSize)) throw new IndexOutOfBoundsException("Row index is out of bounds");
		if ((j <= 0) || (j > m_gridSize)) throw new IndexOutOfBoundsException("Column index is out of bounds");
		return (i - 1) * m_gridSize + j;
	}
	
	private int getRoot(int index) {
		while (index != m_connTree[index]) index = m_connTree[index];
		return index;
	}
	
	private void union(int site1, int site2) {
		int root1 = getRoot(site1);
		int root2 = getRoot(site2);
		if (root1 == root2) return;
		if (m_siteSize[root1] < m_siteSize[root2]) {
			m_connTree[root1] = root2;
			m_siteSize[root2] += m_siteSize[root1];
		} else {
			m_connTree[root2] = root1;
			m_siteSize[root1] += m_siteSize[root2];
		}
	}
}
