package factories;

import java.util.List;

import TurtleStuff.Turtle;
import parser.AbstractParser;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import exception.UndefinedVariableException;

public class LoopFactory extends CommandFactory{
	
	public LoopFactory(){
		super();
	}
	
	/*
	 * Should be called by Repeat Command to process its own list of commands
	 */
	public double runAutoLoopCommands(List<StringNode> roots, String variable, double loopTimes, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException{
		if(loopTimes <= 0) return 0;
		String answer = "";
		for(int i = 0; i < loopTimes; i ++){
			for(StringNode root: roots){
				myVariableManager.setValueToVariable(variable, loopTimes);
				answer = processStringNode(root, turtles);	
				myVariableManager.removeVariable(variable);
			}	
		}
		return AbstractParser.convertToDouble(answer); // return the value of the last command tree
	}
}
