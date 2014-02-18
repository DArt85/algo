/**
 * @file	Board.java 
 */

public class Board {

	private int   dim;
	private int[] board;
	
	public Board(int[][] blocks) {
		dim = blocks.length;
		board = new int[dim * dim];
		int index = 0;
		for (int[] row : blocks)
			for (int el : row)
				board[index++] = el;
	}
	
	public int dimension() {
		return dim;
	}
	
	public int hamming() {
		int rs = 0;
		for (int i = 0; i < board.length; i++)
			if ((board[i] != 0) && (board[i] != (i + 1))) rs++;
		return rs;
	}
	
	public int manhattan() {
		int rs = 0;
		for (int i = 0; i < board.length; i++) {
			if ((board[i] != 0) && (board[i] != (i + 1))) {
				int distLin = Math.abs(i + 1 - board[i]);
				int distV = distLin / dim;
				int distH = distLin - dim * distV;
				rs += distV + distH;
				//StdOut.printf("Element %d distance: %d\n", board[i], distV + distH);
			}
		}
		return rs;
	}
	
	public boolean isGoal() {
		return (hamming() == 0);
	}
	
	public Board twin() {
		return null;
	}
	
	public boolean equals(Object y) {
		return false;
	}
	
	public Iterable<Board> neighbours() {
		return null;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(2*dim*dim);
		for (int i = 0; i < board.length; i++) {
			sb.append(board[i]);
			sb.append((i % dim == dim - 1) ? "\n" : " ");
		}
		return sb.toString();
	}
}
