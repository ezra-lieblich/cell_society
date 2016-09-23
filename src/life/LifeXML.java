package life;

import cellsociety_team01.Simulation;

public class LifeXML extends Simulation {
	private String percentAlive;
	private String percentDead;
	
	public LifeXML (String simulationName, String simulationTitle, String simulationAuthor, String percentX, String percentY, String pAlive, String pDead) {
		super(simulationName, simulationTitle, simulationAuthor, percentX, percentY);
		percentAlive = pAlive;
		percentDead = pDead;
	}
	
	public String getPercentAlive() {
		return percentAlive;
	}
	
	public String getPercentDead() {
		return percentDead;
	}
	
	@Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append("GameOfLife{")
              .append("simulationName='").append(getName()).append("', ")
              .append("simulationTitle='").append(getTitle()).append("', ")
              .append("simulationAuthor='").append(getAuthor()).append("', ")
              .append("percentX='").append(getXSize()).append("', ")
              .append("percentYSize='").append(getYSize()).append("'. ")
              .append("percentAlive='").append(getPercentAlive()).append("', ")
              .append("percentDead='").append(getPercentDead()).append("', ")
              .append('}');
       return result.toString();
    }
}
