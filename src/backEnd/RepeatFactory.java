package backEnd;

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
	public double runCommands(StringNode root, double repCount, Turtle turtle) throws IllegalCommandException, IllegalParameterException{
		updateCountInfo(root, repCount);
		return processStringNode(root, turtle);
	}
	
	protected void updateCountInfo(StringNode node, double repCount){
		if(node == null){ return; }
		if(node.getCommandString().equals(":repcount")){
			node.setCommandString(""+repCount);
		}
		if(node.getChildren() != null){
			for(StringNode child: node.getChildren()){
				updateCountInfo(child, repCount);	
			}			
		}
	}
}
