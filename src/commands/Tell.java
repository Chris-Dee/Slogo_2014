package commands;

import java.util.ArrayList;
import java.util.List;

import TurtleStuff.Turtle;
import parser.AbstractParser;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.CommandFactory;

public class Tell extends TurtleControlCommand{
	
	protected CommandFactory myFactory;
	
	public Tell(){
		super();
		myFactory = new CommandFactory();
		
	}
	
	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		
		
		return 0;
	}
	
	protected List<Integer> processExprNodes(List<StringNode> expr){
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

}
