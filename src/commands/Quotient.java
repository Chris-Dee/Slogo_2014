package commands;

public class Quotient extends TwoParameterOperationCommand {
	
	public Quotient() {}

	@Override
	public double execute() {
		return myExpression1 / myExpression2;
	}
	
	@Override
	public String getCommandType() {
		return "QUOTIENT";
	}
}