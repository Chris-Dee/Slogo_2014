package commands;

public abstract class TwoParameterTurtleCommand extends TurtleCommand{

	protected double myExpression1;
	protected double myExpression2;
	
	public void setDoubleMagnitude(double expression1, double expression2) {
		myExpression1 = expression1;
		myExpression2 = expression2;
	}
}