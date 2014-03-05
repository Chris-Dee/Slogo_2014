package backEnd;

import java.util.List;

import TurtleStuff.Turtle;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;

public class RepeatFactory extends CommandFactory{
	
	public RepeatFactory(){
		super();
	}
	
	/*
	 * Should be called by Repeat Command to process its own list of commands
	 */
	public double runCommands(List<StringNode> roots, double repCount, Turtle turtle) throws IllegalCommandException, IllegalParameterException{
		double answer = 0;
		for(StringNode root: roots){
			updateCountInfo(root, repCount);
			answer = processStringNode(root, turtle);	
		}
		return answer; // return the value of the last command tree
	}
	
	protected void updateCountInfo(StringNode node, double repCount){
		if(node == null){ return; }
		if(node.getCommandString().equals(":repcount")){ // need revision
			node.setCommandString(""+repCount);
		}
		if(node.getChildren() != null){
			for(StringNode child: node.getChildren()){
				updateCountInfo(child, repCount);	
			}			
		}
	}
}
