import java.util.LinkedList;

public class AdjacencyListLVE 
{
	LinkedList<VertexEdges> adjacencyList;

	public AdjacencyListLVE() 
	{
		this.adjacencyList = new LinkedList<>();
	}
	
	public void addDirectedEdge(String vertex, String destination,int weight) 
	{
		int indexVertex = this.findVertex(vertex);
		int indexDest = this.findVertex(destination);
		
		if(indexVertex == -1) 
		{
			this.adjacencyList.add(new VertexEdges(vertex));
			indexVertex = this.findVertex(vertex);
		}
		
		if(indexDest == -1) 
		{
			this.adjacencyList.add(new VertexEdges(destination));
		}
		
		this.adjacencyList.get(indexVertex).getEdges().add(new Edge(destination, weight));
	}
	
	public void addUndirectedEdge(String vertex, String destination,int weight) 
	{
		this.addDirectedEdge(vertex, destination, weight);
		this.addDirectedEdge(destination, vertex, weight);
	}
	

	private int findVertex(String vertex) 
	{
		for(VertexEdges v: this.adjacencyList) 
		{
			if(vertex.equals(v.getVertex())) 
			{
				return this.adjacencyList.indexOf(v);
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {
		String value = "";
		for(VertexEdges entry: this.adjacencyList) 
		{
			value+=entry.getVertex();
			value+=" - ";
			for(Edge edge: entry.getEdges()) 
			{
				value+=edge.toString()+",";						
			}
			value = value.substring(0,(value.length()-1));
			value+="\n";
		}
		return value;
	}
}
