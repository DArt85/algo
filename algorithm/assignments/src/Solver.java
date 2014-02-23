/**
 * @file	Solver.java
 */

import java.util.Comparator;

public class Solver {

	private class Node {
		
		private int   moves;
		private Board bd;
		private Node  prev;
		
		public Node(Board b, Node p, int mov) {
			bd = b;
			prev = p;
			moves = mov;
		}
		
		public int priority() {
			return (moves + bd.manhattan());
		}
	}
	
	private final Comparator<Node> PRI_ORDER = new Comparator<Node>() {
    	public int compare(Node n1, Node n2) {
    		int rc;
    		int pri1 = n1.priority();
    		int pri2 = n2.priority();
    		if (pri1 < pri2) rc = -1;
    		else if (pri1 > pri2) rc = 1;
    		else rc = 0;
			return rc;
		}
    };
	
	private Node 		n;
	private boolean		solv;
	
	public Solver(Board initial) {
		solv = false;
		n = new Node(initial, null, 0);
	}
	
	public boolean isSolvable() {
		MinPQ<Node> squeue = new MinPQ<Node>(PRI_ORDER);;
		squeue.insert(n);
		while (!squeue.isEmpty()) {
			n = squeue.delMin();
			if (n.bd.isGoal()) {
				solv = true;
				break;
			}
			for (Board b : n.bd.neighbours())
				if ((n.prev == null) || !b.equals(n.prev.bd)) squeue.insert(new Node(b, n, n.moves++));
		}
		return solv;
	}
	
	public int moves() {
		return (solv) ? n.moves : -1;
	}
	
	public Iterable<Board> solution() {
		if (!solv) return null;
		Node it = n;
		Stack<Board> sol = new Stack<Board>();
		while (it != null) {
			sol.push(it.bd);
			it = it.prev;
		}
		return sol;
	}
	
	public static Board readBoardFromFile(String file) {
		// create initial board from file
	    In in = new In(file);
	    int N = in.readInt();
	    byte[][] blocks = new byte[N][N];
	    for (int i = 0; i < N; i++)
	        for (int j = 0; j < N; j++)
	            blocks[i][j] = in.readByte();
	    Board bd = new Board(blocks);
	    return bd;
	}
	
	public static void main(String[] args) {
		// solve the puzzle
	    Solver solver = new Solver(readBoardFromFile(args[0]));

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}
}
