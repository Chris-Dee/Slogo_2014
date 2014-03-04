package commands;

import TurtleStuff.Turtle;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import parser.AbstractParser;
import parser.TextParser;

public abstract class ControlCommand {

	protected AbstractParser myParser;
	protected String myExpression;
	protected Turtle myTurtle;
	protected String myCommands;
	
	public ControlCommand(){
		myParser = new TextParser();
	}
	
	public void setExpression(String s){
		myExpression = s;
	}
	
	public void setTurtle(Turtle turtle){
		myTurtle = turtle;
	}
	
	public void setCommands(String s){
		if(s != null) myCommands = s;
	}
	
	public abstract double execute() throws IllegalCommandException, IllegalParameterException;
	
	public abstract String getCommandType();
}
