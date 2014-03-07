package commands;

import java.util.List;
import java.util.ResourceBundle;

import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;
import backEnd.Managers.LanguageManager;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.ForFactory;

public class For extends ControlCommand {
	protected ForFactory myFactory;
    protected ResourceBundle myProgramLanguage; // not used
	
	public For(){
		myParser = new TextParser();
		myFactory = new ForFactory();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		System.out.println("For execute() called");
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		AbstractParser.printListNodes(expr); //
		List<StringNode> commands = myParser.parse(myCommands);
		AbstractParser.printListNodes(commands); //
		String variable = expr.get(0).getCommandString();
		expr.remove(0);
		AbstractParser.printListNodes(expr);
		return myFactory.runForLoopCommands(expr, commands, variable, myTurtles);
	}
}
