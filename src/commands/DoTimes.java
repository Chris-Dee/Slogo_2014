package commands;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import parser.TextParser;
import parser.tree.StringNode;
import backEnd.Managers.LanguageManager;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.LoopFactory;

public class DoTimes extends ControlCommand {
	protected LoopFactory myLoopFactory;
    protected ResourceBundle myProgramLanguage;
	
	public DoTimes(){
		myParser = new TextParser();
		myLoopFactory = new LoopFactory();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		initExecute();
		myLoopFactory.setVariableManager(myVariableManager);
		myLoopFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		
		List<StringNode> expr = myParser.parse(myExpression);
		String variable = expr.get(0).getCommandString();
		expr.remove(0);
		double loop = myLoopFactory.runCommands(expr, myTurtles);
		List<StringNode> commands = myParser.parse(myCommands);
		double answer = myLoopFactory.runAutoLoopCommands(commands, variable, loop, myTurtles);
		backToLastVariableSpace(lastVCopy);
		return answer;
	}
}
