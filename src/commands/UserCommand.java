package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import backEnd.Managers.LanguageManager;
import backEnd.Managers.VariableManager;
import parser.AbstractParser;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.CommandFactory;

public class UserCommand extends ControlCommand {
	
	protected List<String> myVariables;
	protected VariableManager myLocalVariableManager;
	protected CommandFactory myFactory;
	protected ResourceBundle myProgramLanguage;
	
	public UserCommand(){
		super();
		myVariables = new ArrayList<String>();	
		myFactory = new CommandFactory();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
	}
	
	/*
	 * Initialize myVariables list
	 */
	public void initializeParameterList(String p) throws IllegalParameterException, IllegalCommandException{
		List<StringNode> list = myParser.parse(p);
		for(StringNode node: list){
			String current = node.getCommandString();
			if(!current.startsWith(myProgramLanguage.getString(VariableManager.VARIABLE_PROGRAM_SYNTAX))){
				throw new IllegalParameterException();
			}
			myVariables.add(current);
		}
	}
	
	/*
	 * return false if parameters not set successfully
	 * the list values should contain either numbers or global variables
	 */
	public boolean setValueToParameter(List<String> values){
		if(values.size() != myVariables.size()) return false;
		myLocalVariableManager = new VariableManager();
		for(int i = 0; i < values.size(); i ++){
			if(!AbstractParser.isParameter(values.get(i))) return false;
			if(myVariableManager.isVariable(values.get(i))){
				myLocalVariableManager.setValueToVariable(myVariables.get(i), myVariableManager.getValueOfVariable(values.get(i)));
			}
			else{
				myLocalVariableManager.setValueToVariable(myVariables.get(i), AbstractParser.convertToDouble(values.get(i)));
			}
		}
		return true;
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myLocalVariableManager);
		List<StringNode> roots = myParser.parse(myCommands);
		return myFactory.runCommands(roots, myTurtles);
	}

}
