package commands;

import java.util.List;

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
		List<StringNode> expr = myParser.parse(myExpression);
		double magnitude = myFactory.runCommands(expr, myTurtle);
		if (magnitude == 0)
			return 0;
		
		List<StringNode> roots = myParser.parse(myCommands);
		double answer = myFactory.runCommands(roots, myTurtle);
		return answer;
	}

}
