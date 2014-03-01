package commands;

import backEnd.CommandFactory;
import parser.TextParser;

public class Repeat extends ControlCommand{
	
	public Repeat(){
		myParser = new TextParser();
		myFactory = new CommandFactory();
	}

	@Override
	public String getCommandType() {
		return "REPEAT";
	}

	@Override
	public double execute() {
		if(myMagnitude <= 0) return 0;
		
		for(int count)
		
		return 0;
	}
	
	

}
