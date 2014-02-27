package commands;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Right extends TurtleCommand {
	
	private double myDegree;
	
	public Right() {}

	@Override
	public String getCommandType() {
		return "RT";
	}
	
	public void setMagnitude(double magnitude){
		if(magnitude >= 0){
			myDegree = magnitude;
		}
	}

	@Override
	public double execute() {
		myTurtle.addRotation(-1.0*myDegree);
		return myDegree;
	}
}
