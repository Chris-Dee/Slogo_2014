package commands;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import backEnd.UserCommandManager;

public class To extends ControlCommand {
	
	protected UserCommandManager myCommandManager;
	protected String myName;
	
	public void setCommandName(String n){
		myName = n;
	}
	
	public void setUserCommandManager(UserCommandManager m){
		myCommandManager = m;
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		// TODO Auto-generated method stub
		return 0;
	}

}
