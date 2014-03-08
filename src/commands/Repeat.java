package commands;

import java.util.List;
import java.util.Map;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import exception.UndefinedVariableException;
import factories.LoopFactory;
import parser.tree.StringNode;

public class Repeat extends ControlCommand{
	
	public static final String REPCOUNT = ":repcount";
	
	protected LoopFactory myLoopFactory;
	
	public Repeat(){
		super();
		myLoopFactory = new LoopFactory();
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException, UndefinedVariableException {
		System.out.println("repeat execute() called");
		System.out.println("myExpression: "+myExpression);
		System.out.println("myCommands: "+myCommands);
		myLoopFactory.setVariableManager(myLocalVariableManager);
		myLoopFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		myParser.setUserCommandManager(myUserCommandManager);
		List<StringNode> expr = myParser.parse(myExpression);
		System.out.println("expr: " + expr.get(0).getCommandString());
		double loop = myLoopFactory.runCommands(expr, myTurtles);
		List<StringNode> commands = myParser.parse(myCommands);
		double answer = myLoopFactory.runAutoLoopCommands(commands, REPCOUNT, loop, myTurtles);
		backToLastVariableSpace(lastVCopy);
		return answer;
	}
}
