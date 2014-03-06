package commands;

import java.util.List;
import java.util.ResourceBundle;

import parser.tree.StringNode;
import backEnd.LanguageManager;
import backEnd.VariableManager;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.LoopFactory;

public class DoTimes extends ControlCommand {
	protected LoopFactory myFactory;
    protected ResourceBundle myProgramLanguage;
	
	public DoTimes(){
		myParser = new DoTimesParameterParser();
		myFactory = new LoopFactory();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		int vStart = findIndexOfVariableSyntax(myExpression); // vStart has the index of ":"
		if(vStart == myExpression.length()-1){ throw new IllegalParameterException(); }
		int vEnd = findIndexOfFirstEmptySpace(myExpression.substring(vStart));
		if(vEnd == myExpression.substring(vStart).length()){ throw new IllegalParameterException(); }
		String variable = myExpression.substring(vStart, vEnd);
		
		List<StringNode> expr = myParser.parse(myExpression.substring(vEnd));
		double loop = myFactory.runCommands(expr, myTurtles);
		List<StringNode> commands = myParser.parse(myCommands);
		return myFactory.runAutoLoopCommands(commands, variable, loop, myTurtles);
	}

	protected int findIndexOfVariableSyntax(String s) {
		int vStart = s.length()-1;
		for(int i = 0; i < s.length()-1; i ++){
			if(s.substring(i, i+1).equals(myProgramLanguage.getString(VariableManager.VARIABLE_PROGRAM_SYNTAX))){
				vStart = i;
				break;
			}
		}
		return vStart;
	}
	
	protected int findIndexOfFirstEmptySpace(String s){
		int answer = s.length();
		for(int i = 0; i < s.length(); i ++){
			if(s.charAt(i) == ' '){
				answer = i;
				break;
			}
		}
		return answer;
	}
}
