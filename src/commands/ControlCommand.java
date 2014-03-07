package commands;

import java.util.List;

import backEnd.Managers.UserCommandManager;
import backEnd.Managers.VariableManager;
import TurtleStuff.Turtle;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import parser.TextParser;

public abstract class ControlCommand implements AbstractCommand{

	protected TextParser myParser;
	protected String myExpression;
	protected String myCommands;
	protected List<Turtle> myTurtles;
	protected VariableManager myVariableManager;
	protected UserCommandManager myUserCommandManager;
	
	public ControlCommand(){
		myParser = new TextParser();
		myUserCommandManager = new UserCommandManager();
		myVariableManager = new VariableManager();
	}
	
	public void setUserCommandManager(UserCommandManager userCommandManager){
		myUserCommandManager = userCommandManager;
		myParser.setUserCommandManager(myUserCommandManager);
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
}
