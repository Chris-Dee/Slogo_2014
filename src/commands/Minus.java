package commands;

public class Minus extends OneParameterOperationCommand{
	
	public Minus() {}

	@Override
	public double execute() {
		return myMagnitude * -1;
	}
}
