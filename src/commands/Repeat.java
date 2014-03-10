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
		myLoopFactory.setVariableManager(myLocalVariableManager);
		myLoopFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		myParser.setUserCommandManager(myUserCommandManager);
		List<StringNode> expr = myParser.parse(myExpression);
		double loop = myLoopFactory.runCommands(expr, myTurtles);
		List<StringNode> commands = myParser.parse(myCommands);
		double answer = myLoopFactory.runAutoLoopCommands(commands, REPCOUNT, loop, myTurtles);
		backToLastVariableSpace(lastVCopy);
		return answer;
	}
}
