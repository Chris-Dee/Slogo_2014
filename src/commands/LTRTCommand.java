package commands;

import java.util.List;
import frontEnd.Turtle;

public class LTRTCommand extends ActionCommand {

	protected boolean turnLeft;
	
	public LTRTCommand(String string, double degrees, List<Turtle> turtles) {
		super(string, degrees, turtles);
		if(myString.startsWith("L")){ turnLeft = true; }
		turnLeft = false;
	}

	@Override
	protected double moveTurtle(Turtle turtle) {
		//current.setTarget();
		return 1;
	}
}
