package commands;

public class GreaterP extends TwoParameterOperationCommand {
	
	public GreaterP() {}
	
	@Override
	public double execute() {
		if (myExpression1 > myExpression2) return 1;
		return 0;
	}
}
