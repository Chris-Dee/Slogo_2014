package commands;

public class Quotient extends TwoParameterOperationCommand {
	
	public Quotient() {}

	@Override
	public double execute() {
		return myExpression1 / myExpression2;
	}
}