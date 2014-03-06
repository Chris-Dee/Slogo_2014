package commands;

public class Cos extends OneParameterOperationCommand {

	public Cos() {}
	
	@Override
	public double execute() {
		return Math.toDegrees(Math.cos(Math.toRadians(myMagnitude)));
	}
}