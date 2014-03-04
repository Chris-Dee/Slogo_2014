package commands;

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
		StringNode expr = myParser.parse(myExpression);
		double magnitude = myFactory.runCommands(expr, myTurtle);
		StringNode root;
		if (magnitude == 0){
			root = myParser.parse(myElseCommands);
		}
		else{
			root = myParser.parse(myCommands);
		}
		double answer = myFactory.runCommands(root, myTurtle);
		return answer;
	}

}
