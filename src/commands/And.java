package commands;

public class And extends TwoParameterOperationCommand {
	
	public And() {}
	
	@Override
	public double execute() {
		if (myExpression1 != 0 && myExpression2 != 0) return 1;
		return 0;
	}

	@Override
	public String getCommandType() {
		return "AND";
	}
}