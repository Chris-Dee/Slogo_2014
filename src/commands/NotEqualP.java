package commands;

public class NotEqualP implements AbstractCommand {
	
	private double myTest1;
	private double myTest2;
	
	public NotEqualP() {}
	
	@Override
	public double execute() {
		// TODO Auto-generated method stub
		if (myTest1 != myTest2)
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
		return "NOTEQUALP";
	}

}
