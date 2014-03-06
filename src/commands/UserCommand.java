package commands;

import java.util.ArrayList;
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
		myVariables = new ArrayList<String>();	
		
		}
	
	/*
	 * Return true if valid parameter String
	 */
	public boolean initializeParameterList(String p){
		myVariables = myParameterParser.constructVariableList(p); // return null if invalid
		if(myVariables ==  null) return false;
		return true;
	}
	
	public void setValueToParameter(String v){
		
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myLocalVariables = new VariableManager();
		return 0;
	}

}
