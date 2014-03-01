package commands;

public class Random implements AbstractCommand{
	private double myMax;
	
	public Random() {}

	public void setMagnitude(double max) {
		myMax = max;
	}
	
	@Override
	public double execute() {
		// TODO Auto-generated method stub
		return Math.random()*myMax;
	}

	@Override
	public String getCommandType() {
		// TODO Auto-generated method stub
		return "RANDOM";
	}
	
	
}
