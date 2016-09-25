package tree;

import cellsociety_team01.Simulation;

public class TreeXML extends Simulation {
	private String percentTree;
	private String percentBurn;
	private String percentEmpty;
	private String probCatch;
	public TreeXML (String simulationName, String simulationTitle, String simulationAuthor, String xSize, String ySize, String pTree, String pBurn, String pEmpty, String prob) {
		super(simulationName, simulationTitle, simulationAuthor, xSize, ySize);
		percentTree = pTree;
		percentBurn = pBurn;
		percentEmpty = pEmpty;
		probCatch = prob;
	}
	
	public String getPercentTree() {
		return percentTree;
	}
	
	public String getPercentBurn() {
		return percentBurn;
	}
	
	public String getPercentEmpty() {
		return percentEmpty;
	}
	
	public String getProbCatch() {
		return probCatch;
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
              .append("percentTree='").append(getPercentTree()).append("', ")
              .append("percentBurn='").append(getPercentBurn()).append("', ")
              .append("percentEmpty='").append(getPercentEmpty()).append("', ")
              .append("probCatch='").append(getProbCatch()).append("', ")
              .append('}');
       return result.toString();
    }
}
