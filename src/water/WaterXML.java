package water;

import cellsociety_team01.Simulation;

public class WaterXML extends Simulation {
	private String percentFish;
	private String percentShark;
	private String percentEmpty;
	
	public WaterXML (String simulationName, String simulationTitle, String simulationAuthor, String xSize, String ySize, String pFish, String pShark) {
		super(simulationName, simulationTitle, simulationAuthor, xSize, ySize);
		percentFish = pFish;
		percentShark = pShark;
	}
	
	public String getPercentFish() {
		return percentFish;
	}
	
	public String getPercentShark() {
		return percentShark;
	}
	
	@Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("GameOfLife{")
              .append("simulationName='").append(getName()).append("', ")
              .append("simulationTitle='").append(getTitle()).append("', ")
              .append("simulationAuthor='").append(getAuthor()).append("', ")
              .append("xSize='").append(getXSize()).append("', ")
              .append("ySize='").append(getYSize()).append("'. ")
              .append("percentFish='").append(getPercentFish()).append("', ")
              .append("percentShark='").append(getPercentShark()).append("', ")
              .append('}');
       return result.toString();
    }
}
