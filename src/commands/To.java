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
		// check if has name
		// check if there is a parameter list
		// check if there is a command list
		// randomly assign values and see if runs
		// runs add to myCommandmanager and return 1
		// has errors return 0
	}

}
