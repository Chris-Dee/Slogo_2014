package commands;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import parser.AbstractParser;
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
		System.out.println();
		System.out.println("For execute called: "+ myExpression + " " + myCommands);
		myForFactory.setVariableManager(myLocalVariableManager);
		myForFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		// Pay attention here
		System.out.println();
		System.out.println("Pre expression: "+expr.get(0).getCommandString()+" "+expr.get(1).getCommandString()+" "+expr.get(2).getCommandString());
//		AbstractParser.printListNodes(expr); 
		List<StringNode> commands = myParser.parse(myCommands);
//		AbstractParser.printListNodes(commands); 
		System.out.println("Post expression: "+expr.get(0).getCommandString()+" "+expr.get(1).getCommandString()+" "+expr.get(2).getCommandString());
		System.out.println();
		System.out.println("commands: "+expr.get(0).getCommandString());
		String variable = expr.get(0).getCommandString();
		System.out.println("variable: " + variable);
		expr.remove(0);
//		AbstractParser.printListNodes(expr);
		System.out.println("expr: " + expr.get(0).getCommandString());
		double answer = 0;
		try{
			answer = myForFactory.runForLoopCommands(expr, commands, variable, myTurtles);
		} catch (Exception e){
			e.printStackTrace();
		}
		backToLastVariableSpace(lastVCopy);
		return answer;
	}
}
