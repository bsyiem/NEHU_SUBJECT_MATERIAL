
public class GraphRep 
{
	public static void main(String[] args) 
	{
		//could be better if implementing an interface for the following classes to inherit
		AdjacencyListMap adjMap = new AdjacencyListMap();
		AdjacencyListLVE adjLVE = new AdjacencyListLVE();
		AdjacencyListLL adjLL = new AdjacencyListLL();
		
		adjMap.addDirectedEdge("A", "B", 20);
		adjMap.addDirectedEdge("B", "C", 10);
		adjMap.addDirectedEdge("A", "C", 30);
		adjMap.addDirectedEdge("C", "D", 100);
		
		adjLVE.addDirectedEdge("A", "B", 20);
		adjLVE.addDirectedEdge("B", "C", 10);
		adjLVE.addDirectedEdge("A", "C", 30);
		adjLVE.addDirectedEdge("C", "D", 100);
		
		adjLL.addDirectedEdge("A", "B", 20);
		adjLL.addDirectedEdge("B", "C", 10);
		adjLL.addDirectedEdge("A", "C", 30);
		adjLL.addDirectedEdge("D", "C", 35);
		
		System.out.println("Using AdjacencyListMap");
		System.out.println(adjMap);
		System.out.println();
		
		System.out.println("Using AdjacencyListLVE");
		System.out.println(adjLVE);
		System.out.println();
		
		System.out.println("Using AdjacencyListLL");
		System.out.println(adjLL);
		System.out.println();		
	}
}
