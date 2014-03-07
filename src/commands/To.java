package commands;

import java.util.List;

import parser.ParameterParser;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import backEnd.Managers.UserCommandManager;

public class To extends ControlCommand {
	
	public To(){
		myParser = new ParameterParser();
	}
	
	protected UserCommandManager myCommandManager;
	protected String myName;
	
	public void setCommandName(String n){
		if(n != null) myName = n;
	}
	
	public void setUserCommandManager(UserCommandManager m){
		myCommandManager = m;
	}

	// check if has name
	// check if there is a parameter list
	// check if there is a command list
	// randomly assign values and see if runs
	// runs add to myCommandmanager and return 1
	// has errors return 0
	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		if(myName == null) return 0;
		if(myExpression == null) return 0;
		if(myCommands == null) return 0;
		
		List<StringNode> commandRoots = myParser.parse(myCommands);
		List<StringNode> exprRoots = myParser.parse(myExpression);
		myParser.ifLegal(exprRoots);
		myParser.ifLegal(commandRoots);
		
		UserCommand cmd = new UserCommand();
		cmd.setExpression(myExpression);
		cmd.setCommands(myCommands);
		myCommandManager.createNewUserCommand(myName, cmd);
		return 1;
	}

}
