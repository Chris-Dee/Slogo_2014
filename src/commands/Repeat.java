package commands;

import java.util.List;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import parser.tree.StringNode;
import backEnd.LoopFactory;

public class Repeat extends ControlCommand{
	
	protected LoopFactory myFactory;
	
	public Repeat(){
		super();
		myFactory = new LoopFactory();
	}

	@Override
	public String getCommandType() {
		return "REPEAT";
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		double loop = myFactory.runCommands(expr, myTurtle);
		List<StringNode> commands = myParser.parse(myCommands);
		return myFactory.runCommands(commands, ":repcount", loop, myTurtle);
	}
}
