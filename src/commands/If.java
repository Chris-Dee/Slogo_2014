package commands;

import java.util.List;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.CommandFactory;
import parser.tree.StringNode;

public class If extends ControlCommand{
	
	private CommandFactory myFactory;
	
	public If() {
		super();
		myFactory = new CommandFactory();
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		double magnitude = myFactory.runCommands(expr, myTurtles);
		if (magnitude == 0)
			return 0;
		
		List<StringNode> roots = myParser.parse(myCommands);
		double answer = myFactory.runCommands(roots, myTurtles);
		return answer;
	}

}
