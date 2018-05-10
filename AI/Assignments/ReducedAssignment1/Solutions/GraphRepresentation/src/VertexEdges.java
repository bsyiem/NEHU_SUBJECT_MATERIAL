import java.util.LinkedList;

public class VertexEdges 
{
	String vertex;
	LinkedList<Edge> edges;
	
	public String getVertex() {
		return vertex;
	}

	public void setVertex(String vertex) {
		this.vertex = vertex;
	}
	
	public LinkedList<Edge> getEdges() {
		return edges;
	}

	public void setEdges(LinkedList<Edge> edges) {
		this.edges = edges;
	}

	public VertexEdges(String vertex) {
		super();
		this.vertex = vertex;
		this.edges = new LinkedList<Edge>();
	}
}
