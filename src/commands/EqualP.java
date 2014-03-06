package commands;

public class EqualP extends TwoParameterOperationCommand{
	
	public EqualP() {}
	
	@Override
	public double execute() {
		if (myExpression1 == myExpression2) return 1;
		return 0;
	}
}