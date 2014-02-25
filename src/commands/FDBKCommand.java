package commands;

import java.util.List;
import frontEnd.Turtle;

public class FDBKCommand extends ActionCommand {
	
	protected boolean moveForward;
	
	public FDBKCommand(String string, double pixels, List<Turtle> turtles) {
		super(string, pixels, turtles);
		if(myString.startsWith("F")){ moveForward = true; }
		moveForward = false;
	}

	@Override
	protected double moveTurtle(Turtle turtle) {
		//current.setTarget();
		return 1;
	}
}
