package commands;

import java.util.List;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.LoopFactory;
import parser.tree.StringNode;

public class Repeat extends ControlCommand{
	
	protected LoopFactory myFactory;
	
	public Repeat(){
		super();
		myFactory = new LoopFactory();
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		double loop = myFactory.runCommands(expr, myTurtles);
		List<StringNode> commands = myParser.parse(myCommands);
		return myFactory.runAutoLoopCommands(commands, ":repcount", loop, myTurtles);
	}
}
