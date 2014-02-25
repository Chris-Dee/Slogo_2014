package backEnd;

import java.util.List;
import frontEnd.Turtle;

public class RTCommand extends ActionCommand {

	
	public RTCommand(String string, double degrees, List<Turtle> turtles) {
		super(string, degrees, turtles);
	}

	@Override
	protected void moveTurtle(Turtle turtle) {
		//current.setTarget();
	}
}
