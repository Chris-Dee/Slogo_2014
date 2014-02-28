package backEnd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import parser.AbstractParser;
import parser.tree.StringNode;
import commands.AbstractCommand;
import frontEnd.Turtle;

public class CommandFactory {
    public static final String DEFAULT_COMMAND_PACKAGE = "commands/";
    public static final String DEFAULT_PARAMETER_PACKAGE = "";
    public static final String DEFAULT_COMMANDPATH = "CommandPath";
    public static final String DEFAULT_NUMPARAMETERS = "";
    public static final String COMMAND_INVALID_MESSAGE = "Please enter a valid command!";
    public static final double DEFAULT_MAGNITUDE = 0;
    
    private ResourceBundle myCommands;
    private ResourceBundle myParameters;
    private AbstractParser myParser;
	
	public CommandFactory(AbstractParser parser){
		myCommands = ResourceBundle.getBundle(DEFAULT_COMMAND_PACKAGE + DEFAULT_COMMANDPATH);
		myParameters = ResourceBundle.getBundle(DEFAULT_PARAMETER_PACKAGE + DEFAULT_NUMPARAMETERS);
		myParser = parser;
	}
	
	/*
	 * Called by TextParser to process a tree of Strings of commands
	 * Passed in the root of the tree
	 * return the returned value of the root command
	 */
	public double runCommands(StringNode root, Turtle turtle){
		//makeCommand("FD", 10, DEFAULT_MAGNITUDE, turtle);
		return processStringNode(root, turtle);
	}
	
	protected double processStringNode(StringNode current, Turtle turtle){
		if(current.getChildren().isEmpty()){ // base case: leaf StringNode
			if (myParser.isParameter(current.getCommandString())){ // a number in the leaf
				return myParser.convertToDouble(current.getCommandString());	
			}
			else{ // a non-parameter command in the leaf
				return makeCommand(current.getCommandString(), DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtle);
			}
		}
		
		if(myParser.isParameter(current.getCommandString())){ // the current node is a number but it has a child
			processStringNode(current.getChildren().get(0), turtle);
			return myParser.convertToDouble(current.getCommandString());	
		}
		else if(hasNoParameter(current)){
			double answer = processStringNode(current.getChildren().get(0), turtle);
			return makeCommand(current.getCommandString(), DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtle) + answer;
		}
		else if(hasOneParameter(current)){
			double answer = processStringNode(current.getChildren().get(0), turtle);
			return makeCommand(current.getCommandString(), answer, DEFAULT_MAGNITUDE, turtle);
		}
		else if(hasTwoParameters(current)){
			double leftAnswer = processStringNode(current.getChildren().get(0), turtle);
			double rightAnswer = processStringNode(current.getChildren().get(1), turtle);
			return makeCommand(current.getCommandString(), leftAnswer, rightAnswer, turtle);
		}
		return 0;
	}
	
	protected boolean hasNoParameter(StringNode current){
		if(myParameters.getString(current.getCommandString()) == "0"){ return true; }
		return false;
	}
	
	protected boolean hasOneParameter(StringNode current){
		if(myParameters.getString(current.getCommandString()) == "1"){ return true; }
		return false;
	}
	
	protected boolean hasTwoParameters(StringNode current){
		if(myParameters.getString(current.getCommandString()) == "2"){ return true; }
		return false;
	}
	
	/*
	 * This method should not be called from the outside.
	 * If the command has no magnitude variable, then pass in DEFAULT_MAGNITUDE for magnitude1 and magnitude2
	 * If the command has only 1 magnitude variable, then pass in DEFAULT_MAGNITUDE for magnitude2
	 */
	protected double makeCommand(String cmd, double magnitude1, double magnitude2, Turtle turtle){
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(cmd));
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			for (Method m: methods){
				if(m.getName().equals("setTurtle")){
					m.invoke(command, turtle);
				}
				if(m.getName().equals("setMagnitude")){
					m.invoke(command, magnitude1);
				}
				if(m.getName().equals("setDoubleMagnitude")){
					m.invoke(command, magnitude1, magnitude2);
				}
		    }
			for (Method cur: methods){
				if (cur.getName().equals("execute")){
					return (Double) cur.invoke(command);
			    }	
			}
		}
		catch (ClassNotFoundException e) {
			//System.out.println(COMMAND_INVALID_MESSAGE);
			e.printStackTrace();
		} 
		catch (InstantiationException e) {
			//System.out.println(COMMAND_INVALID_MESSAGE);
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) {
			//System.out.println(COMMAND_INVALID_MESSAGE);
			e.printStackTrace();
		} 
		catch (IllegalArgumentException e) {
			//System.out.println(COMMAND_INVALID_MESSAGE);
			e.printStackTrace();
		} 
		catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0; 
	}
}
