package xo;

import cellsociety_team01.Simulation;

public class XOXML extends Simulation {
	private String percentX;
	private String percentO;
	
	public XOXML (String simulationName, String simulationTitle, String simulationAuthor, String xSize, String ySize, String pX, String pO) {
		super(simulationName, simulationTitle, simulationAuthor, xSize, ySize);
		percentX = pX;
		percentO = pO;
	}
	
	public String getPercentX() {
		return percentX;
	}
	
	public String getPercentO() {
		return percentO;
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
              .append("percentX='").append(getPercentX()).append("', ")
              .append("percentO='").append(getPercentO()).append("', ")
              .append('}');
       return result.toString();
    }
}
