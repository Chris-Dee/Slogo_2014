package commands;

public abstract class OneParameterOperationCommand implements AbstractCommand{

	protected double myMagnitude;
	
	public void setMagnitude(double magnitude) {
		myMagnitude = magnitude;
	}
}
