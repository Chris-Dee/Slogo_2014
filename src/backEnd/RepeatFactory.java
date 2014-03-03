package backEnd;

import parser.tree.StringNode;
import frontEnd.Turtle;

public class RepeatFactory extends CommandFactory{
	
	public RepeatFactory(){
		super();
	}
	
	/*
	 * Should be called by Repeat Command to process its own list of commands
	 */
	public double runCommands(StringNode root, double repCount, Turtle turtle){
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
		return;
	}

}
