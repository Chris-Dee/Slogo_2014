package commands;

import java.util.ArrayList;
import java.util.List;

import parser.AbstractParser;
import parser.tree.StringNode;
import TurtleStuff.Turtle;
import exception.IllegalCommandException;
import exception.IllegalParameterException;

public class AskWith extends TurtleControlCommand {
	
	public AskWith(){ super(); }

	protected List<Integer> processExprNodes(List<StringNode> expr) throws IllegalCommandException, IllegalParameterException{
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
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		List<Turtle> turtles = myTurtleManager.getFilteredTurtles();
		List<Integer> lastIDs = getIDsOfTurtles(turtles);
		myTurtleManager.setFilterList(processExprNodes(expr));
		List<StringNode> commands = myParser.parse(myCommands);
		double answer = myFactory.runCommands(commands, myTurtleManager.getFilteredTurtles());
		myTurtleManager.setFilterList(lastIDs);
		return answer;
	}
}
