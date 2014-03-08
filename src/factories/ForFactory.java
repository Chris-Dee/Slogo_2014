package factories;

import java.util.ArrayList;
import java.util.List;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import exception.UndefinedVariableException;
import parser.AbstractParser;
import parser.tree.StringNode;
import TurtleStuff.Turtle;

public class ForFactory extends LoopFactory{
	
	protected double start;
	protected double end;
	protected double increment;
	
	public ForFactory(){
		super();
		start = 0;
		end = 0;
		increment = 0;
	}
	
	protected void setForParameters(List<StringNode> roots, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException{
		if(roots.size() != 3) throw new IllegalParameterException(); 
		start = processForRoots(roots.get(0), turtles);
		end = processForRoots(roots.get(1), turtles);
		increment = processForRoots(roots.get(2), turtles);
		System.out.println("setForParameters start: "+start+" end: "+end+" increment: "+increment);
		System.out.println();
	}
	
	public double runForLoopCommands(List<StringNode> expr, List<StringNode> cmds, String variable, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException{
		System.out.println("runForLoopCommands called: "+expr.size() +" "+cmds.size() +" "+variable);
		setForParameters(expr, turtles);
		String answer = "";
		for(double i = start; i <= end; i = i + increment){
			for(StringNode root: cmds){
				System.out.println("setValue: " + variable + " " + i);
				myVariableManager.setValueToVariable(variable, i);
				answer = processStringNode(root, turtles);
				myVariableManager.removeVariable(variable);
				System.out.println("runForLoopCommands: "+answer);
			}	
		}
		return AbstractParser.convertToDouble(answer); // return the value of the last command tree
	}
	
	protected double processForRoots(StringNode root, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException{
		List<StringNode> list = new ArrayList<StringNode>();
		list.add(root);
		return runCommands(list, turtles);
	}
}
