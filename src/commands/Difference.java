package commands;

public class Difference extends TwoParameterOperationCommand {
	
	public Difference() {}
	
	@Override
	public double execute() {
		return myExpression1 - myExpression2;
	}
}