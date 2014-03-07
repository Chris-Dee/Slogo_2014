package commands;

public class Sin extends OneParameterOperationCommand{
	
	public Sin() {}

	@Override
	public double execute() {
		return Math.sin(Math.toRadians(myMagnitude));
	}
}
