package commands;

import java.util.List;
import frontEnd.Turtle;

public class PENCommand extends AbstractCommand{
	
	public PENCommand(String string, List<Turtle> turtles){
		super(string, turtles);
	}

	@Override
	public double execute() {
		if(myString.endsWith("D")){
			// revision
			return 1;
		}
		// revision
		return 0;
	}	
}
