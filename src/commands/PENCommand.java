package commands;

import java.util.List;
import frontEnd.Turtle;

public class PENCommand implements ICommand{
	
	protected String myString;
	protected List<Turtle> myTurtles;
	
	public PENCommand(String string, List<Turtle> turtles){
		myString = string;
		myTurtles = turtles;
	}

	@Override
	public double execute() {
		if(myString.endsWith("d")){
			// revision
			return 1;
		}
		// revision
		return 0;
	}

	@Override
	public String getCommandType() {
		return myString;
	}
	
}
