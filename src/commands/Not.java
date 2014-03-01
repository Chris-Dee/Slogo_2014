package commands;

public class Not implements AbstractCommand{
	
	private double myTest;
	
	public Not() {}

	@Override
	public double execute() {
		// TODO Auto-generated method stub
		if (myTest == 0)
			return 1;
		return 0;
	
	}
	
	public void setMagnitude(double test) {
		myTest = test;
	}

	@Override
	public String getCommandType() {
		// TODO Auto-generated method stub
		return "NOT";
	}

}
