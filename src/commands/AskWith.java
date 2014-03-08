package commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parser.AbstractParser;
import parser.tree.StringNode;
import TurtleStuff.Turtle;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import exception.UndefinedVariableException;

public class AskWith extends TurtleControlCommand {
	
	public AskWith(){ super(); }

	protected List<Integer> processExprNodes(List<StringNode> expr) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException{
		List<Integer> nextActiveTurtles = new ArrayList<Integer>();
		for(StringNode node: expr){
			String current = node.getCommandString();
			if(AbstractParser.isParameter(current)){
				nextActiveTurtles.add(Integer.parseInt(current));
			}
			else{
				for(Turtle turtle: myTurtleManager.getAllTurtles()){
					List<Turtle> testTurtles = new ArrayList<Turtle>();
					testTurtles.add(turtle);
					List<StringNode> testCmds = new ArrayList<StringNode>();
					testCmds.add(node);
					if(myFactory.runCommands(testCmds, testTurtles) == 1){
						nextActiveTurtles.add(turtle.getID());
					}
				}
			}
		}
		return nextActiveTurtles;
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException, UndefinedVariableException {
		System.out.println();
		System.out.println("AskWith executed called: ");
		myFactory.setVariableManager(myLocalVariableManager);
		myFactory.setUserCommandManager(myUserCommandManager);
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		System.out.println("myExpression: "+myExpression);
		System.out.println("myCommands: "+myCommands);
		List<StringNode> expr = myParser.parse(myExpression);
		List<Turtle> turtles = myTurtleManager.getFilteredTurtles();
		List<Integer> lastIDs = getIDsOfTurtles(turtles);
		if(expr == null) System.out.println("expr wrong !!");
		myTurtleManager.setFilterList(processExprNodes(expr));
		System.out.println("parse myCommands");
		System.out.println();
		List<StringNode> commands = myParser.parse(myCommands);
		System.out.println("factory runCommands");
		System.out.println();
		double answer = myFactory.runCommands(commands, myTurtleManager.getFilteredTurtles());
		System.out.println("pass factory: "+answer);
		myTurtleManager.setFilterList(lastIDs);
		System.out.println("pass setFilter");
		backToLastVariableSpace(lastVCopy);
		System.out.println("pass backVariable");
		return answer;
	}
}
