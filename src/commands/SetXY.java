package commands;

public class SetXY extends TurtleCommand{

	protected double myNewX;
	protected double myNewY;
	
	public SetXY(){}
	
	protected double computeEuclidean(double x1, double y1, double x2, double y2){
		return Math.sqrt( Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) );
	}
	
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
		//return myTurtle.setTarget(HOME_X, HOME_Y);
		return 0;
	}

	@Override
	public String getCommandType() {
		return "GOTO";
	}
}
