package commands;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Left extends TurtleCommand {
	
	private double myDegree;
	
	public Left() {}

	@Override
	public String getCommandType() {
		return "LT";
	}
	
	public void setMagnitude(double magnitude){
		myDegree = magnitude;
	}

	@Override
	public double execute() {
		myTurtle.addRotation(myDegree);
		return myDegree;
	}
}
