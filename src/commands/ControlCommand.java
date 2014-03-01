package commands;

import frontEnd.Turtle;
import parser.AbstractParser;
import parser.TextParser;

public abstract class ControlCommand implements AbstractCommand {

	protected AbstractParser myParser;
	protected double myMagnitude;
	protected Turtle myTurtle;
	protected String myCommands;
	
	public ControlCommand(){
		myParser = new TextParser();
	}
	
	public void setMagnitude(double magnitude){
		myMagnitude = magnitude;
	}
	
	public void setTurtle(Turtle turtle){
		myTurtle = turtle;
	}
	
	public void setCommands(String s){
		if(s != null) myCommands = s;
	}
	
	@Override
	public abstract double execute();
}
