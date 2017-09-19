package assignment_4;

/*
 * - Corner cases.  You may assume that the constructor receives an n-by-n array containing 
 * 	the n2 integers between 0 and n2 − 1, where 0 represents the blank square.
 * - Performance requirements.  Your implementation should support all Board methods in 
 * 	time proportional to n2 (or better) in the worst case. 
 * 
 */
public class Board {
	
	/**
	 * construct a board from an n-by-n array of blocks 
	 * (where blocks[i][j] = block in row i, column j)
	 * @param blocks
	 */
	public Board(int[][] blocks)
	{
		
	}
	
	/**
	 * board dimension n
	 * @return
	 */
	public int dimension()
	{
		return -1;
		
	}
	
	/**
	 * number of blocks out of place
	 * @return
	 */
	public int hamming()
	{
		return -1;
	}
	
	/**
	 * sum of Manhattan distances between blocks and goal
	 * @return
	 */
	public int manhattan()
	{
		return -1;
	}
	
	/**
	 * is this board the goal board?
	 * @return
	 */
	public boolean isGoal()
	{
		return false;
	}
	
	/**
	 * a board that is obtained by exchanging any pair of blocks
	 * @return
	 */
	public Board twin()
	{
		return null;
	}
	
	public boolean equals(Object y)
	{
		return false;
	}
	
	/**
	 * all neighboring boards
	 * @return
	 */
	public Iterable<Board> neighbors()
	{
		return null;
	}
	
	/**
	 * string representation of this board (in the output format specified below)
	 */
	public String toString()
	{
		return "";
	}

	/**
	 * unit tests (not graded)
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
