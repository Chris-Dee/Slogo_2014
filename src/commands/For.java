package commands;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import parser.TextParser;
import parser.tree.StringNode;
import backEnd.Managers.LanguageManager;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import exception.UndefinedVariableException;
import factories.ForFactory;

public class For extends ControlCommand {
	protected ForFactory myForFactory;
    protected ResourceBundle myProgramLanguage; // not used
	
	public For(){
		super();
		myForFactory = new ForFactory();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException, UndefinedVariableException {
		TextParser parser = new TextParser();
		parser.setUserCommandManager(myUserCommandManager);
		myForFactory.setVariableManager(myLocalVariableManager);
		myForFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		List<StringNode> expr = parser.parse(myExpression);
		myForFactory.runCommands(expr, myTurtles);
		myLocalVariableManager.setValueToVariable(":A", 1);
		List<StringNode> commands = myParser.parse(myCommands);
		String variable = expr.get(0).getCommandString();
		System.out.println("variable: " + variable);
		expr.remove(0);
		double answer = 0;
		try{
			answer = myForFactory.runForLoopCommands(expr, commands, variable, myTurtles);
		} catch (Exception e){ throw new IllegalCommandException(); }
		backToLastVariableSpace(lastVCopy);
		return answer;
	}
}
