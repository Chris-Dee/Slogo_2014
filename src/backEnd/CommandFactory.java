package backEnd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import parser.AbstractParser;
import parser.tree.ControlNode;
import parser.tree.StringNode;
import commands.AbstractCommand;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import frontEnd.SlogoView;
import frontEnd.Turtle;

public class CommandFactory {
    public static final String DEFAULT_BACKEND_PACKAGE = "backEnd/";
    public static final String DEFAULT_COMMANDPATH = "CommandPath";
    public static final String DEFAULT_NUMPARAMETERS = "CommandParameters";
    public static final String DEFAULT_CONTROLS = "ControlCommands";
    private static final double DEFAULT_MAGNITUDE = 0;
    
    protected ResourceBundle myCommands;
    protected ResourceBundle myParameters;
    protected ResourceBundle myPossibleControls;
    protected List<String> myControlCommands;
    protected VariableManager myVariableManager;
	
	public CommandFactory(){
		myCommands = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_COMMANDPATH);
		myParameters = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_NUMPARAMETERS);
		myPossibleControls = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_CONTROLS);
		initControlCommands();
		myVariableManager = new VariableManager();
	}



	protected void initControlCommands() {
		myControlCommands = new ArrayList<String>();
		String[] controlList = myPossibleControls.getString("Control").split(",");
		for(String s:controlList){
			myControlCommands.add(s);
		}
	}
	
	
	
	/*
	 * Called by TextParser to process a tree of Strings of commands
	 * Passed in the root of the tree
	 * return the returned value of the root command
	 * All passed in command tree has been checked legality and is thus legal
	 */
	public double runCommands(StringNode root, Turtle turtle) throws IllegalCommandException, IllegalParameterException{
		//myParser.printTree(root);
		return processStringNode(root, turtle);
	}
	
	
	
	/*
	 * This method should not be called from the outside.
	 * Used to build a command or a parameter for the current StringNode
	 */
	protected double processStringNode(StringNode current, Turtle turtle) throws IllegalCommandException, IllegalParameterException{
		if(current == null){ return 0; } // make sure the current node is not null
		if(current.getChildren().isEmpty()){ // base case: leaf StringNode
			if (AbstractParser.isParameter(current.getCommandString())){ // a number in the leaf
				//System.out.println("reach a number in the leaf in CommandFactory: "+myParser.convertToDouble(current.getCommandString()));
				return AbstractParser.convertToDouble(current.getCommandString());	
			}
			else if (hasNoParameter(current)){ // a non-parameter command in the leaf
				if(ifControlCommand(current)){
					//System.out.println(current.getCommandString() + " is a control command");
					ControlNode cur = (ControlNode) current;
					return makeControlCommand(cur, turtle);
				}
				return makeCommand(current.getCommandString(), DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtle);
			}
		}
		
		if(AbstractParser.isParameter(current.getCommandString())){ // the current node is a number but it has a child
			processStringNode(current.getChildren().get(0), turtle);
			return AbstractParser.convertToDouble(current.getCommandString());	
		}
		else if(hasNoParameter(current)){
			processStringNode(current.getChildren().get(0), turtle);
			if(ifControlCommand(current)){
				ControlNode cur = (ControlNode) current;
				return makeControlCommand(cur, turtle);
			}
			return makeCommand(current.getCommandString(), DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtle);
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
	 * Used to check if a StringNode is a ControlNode
	 */
	protected boolean ifControlCommand(StringNode current){
		return myControlCommands.contains(current.getCommandString());
	}
	
	/*
	 * This method should not be called from the outside.
	 * Used to make a control command out of the current ControlNode in the command tree structure
	 */
	protected double makeControlCommand(ControlNode node, Turtle turtle) throws IllegalCommandException, IllegalParameterException {
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(node.getCommandString()));
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			for (Method m: methods){
				if(m.getName().equals("setTurtle")){
					m.invoke(command, turtle);
				}
				if(m.getName().equals("setExpression")){
					m.invoke(command, node.getExpression());
				}
				if(m.getName().equals("setCommands")){
					m.invoke(command, node.getCommands());
				}
				if(m.getName().equals("setElseCommands")){
					m.invoke(command, node.getElseCommands());
				}
		    }
			return executeTurtle(command, methods);
		} catch (ClassNotFoundException e) {
			throw new IllegalCommandException();
		} catch (InstantiationException e) {
			throw new IllegalCommandException();
		} catch (IllegalAccessException e) {
			throw new IllegalCommandException();
		} catch (IllegalArgumentException e) {
			throw new IllegalParameterException();
		} catch (InvocationTargetException e) {
			throw new IllegalCommandException();
		}
	}



	protected double executeTurtle(AbstractCommand command, Method[] methods) 
			throws IllegalAccessException, InvocationTargetException {
		double answer = 0;
		for (Method cur: methods){
			if (cur.getName().equals("execute")){
				answer = (Double) cur.invoke(command);
				SlogoView.updateInfo();
		    }	
		}
		return answer;
	}
	
	
	/*
	 * This method should not be called from the outside.
	 * If the command has no magnitude variable, then pass in DEFAULT_MAGNITUDE for magnitude1 and magnitude2
	 * If the command has only 1 magnitude variable, then pass in DEFAULT_MAGNITUDE for magnitude2
	 */
	protected double makeCommand(String cmd, double magnitude1, double magnitude2, Turtle turtle) throws IllegalCommandException, IllegalParameterException{
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(cmd));
			//System.out.println("current command: "+myCommands.getString(cmd) + " " + magnitude1 + magnitude2);
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
			return executeTurtle(command, methods);
		} catch (ClassNotFoundException e) {
			throw new IllegalCommandException();
		} catch (InstantiationException e) {
			throw new IllegalCommandException();
		} catch (IllegalAccessException e) {
			throw new IllegalCommandException();
		} catch (IllegalArgumentException e) {
			throw new IllegalParameterException();
		} catch (InvocationTargetException e) {
			throw new IllegalCommandException();
		}
	}
	
	protected boolean hasNoParameter(StringNode current){
		return myParameters.getString(current.getCommandString()).equals("0");
	}
	
	protected boolean hasOneParameter(StringNode current){
		return myParameters.getString(current.getCommandString()).equals("1");
	}
	
	protected boolean hasTwoParameters(StringNode current){
		return myParameters.getString(current.getCommandString()).equals("2");
	}
}
