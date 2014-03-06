package commands;

public class HideTurtle extends NonParameterTurtleCommand{
	
	public HideTurtle(){}

	@Override
	public double execute() {
		System.out.println("Hide Turtle Executed");
		myTurtle.suspend();
		return 0;
	}

	@Override
	public String getCommandType() {
		return "HT";
	}	
}
