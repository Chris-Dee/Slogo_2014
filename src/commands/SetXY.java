package commands;

import frontEnd.Position;

public class SetXY extends TurtleCommand{

	protected double myNewX;
	protected double myNewY;
	
	public SetXY(){}
	
	/*
	 * The parameter m1 will be the new X position
	 * The parameter m2 will be the new Y position
	 */
	public void setDoubleMagnitude(double m1, double m2){
		myNewX = m1;
		myNewY = m2;
	}

	@Override
	public double execute() {
		return myTurtle.setTarget(new Position(myNewX, myNewY));
	}

	@Override
	public String getCommandType() {
		return "GOTO";
	}
}
