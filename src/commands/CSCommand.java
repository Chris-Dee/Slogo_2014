package commands;

import java.util.List;

import frontEnd.Turtle;

public class CSCommand extends TurtleCommand{
	
	public CSCommand(String string, Turtle turtle){
		super(string, turtle);
	}

	@Override
	public double execute() {
		//myTurtle.eraseTrail();
		HomeCommand homeCom = new HomeCommand("HOME", myTurtle);
		return homeCom.execute();
	}
}
