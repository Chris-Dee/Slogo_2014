package commands;

public class Cos extends OneParameterOperationCommand {

	public Cos() {}
	
	@Override
	public double execute() {
		return Math.cos(Math.toRadians(myMagnitude));
	}
}