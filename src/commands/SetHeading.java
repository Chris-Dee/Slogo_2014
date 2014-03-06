package commands;

import TurtleStuff.Turtle;

public class SetHeading extends OneParameterTurtleCommand{
	
	public SetHeading(){}
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		return turtle.setRotation(myMagnitude);
	}

	@Override
	public String getCommandType() {
		return "SETH";
	}
}
