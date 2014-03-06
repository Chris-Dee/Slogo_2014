package commands;

import java.util.List;
import java.util.ResourceBundle;

import parser.tree.StringNode;
import backEnd.LanguageManager;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.ForFactory;

public class For extends ControlCommand {
	protected ForFactory myFactory;
    protected ResourceBundle myProgramLanguage;
	
	public For(){
		myParser = new ForParameterParser();
		myFactory = new ForFactory();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		String variable = myParser.getVariable(myExpression);
		List<StringNode> commands = myParser.parse(myCommands);
		
		return myFactory.runForLoopCommands(expr, commands, variable, myTurtles);
	}
}
