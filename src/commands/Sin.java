package commands;

public class Sin extends OneParameterOperationCommand{
	
	public Sin() {}

	@Override
	public double execute() {
		return Math.toDegrees(Math.sin(Math.toRadians(myMagnitude)));
	}
}
