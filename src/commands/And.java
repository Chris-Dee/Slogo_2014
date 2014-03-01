package commands;

public class And implements AbstractCommand {
	
	private double myTest1;
	private double myTest2;
	
	public And() {}
	
	@Override
	public double execute() {
		// TODO Auto-generated method stub
		if (myTest1 != 0 && myTest2 != 0)
			return 1;
		return 0;
	}
	
	public void setDoubleMagnitude(double test1, double test2) {
		myTest1 = test1;
		myTest2 = test2;
	}

	@Override
	public String getCommandType() {
		// TODO Auto-generated method stub
		return "AND";
	}

}