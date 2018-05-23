package nehu.ai.entity;

import nehu.ai.logic.SudokuUtils;

public class Board 
{
	private int[][] cell;
	
	public int[][] getboard() {
		return cell;
	}

	public Board(int[][] board) 
	{
		this.cell =new int[SudokuUtils.ROW_SIZE][SudokuUtils.COLUMN_SIZE];
		for(int i =0; i< SudokuUtils.ROW_SIZE;i++)
		{
			for(int j = 0;j<SudokuUtils.COLUMN_SIZE;j++) 
			{
				this.setCellValue(i, j, board[i][j]);;
			}
		}
	}
	
	public int getCellValue(int i,int j) 
	{
		return this.cell[i][j];
	}
	
	public void setCellValue(int i,int j, int number) 
	{
		this.cell[i][j] = number;
	}

	@Override
	public String toString() 
	{
		String string  = "";
		for(int i =0 ;i<SudokuUtils.ROW_SIZE;i++)
		{
			for(int j=0;j<SudokuUtils.COLUMN_SIZE;j++) 
			{ 
				string+=cell[i][j]+" ";
			}
			string+="\n";
		}
		return string;
	}
}
