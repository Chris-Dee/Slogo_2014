package commands;

import java.util.List;
import java.util.Map;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.CommandFactory;
import parser.tree.StringNode;

public class IfElse extends ControlCommand{
	
	protected CommandFactory myFactory;
	protected String myElseCommands;
	
	public IfElse() {
		super();
		myFactory = new CommandFactory();
	}
	
	public void setElseCommands(String s){
		if( s != null ) myElseCommands = s;
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myLocalVariableManager);
		myFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		double magnitude = myFactory.runCommands(expr, myTurtles);
		List<StringNode> roots;
		if (magnitude == 0){
			roots = myParser.parse(myElseCommands);
		}
		else{
			roots = myParser.parse(myCommands);
		}
		double answer = myFactory.runCommands(roots, myTurtles);
		backToLastVariableSpace(lastVCopy);
		return answer;
	}

}
