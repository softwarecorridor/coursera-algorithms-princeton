import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	
	private Node goalNode = null;
	private class Node implements Comparable<Node>
	{
		private Board b;
		private int move = 0;
		private Node predecessor = null;
		private int manhattanDistance = 0;
		
		public Node(Board currentBoard, Node previousBoard)
		{
			b = currentBoard;
			predecessor = previousBoard;
			if (predecessor!=null)
				move = predecessor.getMoveCount() + 1;
			
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
		
		public int getMoveCount()
		{
			return move;
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
	
	/**
	 * find a solution to the initial board (using the A* algorithm)
	 * @param initial
	 */
	
	public Solver(Board initial)
	{
		bestFirstSearch(initial);
	}
	
	
	
	
	private void bestFirstSearch(Board initial)
	{
		
		
		MinPQ<Node> mainPriorityQueue = new MinPQ<>();
		MinPQ<Node> twinPriorityQueue = new MinPQ<>();
		// have two boards initial and twin of initial
		Board twin = initial.twin();
//		System.out.println(twin.toString());
		
//		First, insert the initial search node (the initial board, 0 moves, and a null predecessor search node) into a priority queue.
		Node initialNode = new Node(initial,  null);
		Node twinNode = new Node(twin,  null);
		
		
		mainPriorityQueue.insert(initialNode);
		twinPriorityQueue.insert(twinNode);
		
		
//		delete from the priority queue the search node with the minimum priority (those that can be reached in one move from the dequeued search node). 
		
		Node mainDequeueNode = mainPriorityQueue.delMin();
		Node twinDequeueNode = twinPriorityQueue.delMin();
		

		while(!mainDequeueNode.isGoal() && !twinDequeueNode.isGoal())
		{
			
//			insert onto the priority queue all neighboring search nodes 
			for(Board b : mainDequeueNode.neighbors())
			{
				if(mainDequeueNode.predecessor == null || !b.equals(mainDequeueNode.predecessor.b))
				{
					Node newNode = new Node(b,mainDequeueNode);
//					System.out.println(b.toString());
					mainPriorityQueue.insert(newNode);
				}
				
			}
			
			for (Board twinB : twinDequeueNode.neighbors())
			{
				if(twinDequeueNode.predecessor == null || !twinB.equals(twinDequeueNode.predecessor.b))
				{
					Node newNode = new Node(twinB, twinDequeueNode);
					twinPriorityQueue.insert(newNode);
				}
			}
			
			mainDequeueNode = mainPriorityQueue.delMin();
			twinDequeueNode = twinPriorityQueue.delMin();
			
			
		}

		// Exactly one of the two will lead to the goal board. 
		if  (mainDequeueNode.isGoal())
		{
			goalNode = mainDequeueNode;
		}
		
	}
	
	/**
	 * is the initial board solvable?
	 * @return
	 */
	public boolean isSolvable()
	{
		return goalNode!=null;
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
		return goalNode.move;
	}
	
	/**
	 *  sequence of boards in a shortest solution; null if unsolvable
	 * @return
	 */
	public Iterable<Board> solution()
	{
		Stack<Board> resultStack = new Stack<>();
		Node currentNode = goalNode;
		while(currentNode!=null)
		{
			resultStack.push(currentNode.b);
			currentNode = currentNode.predecessor;
		}
		return resultStack;
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
