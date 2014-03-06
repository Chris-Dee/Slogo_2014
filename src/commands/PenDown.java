package commands;

public class PenDown extends NonParameterTurtleCommand{
	
	public PenDown(){}

	@Override
	public double execute() {
		myTurtle.lowerPen();
		return 1;
	}

	@Override
	public String getCommandType() {
		return "PD";
	}	
}
