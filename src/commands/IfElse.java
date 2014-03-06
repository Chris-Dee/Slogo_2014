package commands;

import java.util.List;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import parser.tree.StringNode;
import backEnd.CommandFactory;

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
	public String getCommandType() {
		return "IFELSE";
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		double magnitude = myFactory.runCommands(expr, myTurtle);
		List<StringNode> roots;
		if (magnitude == 0){
			roots = myParser.parse(myElseCommands);
		}
		else{
			roots = myParser.parse(myCommands);
		}
		double answer = myFactory.runCommands(roots, myTurtle);
		return answer;
	}

}
