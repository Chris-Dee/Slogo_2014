package commands;

import java.util.List;

import backEnd.VariableManager;
import TurtleStuff.Turtle;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import parser.AbstractParser;
import parser.TextParser;

public abstract class ControlCommand {

	protected AbstractParser myParser;
	protected String myExpression;
	protected List<Turtle> myTurtles;
	protected String myCommands;
	protected VariableManager myVariableManager;
	
	public ControlCommand(){
		myParser = new TextParser();
	}
	
	public void setVariableManager(VariableManager manager){
		myVariableManager = manager;
	}
	
	public void setExpression(String s){
		myExpression = s;
	}
	
	public void setTurtles(List<Turtle> turtles){
		if(turtles != null){
			myTurtles = turtles;
		}
	}
	
	public void setCommands(String s){
		if(s != null) myCommands = s;
	}
	
	public abstract double execute() throws IllegalCommandException, IllegalParameterException;
	
	public abstract String getCommandType();
}
