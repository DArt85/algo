/**
 * @file	Board.java 
 */

public class Board {
	
	private int    dim;
	private int    dist;
	private int[] board;
	
	public Board(int[][] blocks) {
		dim = blocks.length;
		dist = -1;
		board = new int[dim * dim];
		int index = 0;
		for (int[] row : blocks)
			for (int el : row) board[index++] = el;
	}
	
	private Board(Board bd) {
		dim = bd.dim;
		dist = -1;
		board = new int[bd.board.length];
		for (int i = 0; i < board.length; i++) board[i] = bd.board[i];
	}
	
	public int dimension() {
		return dim;
	}
	
	public int hamming() {
		int rs = 0;
		for (int i = 0; i < board.length; i++)
			if (board[i] != (i + 1)) rs++;
		return (rs - 1);
	}
	
	public int manhattan() {
		if (dist < 0) {
			dist = 0;
			for (int i = 0; i < board.length; i++) {
				if ((board[i] != 0) && (board[i] != (i + 1))) {
					int cRow = i/dim, cCol = i%dim, tRow = (board[i]-1)/dim, tCol = (board[i]-1)%dim;
					dist += Math.abs(cRow-tRow) + Math.abs(cCol-tCol);
				}
			}
		}
		return dist;
	}
	
	public boolean isGoal() {
		if (board[board.length-1] != 0) return false;
        for (int i = 0; i < board.length-1; i++)
        	if (board[i] != (i+1)) return false;
        return true;
	}
	
	public Board twin() {
		Board copy = new Board(this);
		int row = 0;
		if ((board[0] == 0) || (board[1] == 0)) row = 1;
		copy.swap(row*dim, row*dim + 1);
		return copy;
	}
	
	public boolean equals(Object y) {
		boolean rs = true;
		if ((y == null) || !(y instanceof Board)) {
			rs = false;
		} else if (this == y) {
			rs = true;
		} else {
			Board cmp = (Board) y;
			if (cmp.dim != dim) return false;
			for (int i = 0; i < board.length; i++)
				if (board[i] != cmp.board[i]) {
					rs = false;
					break;
				}
		}
		return rs;
	}
	
	private void swap(int i, int j) {
		int tmp = board[i];
		board[i] = board[j];
		board[j] = tmp;
	}
	
	private Board getSwapedCopy(int i, int j) {
		Board b = new Board(this);
		b.swap(i, j);
		return b;
	}
	
	public Iterable<Board> neighbors() {
		Queue<Board> rq = new Queue<Board>();
		int pos = 0;
		for (; (pos < board.length) && (board[pos] != 0); pos++);
		int row = pos/dim, col = pos%dim;
		if (row > 0)     rq.enqueue(getSwapedCopy(pos, (row-1)*dim + col));
		if (row < dim-1) rq.enqueue(getSwapedCopy(pos, (row+1)*dim + col));
		if (col > 0)     rq.enqueue(getSwapedCopy(pos, pos-1));
		if (col < dim-1) rq.enqueue(getSwapedCopy(pos, pos+1));
		return rq;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(2*dim*dim);
		sb.append(dim+"\n");
		for (int i = 0; i < board.length; i++) {
			sb.append(String.format("%2d", board[i]));
			sb.append((i % dim == dim - 1) ? "\n" : " ");
		}
		return sb.toString();
	}
	
	public static void main(String args[]) {
		//Board initial = Solver.readBoardFromFile(args[0]);
		Board initial = new Board(new int[][]{{5,8,7},{1,4,6},{3,0,2}});
	    StdOut.printf("Dimension: %d\n", initial.dimension());
	    StdOut.print(initial.toString());
	    StdOut.printf("Hamming: %d\n", initial.hamming());
	    StdOut.printf("Manhattan: %d\n", initial.manhattan());
	    StdOut.printf("Twin:\n%s", initial.twin());
	    StdOut.printf("\nNeighbours:\n\n");
	    for (Board b : initial.neighbors()) StdOut.println(b);
	}
}
