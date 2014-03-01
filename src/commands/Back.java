package commands;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Back extends TurtleCommand {
	
	private double myMagnitude;
	
	public Back() {}

	@Override
	public String getCommandType() {
		return "BK";
	}
	
	public void setMagnitude(double magnitude){
		myMagnitude = magnitude;
	}

	@Override
	public double execute() {
		System.out.println("Back magnitude: " + myMagnitude);
		myTurtle.goForward(-1.0*myMagnitude);
		return myMagnitude;
	}
}
