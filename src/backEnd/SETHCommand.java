package backEnd;

import java.util.List;

import frontEnd.Turtle;

public class SETHCommand extends ActionCommand{

	public SETHCommand(String string, double degrees, List<Turtle> turtles) {
		super(string, degrees, turtles);
	}
	
	@Override
	protected void moveTurtle(Turtle turtle) {
		
	}

}
