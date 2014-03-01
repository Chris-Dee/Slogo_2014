package commands;

import parser.tree.StringNode;
import backEnd.CommandFactory;

public class If extends ControlCommand{
	
	private CommandFactory myFactory;
	
	public If() {
		super();
		myFactory = new CommandFactory();
	}

	@Override
	public String getCommandType() {
		// TODO Auto-generated method stub
		return "IF";
	}

	@Override
	public double execute() {
		if (myMagnitude == 0)
			return 0;
		
		StringNode root = myParser.parse(myCommands);
		double answer = myFactory.runCommands(root, myTurtle);
		
		return answer;
	}

}
