package commands;

import parser.tree.StringNode;
import backEnd.RepeatFactory;

public class Repeat extends ControlCommand{
	
	protected RepeatFactory myFactory;
	
	public Repeat(){
		super();
		myFactory = new RepeatFactory();
	}

	@Override
	public String getCommandType() {
		return "REPEAT";
	}

	@Override
	public double execute() {
		if(myMagnitude <= 0) return 0;
		
		StringNode root = myParser.parse(myCommands);
		double answer = 0;
		
		for(int i = 1; i <= myMagnitude; i ++){
			answer = myFactory.runCommands(root, myMagnitude, myTurtle);
		}
		
		return answer;
	}
}
