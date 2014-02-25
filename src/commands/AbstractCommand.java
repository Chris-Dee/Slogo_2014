package commands;

import java.util.List;
import frontEnd.Turtle;

public abstract class AbstractCommand {
	
	protected String myString;
	protected List<Turtle> myTurtles;
	
	public AbstractCommand(String string, List<Turtle> turtles){
		myString = string;
		myTurtles = turtles;
	}
	
	public double execute(){
		return 0;
	}
	
	public String getCommandType(){
		return myString;
	}
	
}
