package commands;

/*
 * This command makes the turtle move forward by certain pixels
 */
public class Left extends OneParameterTurtleCommand {
	
	public Left() {}

	@Override
	public String getCommandType() {
		return "LT";
	}

	@Override
	public double execute() {
		myTurtle.addRotation(myMagnitude);
		return myMagnitude;
	}
}
