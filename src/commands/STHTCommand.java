package commands;

import java.util.List;

import frontEnd.Turtle;

public class STHTCommand extends ICommand{
	
	public STHTCommand(String string, List<Turtle> turtles){
		super(string, turtles);
	}

	@Override
	public double execute() {
		if(myString.startsWith("S")){
			// revision
			return 1;
		}
		// revision
		return 0;
	}
}
