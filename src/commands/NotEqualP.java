package commands;

public class NotEqualP extends TwoParameterOperationCommand {
	
	public NotEqualP() {}
	
	@Override
	public double execute() {
		if (myExpression1 != myExpression2) return 1;
		return 0;
	}
}
