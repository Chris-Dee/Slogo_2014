package commands;

import java.util.List;

import parser.AbstractParser;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import backEnd.Managers.UserCommandManager;

public class To extends ControlCommand {
	
	public To(){
		super();
	}
	
	protected UserCommandManager myCommandManager;
	protected String myName;
	
	public void setCommandName(String n){
		if(n != null) myName = n;
	}
	
	public void setUserCommandManager(UserCommandManager m){
		myCommandManager = m;
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		if(myName == null) return 0;
		if(myExpression == null) return 0;
		if(myCommands == null) return 0;
		
		List<StringNode> exprRoots = myParser.parse(myExpression);
		if(!ifLegalParameter(exprRoots)) return 0;
		
		List<StringNode> commandRoots = myParser.parse(myCommands);
		myParser.ifLegal(commandRoots);
		
		UserCommand cmd = new UserCommand();
		cmd.setExpression(myExpression);
		cmd.setCommands(myCommands);
		myCommandManager.createNewUserCommand(myName, cmd);
		return 1;
	}
	
	protected boolean ifLegalParameter(List<StringNode> roots){
		for(StringNode root: roots){
			if(!myParser.isVariable(root.getCommandString())){
				if(!AbstractParser.isParameter(root.getCommandString())) return false;
			}
		}
		return true;
	}

}
