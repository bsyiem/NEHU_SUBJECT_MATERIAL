package problemSolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entity.AdjacencyList;

public class ProblemReader 
{
	FileReader fReader;
	BufferedReader bReader;
	
	private AdjacencyList<String> cityMap;
	private String startCity;
	
	public String getStartCity() {
		return startCity;
	}

	public void setStartCity(String startCity) {
		this.startCity = startCity;
	}

	public AdjacencyList<String> getCityMap() {
		return cityMap;
	}

	public void setCityMap(AdjacencyList<String> cityMap) {
		this.cityMap = cityMap;
	}
 

	public ProblemReader(String filename)
	{
		this.cityMap = new AdjacencyList<>();
		
		try 
		{
			this.fReader = new FileReader(filename);
			this.bReader= new BufferedReader(fReader);
		    this.startCity = bReader.readLine();
		    this.getAdjacencyList();
		} catch (IOException e ) 
	    {
			System.out.println("Error getting start city");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void getAdjacencyList() 
	{
		try 
		{
			String line;
			while((line = this.bReader.readLine()) != null) 
			{
				String[] line1 = line.split("-");
				String vertex = line1[0];
//				System.out.println("vertex = "+ vertex);
				
				String[] line2 = line1[1].split(",");
				
				for(String edgeWeight : line2)
				{
					String[] edge_weight = edgeWeight.split(" ");
					String edge = edge_weight[0];
					int weight = Integer.parseInt(edge_weight[1]);
//					System.out.println("Edge ="+ edge);
//					System.out.println("weight ="+ weight);
					
					this.cityMap.addUndirectedEdgeToVertex(vertex, edge, weight);
				}
			}	
		}catch (IOException e) { 
			System.out.println("Error getting city Map");
			e.printStackTrace();
		}
		 
	}
}
