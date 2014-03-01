package commands;

import parser.AbstractParser;
import parser.TextParser;
import backEnd.CommandFactory;

public abstract class ControlCommand implements AbstractCommand {

	protected AbstractParser myParser;
	protected CommandFactory myFactory;
	protected double myMagnitude;
	
	public ControlCommand(){
		myParser = new TextParser();
		myFactory = new CommandFactory();
	}
	
	public void setMagnitude(double magnitude){
		myMagnitude = magnitude;
	}
	
	@Override
	public abstract double execute();
}
