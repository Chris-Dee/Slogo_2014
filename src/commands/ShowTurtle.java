package commands;

public class ShowTurtle extends TurtleCommand{
	
	public ShowTurtle(){}

	@Override
	public double execute() {
		myTurtle.resume();
		return 1;
	}

	@Override
	public String getCommandType() {
		return "ST";
	}	
}
