package backEnd;

import java.util.List;

import TurtleStuff.Turtle;
import parser.AbstractParser;
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
		String answer = "";
		for(StringNode root: roots){
			updateCountInfo(root, repCount);
			answer += processStringNode(root, turtle);	
		}
		return AbstractParser.convertToDouble(answer); // return the value of the last command tree
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
