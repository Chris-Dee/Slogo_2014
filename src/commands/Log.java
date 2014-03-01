package commands;

public class Log implements AbstractCommand {
	
	private double myExpression;
	public Log() {}

	
	@Override
	public double execute() {
		return Math.log(myExpression);
	}
	
	public void setMagnitude(double expression) {
		myExpression = expression;
	}

	@Override
	public String getCommandType() {
		return "LOG";
	}

}