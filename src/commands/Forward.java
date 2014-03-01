package commands;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Forward extends TurtleCommand {
	
	private double myMagnitude;
	
	public Forward() {}

	@Override
	public String getCommandType() {
		return "FD";
	}
	
	public void setMagnitude(double magnitude){
		myMagnitude = magnitude;
	}

	@Override
	public double execute() {
		System.out.println("Forward executed: " + myMagnitude);
		myTurtle.goForward(myMagnitude);
		return myMagnitude;
	}
}
