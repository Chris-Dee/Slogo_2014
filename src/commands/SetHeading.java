package commands;

public class SetHeading extends OneParameterTurtleCommand{
	
	public SetHeading(){}

	@Override
	public double execute() {
		return myTurtle.setRotation(myMagnitude);
	}

	@Override
	public String getCommandType() {
		return "SETH";
	}
}
