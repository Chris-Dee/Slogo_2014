package commands;

import java.util.List;

import frontEnd.Turtle;

public class CSCommand extends AbstractCommand{
	
	public CSCommand(String string, List<Turtle> turtles){
		super(string, turtles);
	}

	@Override
	public double execute() {
		for(Turtle current: myTurtles){
			// erase trails
		}
		HomeCommand homeCom = new HomeCommand("HOME", myTurtles);
		return homeCom.execute();
	}
}
