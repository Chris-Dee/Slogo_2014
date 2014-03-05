package commands;

import frontEnd.Position;

public class SetXY extends TwoParameterTurtleCommand{
	
	public SetXY(){}

	@Override
	public double execute() {
		return myTurtle.setTarget(new Position(myExpression1, myExpression2));
	}

	@Override
	public String getCommandType() {
		return "GOTO";
	}
}
