package commands;

public class Sum extends TwoParameterOperationCommand {
	
	public Sum() {}

	@Override
	public double execute() {
		return myExpression1 + myExpression2;
	}

	@Override
	public String getCommandType() {
		return "SUM";
	}
}
