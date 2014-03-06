package commands;

public class Heading extends NonParameterTurtleCommand {

	public Heading() { }

	@Override
	public double execute() {
		return myTurtle.getStats().getRot();
	}

	@Override
	public String getCommandType() {
		return "HEADING";
	}
}
