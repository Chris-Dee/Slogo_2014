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
		System.out.println("repeat execute() called");
		myFactory.setVariableManager(myVariableManager);
		System.out.println("setVariableManager");
		System.out.println("myExpression: "+myExpression);
		List<StringNode> expr = myParser.parse(myExpression);
		System.out.println("expr: " + expr.get(0).getCommandString());
		double loop = myFactory.runCommands(expr, myTurtles);
		System.out.println("repeat loop: "+ loop);
		List<StringNode> commands = myParser.parse(myCommands);
		return myFactory.runAutoLoopCommands(commands, ":repcount", loop, myTurtles);
	}
}
