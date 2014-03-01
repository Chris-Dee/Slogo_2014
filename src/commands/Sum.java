package commands;

public class Sum implements AbstractCommand {
	
	private double myExpression1;
	private double myExpression2;
	
	public Sum() {}

	
	@Override
	public double execute() {
		return myExpression1 + myExpression2;
	}
	
	public void setDoubleMagnitude(double expression1, double expression2) {
		myExpression1 = expression1;
		myExpression2 = expression2;
	}

	@Override
	public String getCommandType() {
		return "SUM";
	}

}
