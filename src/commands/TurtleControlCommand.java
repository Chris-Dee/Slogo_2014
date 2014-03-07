package commands;

import java.util.ArrayList;
import java.util.List;

import parser.tree.StringNode;
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
	
	protected List<Integer> getIDsOfExpression(List<StringNode> expr){
		List<Integer> ids = new ArrayList<Integer>();
		for(StringNode node: expr){
			ids.add(Integer.parseInt(node.getCommandString()));
		}
		return ids;
	}
	
	protected List<Integer> getIDsOfTurtles(List<Turtle> turtles){
		List<Integer> answer = new ArrayList<Integer>();
		for(Turtle turtle: turtles){
			answer.add(turtle.getID());
		}
		return answer;
	}

}
