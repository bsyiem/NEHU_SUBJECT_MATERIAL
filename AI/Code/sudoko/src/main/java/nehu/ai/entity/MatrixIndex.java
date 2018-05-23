package nehu.ai.entity;

public class MatrixIndex 
{
	private int row;
	private int col;
	
	public MatrixIndex(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	@Override
	public boolean equals(Object obj) {
		MatrixIndex matrixIndex = (MatrixIndex)obj;
		if(this.row == matrixIndex.getRow() && this.col == matrixIndex.getCol()) 
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "["+this.row+"]["+this.col+"]";
	}
	
}
