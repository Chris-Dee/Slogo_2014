package commands;

public class Tan extends OneParameterOperationCommand {
	
	public Tan() {}
	
	@Override
	public double execute() {
		return Math.tan(Math.toRadians(myMagnitude));
	}
}