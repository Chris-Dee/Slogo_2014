package commands;

import java.util.List;
import java.util.Map;

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
		initExecute();
		myFactory.setVariableManager(myLocalVariableManager);
		myFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		double magnitude = myFactory.runCommands(expr, myTurtles);
		if (magnitude == 0)
			return 0;
		
		List<StringNode> roots = myParser.parse(myCommands);
		double answer = myFactory.runCommands(roots, myTurtles);
		backToLastVariableSpace(lastVCopy);
		return answer;
	}

}
