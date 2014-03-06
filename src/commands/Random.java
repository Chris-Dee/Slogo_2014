package commands;

public class Random extends OneParameterOperationCommand{
	
	public Random() {}
	
	@Override
	public double execute() {
		return Math.random()*myMagnitude;
	}
}
