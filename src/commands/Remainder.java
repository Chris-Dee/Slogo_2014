package commands;

public class Remainder extends TwoParameterOperationCommand {
	
	public Remainder() {}

	@Override
	public double execute() {
		return myExpression1 % myExpression2;
	}
}