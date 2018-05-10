
public class Edge 
{
	String destination;
	int weight;
	
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}	
	
	public Edge(String destination, int weight) 
	{
		this.destination = destination;
		this.weight = weight;
	}
	
	public int getWeight() 
	{
		return weight;
	}
	
	public void setWeight(int weight) 
	{
		this.weight = weight;
	}

	@Override
	public String toString() {
		return this.destination+"("+this.weight+")";
	}
	
    @Override
    public boolean equals(Object o)	
    {
    	Edge e = (Edge)o;
    	return this.weight == e.weight && this.destination.equals(e.destination)? true: false;
 	}
}
