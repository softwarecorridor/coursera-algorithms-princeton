package assignment_4;

import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private MinPQ<Node> priorityQueue = new MinPQ<>();
	
	/**
	 * find a solution to the initial board (using the A* algorithm)
	 * @param initial
	 */
	private ArrayList<Board> nodeList = new ArrayList<>();
	
	private class Node implements Comparable<Node>
	{
		private Board b;
		private int move = 0;
		private Node predecessor = null;
		
		public Node(Board currentBoard, int moveNumber, Node previousBoard)
		{
			b = currentBoard;
			move = moveNumber;
			predecessor = previousBoard;
		}
		
		public boolean isGoal()
		{
			return b.isGoal();
		}
		
		public Iterable<Board> neighbors()
		{
			return b.neighbors();
		}


		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			if(b.hamming() > o.b.hamming())
			{
				return 1;
			}
			
			if(b.hamming() < o.b.hamming())
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
		/* 
		 * 
		 *  Repeat this procedure until the search node dequeued corresponds to a goal board. 
		 *  The success of this approach hinges on the choice of priority function for a search node. 
		 *  We consider two priority functions: 
		 */
		
//		First, insert the initial search node (the initial board, 0 moves, and a null predecessor search node) into a priority queue.
		int move = 0;
		Node initialNode = new Node(initial, move, null);
		priorityQueue.insert(initialNode);
		
//		delete from the priority queue the search node with the minimum priority (those that can be reached in one move from the dequeued search node). 
		
		Node dequeueNode = priorityQueue.delMin();
		nodeList.add(dequeueNode.b);
//		 Best-first search has one annoying feature: search nodes corresponding to the same board are enqueued on the priority queue many times. 
//		 To reduce unnecessary exploration of useless search nodes, when considering the neighbors of a search node, don't enqueue a neighbor if its board 
//		 is the same as the board of the predecessor search node
		while(!dequeueNode.isGoal())
		{
			System.out.println("start...");
			
			move++;
//			insert onto the priority queue all neighboring search nodes 
			for(Board b : dequeueNode.neighbors())
			{
				if(dequeueNode.predecessor == null || !b.equals(dequeueNode.predecessor.b))
				{
					Node newNode = new Node(b,move,dequeueNode);
//					System.out.println(b.toString());
					priorityQueue.insert(newNode);
				}
				
			}
			
			dequeueNode = priorityQueue.delMin();
			dequeueNode.toString();
			
			nodeList.add(dequeueNode.b);
		}
		
		System.out.println(priorityQueue.size());
		
	}
	
	/**
	 * is the initial board solvable?
	 * @return
	 */
	public boolean isSolvable()
	{
		return true;
	}
	
	/**
	 * min number of moves to solve initial board; -1 if unsolvable
	 * @return
	 */
	public int moves()
	{
		return priorityQueue.size();
	}
	
	/**
	 *  sequence of boards in a shortest solution; null if unsolvable
	 * @return
	 */
	public Iterable<Board> solution()
	{
		return nodeList;
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
