/**
 * 
 */
package ufind;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

/**
 * @author Tigra
 *
 */
public class SimpleUFind {
	
	private int m_dimX;
	private int m_dimY;
	
	private int m_Size;
	private int[] m_IDs;
	
	public SimpleUFind(int dimX, int dimY) {
		m_dimX = dimX;
		m_dimY = dimY;
		m_Size = dimX * dimY;
		m_IDs = new int[m_Size];
		for (int i = 0; i < m_Size; i++) m_IDs[i] = i;
	}
	
	public void plotGrid(double conFactor) {
		StdDraw.setXscale(-1, m_dimX + 1);
		StdDraw.setYscale(-1, m_dimY + 1);

		// offset by 1 in the frame from all sides
		for (int i = 0; i < m_dimX; i++) {
			for (int j = 0; j < m_dimY; j++) {
				StdDraw.setPenColor(Color.RED);
				StdDraw.setPenRadius(StdDraw.getPenRadius() * 4);
				StdDraw.point(i, j);
				
				StdDraw.setPenColor();
				StdDraw.setPenRadius();
				if (isPoint(i - 1, j) & (Math.random() > conFactor)) { 
					StdDraw.line(i, j, i - 1, j);
					union(i + j * m_dimX, i - 1 + j * m_dimX);
				}
				if (isPoint(i + 1, j) & (Math.random() > conFactor)) {
					StdDraw.line(i, j, i + 1, j);
					union(i + j * m_dimX, i + 1 + j * m_dimX);
				}
				if (isPoint(i, j - 1) & (Math.random() > conFactor)) {
					StdDraw.line(i, j, i, j - 1);
					union(i + j * m_dimX, i + (j - 1) * m_dimX);
				}
				if (isPoint(i, j + 1) & (Math.random() > conFactor)) {
					StdDraw.line(i, j, i, j + 1);
					union(i + j * m_dimX, i + (j + 1) * m_dimX);
				}
			}
		}
	}
	
	private boolean isPoint(int x, int y) {
		return ((x >= 0) & (x < m_dimX) & (y >= 0) & (y < m_dimY));
	}
	
	private void union(int x, int y) {
		int xid = m_IDs[x];
		int yid = m_IDs[y];
		for (int i = 0; i < m_Size; i++) {
			if (m_IDs[i] == yid) m_IDs[i] = xid;
		}
	}
	
	public boolean isConnected(int x1, int y1, int x2, int y2) {
		return (m_IDs[x1 + y1 * m_dimX] == m_IDs[x2 + y2 * m_dimX]);
	}
}
