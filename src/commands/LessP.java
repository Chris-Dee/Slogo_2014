package commands;

public class LessP extends TwoParameterOperationCommand {
	
	public LessP() {}
	
	@Override
	public double execute() {
		if (myExpression1 < myExpression2) return 1;
		return 0;
	}

	@Override
	public String getCommandType() {
		return "LESSP";
	}
}