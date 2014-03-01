package commands;

public class Cos implements AbstractCommand {
	
	private double myDegrees;

	public Cos() {}
	
	@Override
	public double execute() {
		return Math.toDegrees(Math.cos(Math.toRadians(myDegrees)));
	}
	
	public void setMagnitude(double degrees) {
		myDegrees = degrees;
	}

	@Override
	public String getCommandType() {
		return "Cos";
	}

}