package nehu.ai.entity;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import nehu.ai.logic.SudokuUtils;

public class ValidBoard 
{
	
	List<ValidCell> validBoard;
	
	public List<ValidCell> getValidBoardList() {
		return validBoard;
	}

	public ValidBoard(Board board) {
		super();
		this.validBoard = new ArrayList<>();
		this.getValidBoard(board);
	}
	
	
	//creates a deep copy of the object.
	public ValidBoard(ValidBoard vB) 
	{
		this.validBoard = new ArrayList<>();
		for(ValidCell cell: vB.getValidBoardList()) 
		{
			MatrixIndex index = new MatrixIndex(cell.getIndex().getRow(),cell.getIndex().getCol());
			ValidSet validSet = new ValidSet(new HashSet<>(cell.getValidSet().getVSet()));
			ValidCell cellCopy = new ValidCell(index,validSet);
			this.validBoard.add(cellCopy);
		}
	}
	
	/*
	 * checks if placing @number in @board[@row][@column] is a valid choice in the current state of the sudoku puzzle
	 * 
	 * @param
	 * board: the current state of the sudoku board
	 * row: the row we are considering 
	 * column: the column we are considering 
	 * number: the input number we wish to check is valid or not in board[row][column]
	 */
 	public static boolean isValid(Board board, int row, int column, int number) 
	{
		return isValidBox(board, row, column, number) && isValidRow(board, row, number) && isValidColumn(board,column, number);
	}
	
 	/*
	 * checks if placing @number in @board[@row][@column] is valid in row
	 * 
	 * @param
	 * board: the current state of the sudoku board
	 * row: the row we are considering
	 * number: the input number we wish to check is valid or not in board[row][column]
	 */
	private static boolean isValidRow(Board board, int row, int number) 
	{
		for(int column=0;column<SudokuUtils.COLUMN_SIZE;column++)
		{
			if(number == board.getCellValue(row,column))
			{
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * checks if placing @number in @board[@row][@column] is valid in column
	 * 
	 * @param
	 * board: the current state of the sudoku board
	 * column: the column we are considering 
	 * number: the input number we wish to check is valid or not in board[row][column]
	 */
	private static boolean isValidColumn(Board board, int column, int number) 
	{
		for(int row=0;row<SudokuUtils.ROW_SIZE;row++)
		{
			if(number == board.getCellValue(row,column))
			{
				return false;
			}
		}
		return true;
	}
	
	/*
	 * checks if placing @number in @board[@row][@column] is valid in the 3x3 sub-box of the sudoku puzzle
	 * 
	 * @param
	 * board: the current state of the sudoku board
	 * row: the row we are considering 
	 * column: the column we are considering 
	 * number: the input number we wish to check is valid or not in board[row][column]
	 */
	private static boolean isValidBox(Board board, int row,int column, int number) 
	{
		int row_min = (row/SudokuUtils.BOX_SIZE)*SudokuUtils.BOX_SIZE;
		int row_max = row_min+SudokuUtils.BOX_SIZE;
		
		int col_min = (column/SudokuUtils.BOX_SIZE)*SudokuUtils.BOX_SIZE;
		int col_max = col_min+SudokuUtils.BOX_SIZE;
		
		for(int i = row_min;i<row_max;i++)
		{
			for(int j = col_min;j<col_max;j++)
			{
				if(number == board.getCellValue(i, j)) 
				{
					return false;
				}
			}
		}
		return true;
	}
	
	
	/*
	 * returns the set of valid numbers that can be placed in board[row][column]
	 * 
	 * @param
	 * board: the current state of the sudoku board
	 * row: the row we are considering 
	 * column: the column we are considering 
	 */
	private static Set<Integer> getValidSet(int row,int column, Board board)
	{
		Set<Integer> validSet = new HashSet<>();
		for(int i = SudokuUtils.INPUT_RANGE[0]; i<=SudokuUtils.INPUT_RANGE[1]; i++)
		{
			if(isValid(board, row, column, i)) 
			{
				validSet.add(i);
			}
		}
		return validSet;
	}
	
	/*
	 * takes the current state of the board and returns a validBoard Lists that contains the position of the board cell and its respective set of valid inputs 
	 * 
	 * @param
	 * board: the current state of the board
	 */
	private void getValidBoard(Board board)
	{
		for(int row = 0; row < SudokuUtils.ROW_SIZE; row++) 
		{
			for(int col = 0; col < SudokuUtils.COLUMN_SIZE; col++)
			{
				if(!SudokuUtils.isFilled(board, row, col))
				{
					this.validBoard.add(new ValidCell(new MatrixIndex(row, col), new ValidSet(getValidSet(row, col, board))));
				}
			}
		}
	}
	
	/*
	 * propagates a new constraint; removes a no longer valid number from the valid sets in the entire row
	 * 
	 * @param
	 * row: the row we wish to update
	 * number: the number that is no longer valid
	 */
	private void updateRow(int row, int number)
	{
		int index;
		for(int col = 0; col<SudokuUtils.COLUMN_SIZE; col++) 
		{
			index = this.getIndexOfCell(row, col);
			if(index!=-1) 
			{
				this.validBoard.get(index).getValidSet().getVSet().remove(number);
			}
			
		}
	}
	
	/*
	 * propagates a new constraint; removes a no longer valid number from the valid sets in the entire column
	 * 
	 * @param
	 * column: the column we wish to update
	 * number: the number that is no longer valid
	 */
	private void updateColumn(int col, int number)
	{
		int index;
		for(int row = 0; row<SudokuUtils.ROW_SIZE; row++) 
		{
			index = this.getIndexOfCell(row, col);
			if(index!=-1) 
			{
				this.validBoard.get(index).getValidSet().getVSet().remove(number);
			}
		}
	}
	
	/*
	 * propagates a new constraint; removes a no longer valid number from the valid sets in the respective sub_box
	 * 
	 * @param
	 * row: the row we are considering
	 * column: the column we are considering 
	 * number: the number that is no longer valid
	 */
	private void updateBox(int row, int column, int number)
	{
		int row_min = (row/SudokuUtils.BOX_SIZE)*SudokuUtils.BOX_SIZE;
		int row_max = row_min+SudokuUtils.BOX_SIZE;
		
		int col_min = (column/SudokuUtils.BOX_SIZE)*SudokuUtils.BOX_SIZE;
		int col_max = col_min+SudokuUtils.BOX_SIZE;
		
		int index;
		
		for(int i = row_min;i<row_max;i++)
		{
			for(int j = col_min;j<col_max;j++)
			{
				index = this.getIndexOfCell(i, j);
				if(index!=-1) 
				{
					this.validBoard.get(index).getValidSet().getVSet().remove(number);
				}
			}
		}
	}
	
	/*
	 * updates the validSets of the rows,columns and box when a new number is inserted into board[row][col]
	 * Also removes the element from the validBoard list as it will considered as FILLED.
	 * 
	 * @param
	 * row: the row we are considering
	 * column: the column we are considering 
	 * number: the number that is no longer valid
	 */
	public void updateValids(int row, int column, int number) 
	{
		updateBox(row, column, number);
		updateColumn(column, number);
		updateRow(row, number);
		
		this.remove(row, column);
	}
	
	
	/*
	 * gets the index of the validBoard List where the stored matrix index is equal to row and column
	 * 
	 * @param
	 * row: the row of the cell we wish the get the index of in the validBoard List
	 * column: the column of the cell we wish the get the index of in the validBoard List
	 */
	private int getIndexOfCell(int row, int col)
	{
		MatrixIndex index = new MatrixIndex(row, col);
		for(ValidCell cell: this.validBoard) 
		{	
			if(cell.getIndex().equals(index)) 
			{
				return this.validBoard.indexOf(cell);
			}
		}
		return -1;
	}
	
	/*
	 * removes an item from the validBoard
	 */
	private void remove(int row, int col)
	{
		int index = getIndexOfCell(row, col);
		this.validBoard.remove(index);
	}
	
	/*
	 * prints the valid board for debugging
	 */
	public void printValidBoard()
	{
		for(ValidCell cell: this.validBoard)
		{
			System.out.println(cell.toString());
		}
	}
	
	/*
	 * sorts the valid board by the size of the validSets
	 */
	public void sortValidBoard() 
	{
		Collections.sort(this.validBoard);
	}
	
	/*
	 * returns the valid set with the least number of elements
	 */
	public ValidCell getMinimumRange() 
	{
		return Collections.min(this.validBoard);
	}
}
