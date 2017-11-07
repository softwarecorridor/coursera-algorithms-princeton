package assignment_4;

/*
 * - Corner cases.  You may assume that the constructor receives an n-by-n array containing 
 * 	the n2 integers between 0 and n2 − 1, where 0 represents the blank square.
 * - Performance requirements.  Your implementation should support all Board methods in 
 * 	time proportional to n2 (or better) in the worst case. 
 * 
 */
public class Board {
	
	private int[][] currentBoard;
	
	/**
	 * construct a board from an n-by-n array of blocks 
	 * (where blocks[i][j] = block in row i, column j)
	 * @param blocks
	 */
	public Board(int[][] blocks)
	{
		currentBoard = blocks;
	}
	
	/**
	 * board dimension n
	 * @return
	 */
	public int dimension()
	{
		return currentBoard[0].length;
	}
	
	/**
	 * number of blocks out of place
	 * @return
	 */
	public int hamming()
	{
		int counter = 0;
		for(int i= 0; i<dimension()*dimension()-1; i++)
		{
			int posX = i / dimension();
			int posY = i % dimension();
			
			if(currentBoard[posX][posY] != i+1)
			{
				System.out.print("1 ");
				counter++;
			}else
			{
				System.out.print("0 ");
			}
		}
		System.out.print("\n");
		return counter;
	}
	
	/**
	 * sum of Manhattan distances between blocks and goal
	 * @return
	 */
	public int manhattan()
	{
		int counter = 0;
		for(int i= 0; i<dimension()*dimension(); i++)
		{
			int posX = i / dimension();
			int posY = i % dimension();
			
			if(currentBoard[posX][posY] != i+1)
			{
				counter+=manhattan_score(posX, posY, i+1, dimension());
			}
		}
		return counter;
	}
	
	private int manhattan_score(int currentX, int currentY, int goal_node, int dimension)
	{
		int posX = goal_node / dimension();
		int posY = goal_node % dimension();
		
		return Math.abs(currentX-posX) + Math.abs(currentY-posY);
	}
	
	/**
	 * is this board the goal board?
	 * @return
	 */
	public boolean isGoal()
	{
		for(int i= 0; i<dimension()*dimension()-1; i++)
		{
			int posX = i / dimension();
			int posY = i % dimension();
			
			if(currentBoard[posX][posY] != i+1)
			{
				return false;
			}
		}
		return true;
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
		StringBuilder builder = new StringBuilder();
		int size = dimension();
		builder.append(size + "\n");
		for(int i = 0; i<size;i++)
		{
			for (int j = 0; j<size;j++)
			{
				builder.append(String.format("%2d ", currentBoard[i][j]));
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 * unit tests (not graded)
	 * @param args
	 */
	public static void main(String[] args) {
		
		int[][] test_arr  = new int[3][3];
//		test_arr[0][0] = 8;
//		test_arr[1][0] = 4;
//		test_arr[2][0] = 7;
//		test_arr[0][1] = 1;
//		test_arr[1][1] = 0;
//		test_arr[2][1] = 6;
//		test_arr[0][2] = 3;
//		test_arr[1][2] = 2;
//		test_arr[2][2] = 5;
		
		test_arr[0][0] = 1;
		test_arr[1][0] = 4;
		test_arr[2][0] = 7;
		test_arr[0][1] = 2;
		test_arr[1][1] = 5;
		test_arr[2][1] = 8;
		test_arr[0][2] = 3;
		test_arr[1][2] = 6;
		test_arr[2][2] = 0;
		Board b = new Board(test_arr);
		System.out.println(b.hamming());
		System.out.println(b.manhattan());
		
		System.out.print(b.toString());
		System.out.print(b.isGoal());
	}

}
