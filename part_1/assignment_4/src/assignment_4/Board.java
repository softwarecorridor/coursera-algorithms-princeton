package assignment_4;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

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
		for(int i= 0; i<dimension()*dimension()-1; i++)
		{
			int posX = i / dimension();
			int posY = i % dimension();
			//check we are not at edge
			
			if(currentBoard[posX][posY] == 0 || posY+1 == dimension())
			{
				continue;
			}

			if(currentBoard[posX][posY+1] != 0)
			{
				int[][] newBoard = swap(posX, posY, posX, posY+1);
				return new Board(newBoard);
			}
		}
		
		return null;
	}
	
	public boolean equals(Object y)
	{
		return (y instanceof Board && toString().equals(y.toString()));
	}
	
	
	/**
	 * Return a copy of the array with the two positions swapped.
	 * 
	 * @param posX1
	 * @param posY1
	 * @param posX2
	 * @param posY2
	 * @return
	 */
	private int[][] swap(int posX1, int posY1, int posX2, int posY2 )
	{
		int[][] clonedArray = copyBoard(currentBoard);
		int temp = clonedArray[posX1][posY1];
		clonedArray[posX1][posY1] = clonedArray[posX2][posY2];
		clonedArray[posX2][posY2] = temp;
		return clonedArray;
	}
	
	private int[][] copyBoard(int[][] src)
	{
		
		int[][] target = new int[src.length][src[0].length];
		for(int i = 0; i<src[0].length;i++)
		{
			for(int j = 0; j< src[0].length;j++)
			{
				target[i][j] = src[i][j];
			}
		}
		return target;
	}
	
	/**
	 * all neighboring boards
	 * @return
	 */
	public Iterable<Board> neighbors()
	{
		ArrayList<Board> neighborBoards = new ArrayList<>();
		
		for(int i= 0; i<dimension()*dimension(); i++)
		{
			int posX = i / dimension();
			int posY = i % dimension();
			
			if(currentBoard[posX][posY] == 0)
			{
				System.out.println(String.format("%d,%d", posX, posY));
				
				int upPosition = posX-1;
				if(upPosition >=0)
				{
					System.out.print(String.format("%d,%d", upPosition, posY));
					System.out.print(": " + currentBoard[upPosition][posY]);
					int[][] newBoard = swap(posX,posY,upPosition,posY);
					neighborBoards.add(new Board(newBoard));
				}else
				{
					System.out.println("out of bounds");
				}
				
				int downPosition = posX+1;
				if(downPosition<dimension())
				{
					System.out.print(String.format("%d,%d", downPosition, posY));
					System.out.print(": " + currentBoard[downPosition][posY]+"\n");
					int[][] newBoard = swap(posX,posY,downPosition,posY);
					neighborBoards.add(new Board(newBoard));
				}else
				{
					System.out.println("out of bounds");
				}
				
				int leftPosition = posY-1;
				if(leftPosition>=0)
				{
					System.out.print(String.format("%d,%d", posX, leftPosition));
					System.out.print(": " + currentBoard[posX][leftPosition]+"\n");
					int[][] newBoard = swap(posX,posY,posX,leftPosition);
					neighborBoards.add(new Board(newBoard));
				}else
				{
					System.out.println("out of bounds");
				}
				
				int rightPosition = posY+1;
				if(rightPosition<dimension())
				{
					System.out.print(String.format("%d,%d", posX, rightPosition));
					System.out.print(": " + currentBoard[posX][rightPosition]+"\n");
					int[][] newBoard = swap(posX,posY,posX,rightPosition);
					neighborBoards.add(new Board(newBoard));
				}else
				{
					System.out.println("out of bounds");
				}
				break;
			}
		
		}
		
		
		return neighborBoards;
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
		test_arr[0][1] = 0;
		test_arr[1][1] = 5;
		test_arr[2][1] = 8;
		test_arr[0][2] = 3;
		test_arr[1][2] = 6;
		test_arr[2][2] = 2;
		
//		int[][] test_arr  = new int[2][2];
//		test_arr[0][0] = 1;
//		test_arr[0][1] = 0;
//		test_arr[1][0] = 3;
//		test_arr[1][1] = 4;
// 		
		Board a = new Board(test_arr);
		
		System.out.println(a.toString());
		System.out.println(a.twin().toString());

	}

}
