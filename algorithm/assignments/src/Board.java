/**
 * @file	Board.java 
 */

public class Board {
	
	private int    dim;
	private int    dist;
	private byte[] board;
	
	public Board(byte[][] blocks) {
		dim = blocks.length;
		dist = -1;
		board = new byte[dim * dim];
		int index = 0;
		for (byte[] row : blocks)
			for (byte el : row) board[index++] = el;
	}
	
	private Board(Board bd) {
		dim = bd.dim;
		dist = bd.dist;
		board = new byte[bd.board.length];
		for (int i = 0; i < board.length; i++) board[i] = bd.board[i];
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
		if (dist < 0) {
			dist = 0;
			for (int i = 0; i < board.length; i++) {
				if ((board[i] != 0) && (board[i] != (i + 1))) {
					int distLin = Math.abs(i + 1 - board[i]);
					int distV = distLin / dim;
					int distH = distLin - dim * distV;
					dist += distV + distH;
				}
			}
		}
		return dist;
	}
	
	public boolean isGoal() {
		return (hamming() == 0);
	}
	
	public Board twin() {
		Board copy = new Board(this);
		boolean swaped = false;
		while (!swaped) {
			int row = StdRandom.uniform(0, dim);
			int colPair[] = new int[]{-1,-1};
			for (int col = 0, i = 0; (col < dim) && (i < 2); ) {
				int index = row*dim + col;
				if (board[index] != 0) {
					col += (i == 0) ? dim/2 : 1;
					colPair[i++] = index;
				} else {
					col++;
				}
			}
			if ((colPair[0] >= 0) && (colPair[1] >= 0) && (colPair[0] != colPair[1])) {
				copy.board[colPair[0]] = board[colPair[1]];
				copy.board[colPair[1]] = board[colPair[0]];
				swaped = true;
			}
		}
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
			for (int i = 0; i < board.length; i++)
				if (board[i] != cmp.board[i]) rs = false;
		}
		return rs;
	}
	
	private void swap(int i, int j) {
		byte tmp = board[i];
		board[i] = board[j];
		board[j] = tmp;
	}
	
	public Iterable<Board> neighbours() {
		Queue<Board> rq = new Queue<Board>();
		int pos = 0;
		for (; (pos < board.length) && (board[pos] != 0); pos++);
		for (int ind : new int[]{pos-dim,pos+1,pos+dim,pos-1}) {
			int row = ind/dim, col = ind%dim;
			if ((ind >= 0) && (ind < board.length) && (row >= 0) && (row < dim) && (col >= 0) && (col < dim)) {
				Board b = new Board(this);
				b.swap(pos, ind);
				rq.enqueue(b);
			}
		}
		return rq;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder(2*dim*dim);
		for (int i = 0; i < board.length; i++) {
			sb.append(board[i]);
			sb.append((i % dim == dim - 1) ? "\n" : " ");
		}
		return sb.toString();
	}
	
	public static void main(String args[]) {
		Board initial = Solver.readBoardFromFile(args[0]);
	    StdOut.printf("Dimension: %d\n", initial.dimension());
	    StdOut.print(initial.toString());
	    StdOut.printf("Hamming: %d\n", initial.hamming());
	    StdOut.printf("Manhattan: %d\n", initial.manhattan());
	    StdOut.printf("Twin:\n%s", initial.twin());
	    StdOut.printf("\nNeighbours:\n\n");
	    for (Board b : initial.neighbours()) StdOut.println(b);
	}
}
