package commands;

import java.util.List;
import frontEnd.Turtle;

public class FDBKCommand extends ActionCommand {
	
	protected boolean moveForward;
	
	public FDBKCommand(String string, double pixels, List<Turtle> turtles) {
		super(string, pixels, turtles);
		if(myString.startsWith("f")){ moveForward = true; }
		moveForward = false;
	}

	@Override
	protected void moveTurtle(Turtle turtle) {
		//current.setTarget();
	}
}
