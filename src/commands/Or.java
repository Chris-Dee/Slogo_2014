package commands;

public class Or extends TwoParameterOperationCommand {
	
	public Or() {}
	
	@Override
	public double execute() {
		if (myExpression1 != 0 || myExpression2 != 0) return 1;
		return 0;
	}
}
