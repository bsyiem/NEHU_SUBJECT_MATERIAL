package nehu.ai.logic;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nehu.ai.entity.Board;
import nehu.ai.entity.MatrixIndex;
import nehu.ai.entity.ValidBoard;
import nehu.ai.entity.ValidCell;


/*
 * program that takes a 9x9 square matrix representing a suoku puzzle and solves it using constraint satisfaction
 */
public class SudokuAI 
{
	private Board board;
	
	public Board getBoard() {
		return board;
	}
	
	public SudokuAI(int[][] board) 
	{
		this.board=new Board(board);
	}
	
	public void play() 
	{
		ValidBoard validBoard =new ValidBoard(this.board);
		
		if(this.playSudoku(validBoard))
		{
			System.out.println(this.board.toString());
		}
	}
	
	
	
	private boolean playSudoku(ValidBoard validBoard) 
	{
		List<ValidCell> validBoardList = validBoard.getValidBoardList();
		
		/*
		 * this variable is used to propagate the validBoard onto the next level of our DFS search
		 * this is necessary in case of backtracking and we require to undo the changes made to our validBoard
		 */
		ValidBoard validBoardBackup;
		
		
		//if all cells are filled, return success
		if(validBoardList.size()==0) 
		{
			return true;
		}
		
		
		/*
		 * get the row_index and col_index of the validSet we are considering
		 * always pick the valid set containing the least number of possibilities
		 */
		
		ValidCell minimumRangeCell = validBoard.getMinimumRange(); 
		
		
		MatrixIndex index = minimumRangeCell.getIndex();
		int row = index.getRow();
		int column = index.getCol();
		
		//create a new set to iterate as the original set may be modified by later DFS nodes
		Set<Integer> validSet = new HashSet<>(minimumRangeCell.getValidSet().getVSet());

		for(int setElem: validSet)
		{
			//creates a deep copy of validBoard
			validBoardBackup = new ValidBoard(validBoard); 
			
			this.board.setCellValue(row, column, setElem);
			validBoardBackup.updateValids(row, column, setElem);
							
			//the recursive call to filling the next suitable cell
			//returns true if all cells are filled with VALID numbers 
			if(this.playSudoku(validBoardBackup))
			{
				return true;
			}
		}
		
		return false;
	}
	
}
