package nehu.ai.entity;

public class ValidCell implements Comparable<ValidCell>
{
	private MatrixIndex index;
	private ValidSet validSet;
	
	
	public ValidSet getValidSet() {
		return validSet;
	}

	public void setValidSet(ValidSet validSet) {
		this.validSet = validSet;
	}
	
	public ValidCell(MatrixIndex index, ValidSet validSet) 
	{
		super();
		this.index = index;
		this.validSet = validSet;
	}
	
	public MatrixIndex getIndex() {
		return index;
	}

	@Override
	public int compareTo(ValidCell o) 
	{
		if(this.getValidSet().getVSet().size() > o.getValidSet().getVSet().size()) 
		{
			return 1;
		}
		else if (this.getValidSet().getVSet().size() < o.getValidSet().getVSet().size()) 
		{
			return -1;
		}
		return 0;
	}
	
	@Override
	public String toString() 
	{
		return "["+index.getRow()+"]["+index.getCol()+"] = "+validSet.getVSet().toString();
	}
	
}
