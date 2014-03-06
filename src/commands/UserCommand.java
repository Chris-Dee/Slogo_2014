package commands;

import java.util.List;

import backEnd.VariableManager;
import parser.AbstractParser;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.CommandFactory;

public class UserCommand extends ControlCommand {
	
	protected List<String> myVariables;
	protected AbstractParser myParameterParser;
	protected VariableManager myLocalVariables;
	protected CommandFactory myFactory;
	
	public UserCommand(){
		super();
		myParameterParser = new ParameterParser();
	}
	
	public void setParameter(String p){

	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myLocalVariables = new VariableManager();
		return 0;
	}

}
