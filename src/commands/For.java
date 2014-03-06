package commands;

import java.util.List;
import java.util.ResourceBundle;

import parser.TextParser;
import parser.tree.StringNode;
import backEnd.LanguageManager;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.ForFactory;

public class For extends ControlCommand {
	protected ForFactory myFactory;
    protected ResourceBundle myProgramLanguage;
	
	public For(){
		myParser = new TextParser();
		myFactory = new ForFactory();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		List<StringNode> commands = myParser.parse(myCommands);
		String variable = expr.get(0).getCommandString();
		expr.remove(0);
		return myFactory.runForLoopCommands(expr, commands, variable, myTurtles);
	}
}
