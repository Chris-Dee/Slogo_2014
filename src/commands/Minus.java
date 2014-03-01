package commands;

public class Minus implements AbstractCommand{
	private double myExpression;
	
	public Minus() {}

	@Override
	public double execute() {
		return myExpression * -1;
	}
	
	public void setMagnitude(double expression) {
		myExpression = expression;
	}

	@Override
	public String getCommandType() {
		return "MINUS";
	}

}
