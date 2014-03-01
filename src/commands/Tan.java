package commands;

public class Tan implements AbstractCommand {
	
	private double myDegrees;
	public Tan() {}

	
	@Override
	public double execute() {
		return Math.toDegrees(Math.tan(Math.toRadians(myDegrees)));
	}
	
	public void setMagnitude(double degrees) {
		myDegrees = degrees;
	}

	@Override
	public String getCommandType() {
		return "TAN";
	}

}