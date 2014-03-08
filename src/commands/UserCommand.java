package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
	public boolean setValueToParameter(String parameters) throws IllegalCommandException, IllegalParameterException{
		myFactory.setVariableManager(myLocalVariableManager);
		myFactory.setUserCommandManager(myUserCommandManager);
		List<String> values = convertParametersToValues(parameters);
		if(values.size() != myVariables.size()) return false;
		
		for(int i = 0; i < values.size(); i ++){
			if(!AbstractParser.isParameter(values.get(i))  // if not a number or a variable
					|| !values.get(i).startsWith(VariableManager.VARIABLE_PROGRAM_SYNTAX)) return false;
			if(!myLocalVariableManager.isVariable(values.get(i))){
				myLocalVariableManager.setValueToVariable(myVariables.get(i), AbstractParser.convertToDouble(values.get(i)));
			}
		}
		return true;
	}

	protected List<String> convertParametersToValues(String parameters)
			throws IllegalCommandException, IllegalParameterException {
		List<StringNode> roots = myParser.parse(parameters);
		List<String> values = new ArrayList<String>();
		for(StringNode node: roots){
			List<StringNode> current = new ArrayList<StringNode>();
			current.add(node);
			double value = myFactory.runCommands(current, myTurtles);
			values.add(String.valueOf(value));
		}
		return values;
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		List<StringNode> roots = myParser.parse(myCommands);
		double answer = myFactory.runCommands(roots, myTurtles);		
		backToLastVariableSpace(lastVCopy);
		return answer;
	}
}
