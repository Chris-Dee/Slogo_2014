package commands;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import parser.tree.StringNode;
import backEnd.CommandFactory;

public class If extends ControlCommand{
	
	private CommandFactory myFactory;
	
	public If() {
		super();
		myFactory = new CommandFactory();
	}

	@Override
	public String getCommandType() {
		return "IF";
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		StringNode expr = myParser.parse(myExpression);
		double magnitude = myFactory.runCommands(expr, myTurtle);
		if (magnitude == 0)
			return 0;
		
		StringNode root = myParser.parse(myCommands);
		double answer = myFactory.runCommands(root, myTurtle);
		return answer;
	}

}
