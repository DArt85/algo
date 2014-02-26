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
	
	private Node 		 n;
	private boolean		 solv;
	private Stack<Board> sol;
	private boolean		 checked;
	
	public Solver(Board initial) {
		solv = false;
		n = new Node(initial, null, 0);
		sol = new Stack<Board>();
		checked = false;
	}
	
	private boolean step(MinPQ<Node> pq) {
		if (pq.isEmpty()) return false;
		Node cur = pq.delMin();
		if (cur.bd.isGoal()) {
			n = cur;
			return true;
		}
		for (Board b : cur.bd.neighbors())
			if ((cur.prev == null) || !b.equals(cur.prev.bd)) pq.insert(new Node(b, cur, ++cur.moves));
		return false;
	}
	
	public boolean isSolvable() {
		boolean swapSolv = false;
		MinPQ<Node> swapSqueue = new MinPQ<Node>(PRI_ORDER);
		swapSqueue.insert(new Node(n.bd.twin(), null, 0));
		
		MinPQ<Node> squeue = new MinPQ<Node>(PRI_ORDER);
		squeue.insert(n);
		
		while (!solv && !swapSolv) {
			solv = step(squeue);
			swapSolv = step(swapSqueue);
		}
		
		checked = true;
		return solv;
	}
	
	public int moves() {
		if (!checked) isSolvable();
		if (solv) {
			if (sol.isEmpty()) solution();
			return sol.size() - 1;
		} else {
			return -1;
		}
	}
	
	public Iterable<Board> solution() {
		if (!checked) isSolvable();
		if (!solv) return null;
		if (sol.isEmpty()) {
			Node it = n;
			while (it != null) {
				sol.push(it.bd);
				it = it.prev;
			}
		}
		return sol;
	}
	
	private static Board readBoardFromFile(String file) {
		// create initial board from file
	    In in = new In(file);
	    int N = in.readInt();
	    int[][] blocks = new int[N][N];
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
