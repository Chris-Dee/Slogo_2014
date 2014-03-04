package commands;

public class Pow extends TwoParameterOperationCommand {
	
	public Pow() {}
	
	@Override
	public double execute() {
		return Math.pow(myExpression1, myExpression2);
	}

	@Override
	public String getCommandType() {
		return "POW";
	}
}
