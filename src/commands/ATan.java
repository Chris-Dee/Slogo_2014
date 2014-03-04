package commands;

public class ATan extends OneParameterOperationCommand {

	public ATan() {}
	
	@Override
	public double execute() {
		return Math.toDegrees(Math.atan(Math.toRadians(myMagnitude)));
	}

	@Override
	public String getCommandType() {
		return "ATAN";
	}
}
