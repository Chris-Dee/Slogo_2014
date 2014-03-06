package backEnd;

import java.util.List;

import TurtleStuff.Turtle;
import parser.AbstractParser;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;

public class LoopFactory extends CommandFactory{
	
	public LoopFactory(){
		super();
	}
	
	/*
	 * Should be called by Repeat Command to process its own list of commands
	 */
	public double runCommands(List<StringNode> roots, String variable, double loopTimes, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException{
		if(loopTimes <= 0) return 0;
		String answer = "";
		for(int i = 0; i < loopTimes; i ++){
			for(StringNode root: roots){
				updateCountInfo(root, variable, loopTimes);
				answer = processStringNode(root, turtles);	
			}	
		}
		return AbstractParser.convertToDouble(answer); // return the value of the last command tree
	}
	
	protected void updateCountInfo(StringNode node, String v, double times){
		if(node == null){ return; }
		if(node.getCommandString().equals(v)){
			node.setCommandString(""+times);
		}
		if(node.getChildren() != null){
			for(StringNode child: node.getChildren()){
				updateCountInfo(child, v, times);	
			}			
		}
	}
}
