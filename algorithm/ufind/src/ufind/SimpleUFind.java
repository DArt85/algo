/**
 * 
 */
package ufind;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.BinaryIn;
import edu.princeton.cs.introcs.BinaryOut;
import edu.princeton.cs.introcs.StdOut;

/**
 * @author Arthur D
 *
 */
public class SimpleUFind {
	
	private int m_dimX;
	private int m_dimY;
	
	private int m_Size;
	private int[] m_IDs;
	private byte[] m_ConMap;
	
	public SimpleUFind(int dimX, int dimY) {
		m_dimX = dimX;
		m_dimY = dimY;
		m_Size = dimX * dimY;
		m_IDs = new int[m_Size];
		for (int i = 0; i < m_Size; i++) m_IDs[i] = i;
		m_ConMap = new byte[m_Size];
	}
	
	public void readFromFile(String file) {
		try {
			m_dimX = m_dimY = 0;
			int inRow = 0;
			int index = 0;
			BinaryIn fis = new BinaryIn(new FileInputStream(file));
			while (!fis.isEmpty()) {
				byte word = fis.readByte();
				if ((char)(word) == '\n') {
					if (inRow != m_dimX) throw new IOException("Corrupted file");
					inRow = 0;
					m_dimY++;
				} else {
					m_ConMap[index++] = (byte)(word & 0xf);
					m_ConMap[index++] = (byte)((word & 0xf0) >> 4);
					inRow += 2;
				}
			}
			m_dimX = inRow;
		} catch (IOException e) {
			StdOut.printf("Error saving to file: %s\n", e.getMessage());
		}
	}
	
	public void writeToFile(String file) {
		BinaryOut fos = null;
		try {
			fos = new BinaryOut(new FileOutputStream(file));
			for (int j = 0; j < m_dimY; j++) {
				//StdOut.printf("Row: %d\n", j);
				for (int i = 0; i < m_dimX; i += 2) {
					//StdOut.printf("Index: %d\n", i);
					fos.write((m_ConMap[getIndex(i + 1, j)] << 4) | m_ConMap[getIndex(i, j)]);
				}
				fos.write('\n');
			}
		} catch (IOException e) {
			StdOut.printf("Error saving to file: %s\n", e.getMessage());
		} finally {
			if (fos != null) fos.close();
		}
	}
	
	public void plotGrid(double conFactor) {
		byte map;
		
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
				
				map = 0;
				if (isPoint(i - 1, j) & (Math.random() > conFactor)) { 
					StdDraw.line(i, j, i - 1, j);
					union(i, j, i - 1, j);
					map |= 1 << 3;
				}
				if (isPoint(i + 1, j) & (Math.random() > conFactor)) {
					StdDraw.line(i, j, i + 1, j);
					union(i, j, i + 1, j);
					map |= 1 << 1;
				}
				if (isPoint(i, j - 1) & (Math.random() > conFactor)) {
					StdDraw.line(i, j, i, j - 1);
					union(i, j, i, j - 1);
					map |= 1 << 0;
				}
				if (isPoint(i, j + 1) & (Math.random() > conFactor)) {
					StdDraw.line(i, j, i, j + 1);
					union(i, j, i, j + 1);
					map |= 1 << 2;
				}
				m_ConMap[getIndex(i, j)] = map;
			}
		}
	}
	
	private boolean isPoint(int x, int y) {
		return ((x >= 0) & (x < m_dimX) & (y >= 0) & (y < m_dimY));
	}
	
	private void union(int x1, int y1, int x2, int y2) {
		int id1 = m_IDs[getIndex(x1, y1)];
		int id2 = m_IDs[getIndex(x2, y2)];
		for (int i = 0; i < m_Size; i++) {
			if (m_IDs[i] == id2) m_IDs[i] = id1;
		}
	}
	
	public boolean isConnected(int x1, int y1, int x2, int y2) {
		return (m_IDs[getIndex(x1, y1)] == m_IDs[getIndex(x2, y2)]);
	}
	
	private int getIndex(int x, int y) {
		return (x + y * m_dimX); 
	}
}
