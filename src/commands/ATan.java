package commands;

public class ATan implements AbstractCommand {
	
	private double myDegrees;
	public ATan() {}

	
	@Override
	public double execute() {
		return Math.toDegrees(Math.atan(Math.toRadians(myDegrees)));
	}
	
	public void setMagnitude(double degrees) {
		myDegrees = degrees;
	}

	@Override
	public String getCommandType() {
		return "ATAN";
	}

}
