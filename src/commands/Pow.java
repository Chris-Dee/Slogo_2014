package commands;

public class Pow implements AbstractCommand {
	
	private double myBase;
	private double myExponent;
	public Pow() {}

	
	@Override
	public double execute() {
		return Math.pow(myBase, myExponent);
	}
	
	public void setDoubleMagnitude(double base, double exponent) {
		myBase = base;
		myExponent = exponent;
	}

	@Override
	public String getCommandType() {
		return "POW";
	}

}
