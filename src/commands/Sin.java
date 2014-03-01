package commands;

public class Sin implements AbstractCommand {
	
	private double myDegrees;

	public Sin() {}

	
	@Override
	public double execute() {
		return Math.toDegrees(Math.sin(Math.toRadians(myDegrees)));
	}
	
	public void setMagnitude(double degrees) {
		myDegrees = degrees;
	}

	@Override
	public String getCommandType() {
		return "SIN";
	}

}
