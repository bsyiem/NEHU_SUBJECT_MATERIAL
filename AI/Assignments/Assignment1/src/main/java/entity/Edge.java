package entity;

public class Edge<T>
{
	private T destination;
	private int weight;
	
	public Edge(T destination, int weight){
		this.destination = destination;
		this.weight = weight;
	}
	
	public T getDestination() {
		return destination;
	}
	
	public void setDestination(T destination) {
		this.destination = destination;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if(obj == null) {
			return false;
		}
		if(!Edge.class.isAssignableFrom(obj.getClass()))
		{
			return false;
		}
		
		@SuppressWarnings("unchecked")
		Edge<T> edgeOther = ((Edge<T>) obj);
		if(edgeOther.destination != this.destination || edgeOther.weight != this.weight)
		{
			return false;
		}
		return true;
	}
}
