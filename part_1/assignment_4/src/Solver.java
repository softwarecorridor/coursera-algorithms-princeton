import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private MinPQ<Node> mainPriorityQueue = new MinPQ<>();
	private MinPQ<Node> twinPriorityQueue = new MinPQ<>();
	
	/**
	 * find a solution to the initial board (using the A* algorithm)
	 * @param initial
	 */
	private ArrayList<Board> mainNodeList = new ArrayList<>();
	private ArrayList<Board> twinNodeList = new ArrayList<>();
	
	private boolean isSolvable;
	
	private class Node implements Comparable<Node>
	{
		private Board b;
		private int move = 0;
		private Node predecessor = null;
		private int manhattanDistance = 0;
		
		public Node(Board currentBoard, int moveNumber, Node previousBoard)
		{
			b = currentBoard;
			move = moveNumber;
			predecessor = previousBoard;
			
			manhattanDistance = b.manhattan();
		}
		
		public boolean isGoal()
		{
			return b.isGoal();
		}
		
		public Iterable<Board> neighbors()
		{
			return b.neighbors();
		}
		
		public int getPriority()
		{
			return move + manhattanDistance;
		}


		@Override
		public int compareTo(Node o) {
			if(getPriority() > o.getPriority())
			{
				return 1;
			}
			
			if(getPriority() < o.getPriority())
			{
				return -1;
			}
			
			return 0;
		}
		
	}
	
	public Solver(Board initial)
	{
		bestFirstSearch(initial);
	}
	
	
	
	
	private void bestFirstSearch(Board initial)
	{
		
		// have two boards initial and twin of initial
		Board twin = initial.twin();
//		System.out.println(twin.toString());
		
//		First, insert the initial search node (the initial board, 0 moves, and a null predecessor search node) into a priority queue.
		int move = 0;
		Node initialNode = new Node(initial, move, null);
		Node twinNode = new Node(twin, move, null);
		
		
		mainPriorityQueue.insert(initialNode);
		twinPriorityQueue.insert(twinNode);
		
		
//		delete from the priority queue the search node with the minimum priority (those that can be reached in one move from the dequeued search node). 
		
		Node mainDequeueNode = mainPriorityQueue.delMin();
		mainNodeList.add(mainDequeueNode.b);
		
		Node twinDequeueNode = twinPriorityQueue.delMin();
		twinNodeList.add(twinDequeueNode.b);
		

		while(!mainDequeueNode.isGoal() && !twinDequeueNode.isGoal())
		{
			
			//TODO: do each step of the boards at the same time
			//TODO: if twin is the goal then the board is unsolveable
			
			move++;
//			insert onto the priority queue all neighboring search nodes 
			for(Board b : mainDequeueNode.neighbors())
			{
				if(mainDequeueNode.predecessor == null || !b.equals(mainDequeueNode.predecessor.b))
				{
					Node newNode = new Node(b,move,mainDequeueNode);
//					System.out.println(b.toString());
					mainPriorityQueue.insert(newNode);
				}
				
			}
			
			for (Board twinB : twinDequeueNode.neighbors())
			{
				if(twinDequeueNode.predecessor == null || !twinB.equals(twinDequeueNode.predecessor.b))
				{
					Node newNode = new Node(twinB,move,twinDequeueNode);
					twinPriorityQueue.insert(newNode);
				}
			}
			
			mainDequeueNode = mainPriorityQueue.delMin();
			mainNodeList.add(mainDequeueNode.b);
			twinDequeueNode = twinPriorityQueue.delMin();
			twinNodeList.add(twinDequeueNode.b);
			
			
		}

		// Exactly one of the two will lead to the goal board. 
		isSolvable = !twinDequeueNode.isGoal();
		
	}
	
	/**
	 * is the initial board solvable?
	 * @return
	 */
	public boolean isSolvable()
	{
		return isSolvable;
	}
	
	/**
	 * min number of moves to solve initial board; -1 if unsolvable
	 * @return
	 */
	public int moves()
	{
		
		if(!isSolvable())
		{
			return -1;
		}
		// omit one becuase the solution is contained in the nodelist.
		return mainNodeList.size()-1;
	}
	
	/**
	 *  sequence of boards in a shortest solution; null if unsolvable
	 * @return
	 */
	public Iterable<Board> solution()
	{
		return mainNodeList;
	}

	/**
	 * solve a slider puzzle (given below)
	 * @param args
	 */
	public static void main(String[] args) {
		// create initial board from file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    int[][] blocks = new int[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            blocks[i][j] = in.readInt();
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

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
