package commands;

import java.util.ArrayList;
import java.util.List;

import parser.AbstractParser;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import factories.CommandFactory;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleManager;

public abstract class TurtleControlCommand extends ControlCommand{
	
	protected CommandFactory myFactory;
	
	public TurtleControlCommand(){
		super();
		myFactory = new CommandFactory();
	}
	
	protected TurtleManager myTurtleManager;
	
	public void setTurtleManager(TurtleManager manager){
		myTurtleManager = manager;
	}
	
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
	
	protected List<Integer> getIDsOfTurtles(List<Turtle> turtles){
		List<Integer> answer = new ArrayList<Integer>();
		for(Turtle turtle: turtles){
			answer.add(turtle.getID());
		}
		return answer;
	}

}
