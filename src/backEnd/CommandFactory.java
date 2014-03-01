package backEnd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;
import commands.AbstractCommand;
import frontEnd.SlogoView;
import frontEnd.Turtle;

public class CommandFactory {
    public static final String DEFAULT_COMMANDPATH_PACKAGE = "backEnd/";
    public static final String DEFAULT_COMMANDPARAMETER_PACKAGE = "backEnd/";
    public static final String DEFAULT_COMMANDPATH = "CommandPath";
    public static final String DEFAULT_NUMPARAMETERS = "CommandParameters";
    public static final String COMMAND_INVALID_MESSAGE = "Please enter a valid command!";
    public static final double DEFAULT_MAGNITUDE = 0;
    
    protected ResourceBundle myCommands;
    protected ResourceBundle myParameters;
    protected AbstractParser myParser;
	
	public CommandFactory(){
		myCommands = ResourceBundle.getBundle(DEFAULT_COMMANDPATH_PACKAGE + DEFAULT_COMMANDPATH);
		myParameters = ResourceBundle.getBundle(DEFAULT_COMMANDPARAMETER_PACKAGE + DEFAULT_NUMPARAMETERS);
		myParser = new TextParser(); // intended to use some convenient methods in AbstarctParser class
		// myParser is different from the real TextParser object stored in the SlogoModel class
	}
	
	
	
	/*
	 * Called by TextParser to process a tree of Strings of commands
	 * Passed in the root of the tree
	 * return the returned value of the root command
	 * All passed in command tree has been checked legality and is thus legal
	 */
	public double runCommands(StringNode root, Turtle turtle){
		return processStringNode(root, turtle);
	}
	
	
	
	/*
	 * This method should not be called from the outside.
	 * Used to build a command or a parameter for the current StringNode
	 */
	protected double processStringNode(StringNode current, Turtle turtle){
		if(current == null){ return 0; } // make sure the current node is not null
		if(current.getChildren().isEmpty()){ // base case: leaf StringNode
			if (myParser.isParameter(current.getCommandString())){ // a number in the leaf
				System.out.println("reach a number in the leaf in CommandFactory: "+myParser.convertToDouble(current.getCommandString()));
				return myParser.convertToDouble(current.getCommandString());	
			}
			else if (hasNoParameter(current)){ // a non-parameter command in the leaf
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
//			System.out.println(current.getCommandString() + " has only one parameter");
			double answer = processStringNode(current.getChildren().get(0), turtle);
			return makeCommand(current.getCommandString(), answer, DEFAULT_MAGNITUDE, turtle);
		}
		else if(hasTwoParameters(current)){
			double leftAnswer = processStringNode(current.getChildren().get(0), turtle);
			double rightAnswer = processStringNode(current.getChildren().get(1), turtle);
			return makeCommand(current.getCommandString(), leftAnswer, rightAnswer, turtle);
		}
		return 0; // should not reach here
	}

	
	
	/*
	 * This method should not be called from the outside.
	 * If the command has no magnitude variable, then pass in DEFAULT_MAGNITUDE for magnitude1 and magnitude2
	 * If the command has only 1 magnitude variable, then pass in DEFAULT_MAGNITUDE for magnitude2
	 */
	protected double makeCommand(String cmd, double magnitude1, double magnitude2, Turtle turtle){
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(cmd));
			System.out.println("current command: "+myCommands.getString(cmd) + " " + magnitude1);
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
					double answer = (Double) cur.invoke(command);
					SlogoView.updateInfo();
					return answer;
			    }	
			}
		} catch (ClassNotFoundException e) {
			return -1;
			//e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			return -1;
			//e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return 0; // other errors
	}
	
	protected boolean hasNoParameter(StringNode current){
		if(myParameters.getString(current.getCommandString()).equals("0")){ return true; }
		return false;
	}
	
	protected boolean hasOneParameter(StringNode current){
		if(myParameters.getString(current.getCommandString()).equals("1")){ return true; }
		return false;
	}
	
	protected boolean hasTwoParameters(StringNode current){
		if(myParameters.getString(current.getCommandString()).equals("2")){ return true; }
		return false;
	}
	
}
