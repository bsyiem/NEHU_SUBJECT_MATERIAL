package nehu.ai.logic;

import nehu.ai.entity.Board;

public class SudokuUtils 
{
	public final static int COLUMN_SIZE = 9;
	public final static int ROW_SIZE = 9;
	public final static int[] INPUT_RANGE = {1,9};
	public final static int BOX_SIZE = 3;
	
	/*
	 * check if a board cell is empty
	 * 
	 * @param
	 * row: the row we are checking
	 * column: the column we are checking
	 * board: the current board state
	 */
	public static boolean isFilled(Board board,int row,int col) 
	{
		return board.getCellValue(row,col) == 0?false:true;
	}
	
	
}
