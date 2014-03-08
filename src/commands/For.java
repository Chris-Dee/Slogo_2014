package commands;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import parser.AbstractParser;
import parser.tree.StringNode;
import backEnd.Managers.LanguageManager;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
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
	public double execute() throws IllegalCommandException, IllegalParameterException {
		System.out.println("For execute called: "+ myExpression + " " + myCommands);
		myForFactory.setVariableManager(myLocalVariableManager);
		myForFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		AbstractParser.printListNodes(expr); //
		List<StringNode> commands = myParser.parse(myCommands);
		AbstractParser.printListNodes(commands); //
		String variable = expr.get(0).getCommandString();
		expr.remove(0);
		AbstractParser.printListNodes(expr);
		double answer = myForFactory.runForLoopCommands(expr, commands, variable, myTurtles);
		backToLastVariableSpace(lastVCopy);
		return answer;
	}
}
