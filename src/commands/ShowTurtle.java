package commands;

public class ShowTurtle extends TurtleCommand{
	
	public ShowTurtle(){}

	@Override
	public double execute() {
		System.out.println("Show Turtle Executed");
		myTurtle.resume();
		return 1;
	}

	@Override
	public String getCommandType() {
		return "ST";
	}	
}
