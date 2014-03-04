package commands;

public class Not extends OneParameterOperationCommand{
	
	public Not() {}

	@Override
	public double execute() {
		if (myMagnitude == 0) return 1;
		return 0;
	}

	@Override
	public String getCommandType() {
		return "NOT";
	}
}
