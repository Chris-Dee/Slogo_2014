package commands;

public class Tan extends OneParameterOperationCommand {
	
	public Tan() {}
	
	@Override
	public double execute() {
		return Math.toDegrees(Math.tan(Math.toRadians(myMagnitude)));
	}
}