package commands;

public class Log extends OneParameterOperationCommand {
	
	public Log() {}
	
	@Override
	public double execute() {
		return Math.log(myMagnitude);
	}
}