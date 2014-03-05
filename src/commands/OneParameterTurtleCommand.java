package commands;

public abstract class OneParameterTurtleCommand extends TurtleCommand{

	protected double myMagnitude;
	
	public void setMagnitude(double magnitude) {
		myMagnitude = magnitude;
	}
}
