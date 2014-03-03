package commands;

import parser.tree.StringNode;
import backEnd.RepeatFactory;

public class Repeat extends ControlCommand{
	
	protected RepeatFactory myFactory;
	
	public Repeat(){
		super();
//		System.out.println("Repeat Command Executed");
		myFactory = new RepeatFactory();
	}

	@Override
	public String getCommandType() {
		return "REPEAT";
	}

	@Override
	public double execute() {
		StringNode expr = myParser.parse(myExpression);
		double loop = myFactory.runCommands(expr, myTurtle);
		if(loop <= 0) return 0;
		
		StringNode commands = myParser.parse(myCommands);
		double answer = 0;
		for(int i = 1; i <= loop; i ++){
			answer = myFactory.runCommands(commands, loop, myTurtle);
		}
		return answer;
	}
}
