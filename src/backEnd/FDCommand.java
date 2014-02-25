package backEnd;

import java.util.List;
import frontEnd.Turtle;

public class FDCommand extends ActionCommand {

	
	public FDCommand(String string, double pixels, List<Turtle> turtles) {
		super(string, pixels, turtles);
	}

	@Override
	protected void moveTurtle(Turtle turtle) {
		//current.setTarget();
	}
}
