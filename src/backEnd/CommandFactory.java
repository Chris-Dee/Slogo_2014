package backEnd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import TurtleStuff.Turtle;
import parser.AbstractParser;
import parser.tree.ControlNode;
import parser.tree.StringNode;
import commands.AbstractCommand;
import exception.IllegalCommandException;
import exception.IllegalParameterException;

public class CommandFactory {
    public static final String DEFAULT_BACKEND_PACKAGE = "backEnd/";
    public static final String DEFAULT_COMMANDPATH = "CommandPath";
    public static final String DEFAULT_NUMPARAMETERS = "CommandParameters";
    public static final String DEFAULT_COMMANDTYPES = "CommandTypes";
    private static final String DEFAULT_MAGNITUDE = "0";
    
    protected ResourceBundle myCommands;
    protected ResourceBundle myParameters;
    protected ResourceBundle myCommandTypes;
    protected List<String> myControlCommands;
    protected List<String> myModifyVariableCommands;
    protected VariableManager myVariableManager;
	
	public CommandFactory(){
		myCommands = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_COMMANDPATH);
		myParameters = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_NUMPARAMETERS);
		myCommandTypes = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_COMMANDTYPES);
		initCommandTypes(myControlCommands, "Control");
		initCommandTypes(myModifyVariableCommands, "ModifyVariable");
		myVariableManager = new VariableManager();
	}

	protected void initCommandTypes(List<String> myCmdList, String type) {
		myCmdList = new ArrayList<String>();
		String[] controlList = myCommandTypes.getString(type).split(",");
		for(String s:controlList){
			myCmdList.add(s);
		}
	}
	
	/*
	 * Called by TextParser to process a tree of Strings of commands
	 * Passed in the root of the tree
	 * return the returned value of the root command
	 * All passed in command tree has been checked legality and is thus legal
	 */
	public double runCommands(List<StringNode> roots, Turtle turtle) throws IllegalCommandException, IllegalParameterException{
		String answer = "";
		for(StringNode root: roots){
			//myParser.printTree(root);
			answer += processStringNode(root, turtle);	
		}
		return AbstractParser.convertToDouble(answer); // return the value of the last command tree
	}
	
//	need revision
//	protected String processVariableNode(StringNode current){
//		double value = myVariableManager.getValueOfVariable(current.getCommandString());
//		String answer = Double.toString(value);
//		current.setCommandString(answer);
//		return answer;
//	}
	
	/*
	 * This method should not be called from the outside.
	 * Used to build a command or a parameter for the current StringNode
	 */
	protected String processStringNode(StringNode current, Turtle turtle) throws IllegalCommandException, IllegalParameterException{
		if(current == null){ return null; } // make sure the current node is not null
		if(current.getChildren().isEmpty()){ // base case: leaf StringNode
			if (AbstractParser.isParameter(current.getCommandString())){ // a number in the leaf
				//System.out.println("reach a number in the leaf in CommandFactory: "+myParser.convertToDouble(current.getCommandString()));
				return current.getCommandString();	
			}
			else if(myVariableManager.isVariable(current.getCommandString())){ // a variable in the leaf
				if(!ifParentModifyVariable(current)){
					return current.getCommandString();	
				}
			}
			else if (hasNoParameter(current)){ // a non-parameter command in the leaf
				if(ifControlCommand(current)){
					ControlNode cur = (ControlNode) current;
					return makeControlCommand(cur, turtle);
				}
				return makeCommand(current.getCommandString(), DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtle);
			}
		}
		
//		if(hasNoParameter(current)){
//			processStringNode(current.getChildren().get(0), turtle);
//			if(ifControlCommand(current)){
//				ControlNode cur = (ControlNode) current;
//				return makeControlCommand(cur, turtle);
//			}
//			return makeCommand(current.getCommandString(), DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtle);
//		}
		if(hasOneParameter(current)){
			String answer = processStringNode(current.getChildren().get(0), turtle);
			return makeCommand(current.getCommandString(), answer, DEFAULT_MAGNITUDE, turtle);
		}
		else if(hasTwoParameters(current)){
			String leftAnswer = processStringNode(current.getChildren().get(0), turtle);
			String rightAnswer = processStringNode(current.getChildren().get(1), turtle);
			return makeCommand(current.getCommandString(), leftAnswer, rightAnswer, turtle);
		}
		return "0"; // should not reach here
	}
	
	/*
	 * Return true if current's direct parent modifies a variable (e.g. MAKE, SET) 
	 */
	protected boolean ifParentModifyVariable(StringNode current){
		if(current == null || current.getParent() == null) return false;
		if(myModifyVariableCommands.contains(current.getParent().getCommandString())) return true;
		return false;
	}
	
	/*
	 * This method should not be called from the outside.
	 * Used to check if a StringNode is a ControlNode
	 */
	protected boolean ifControlCommand(StringNode current){
		return myControlCommands.contains(current.getCommandString());
	}
	
//	/*
//	 * This method should not be called from the outside.
//	 * Used to make a command that modifies a variable (e.g make, set)
//	 */
//	protected double makeModifyVariableCommand(StringNode node, double magnitude2) throws IllegalCommandException, IllegalParameterException {
//		try { 
//			Class<?> commandClass = Class.forName(myCommands.getString(node.getCommandString()));
//			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
//			Method[] methods = commandClass.getMethods();
//			for (Method m: methods){
//				if(m.getName().equals("setVariable")){
//					m.invoke(command, node.getChildren().get(0));
//				}
//				if(m.getName().equals("setVariableManager")){
//					m.invoke(command, myVariableManager);
//				}
//				if(m.getName().equals("setExpression")){
//					m.invoke(command, magnitude2);
//				}
//		    }
//			return executeCommand(command, methods);
//		} catch (ClassNotFoundException e) {
//			throw new IllegalCommandException();
//		} catch (InstantiationException e) {
//			throw new IllegalCommandException();
//		} catch (IllegalAccessException e) {
//			throw new IllegalCommandException();
//		} catch (IllegalArgumentException e) {
//			throw new IllegalParameterException();
//		} catch (InvocationTargetException e) {
//			throw new IllegalCommandException();
//		}
//	}
	
	/*
	 * This method should not be called from the outside.
	 * Used to make a control command out of the current ControlNode in the command tree structure
	 */
	protected String makeControlCommand(ControlNode node, Turtle turtle) throws IllegalCommandException, IllegalParameterException {
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(node.getCommandString()));
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			firstMethodsExecuted(turtle, command, methods);
			for (Method m: methods){
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
			return executeCommand(command, methods);
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

	protected void firstMethodsExecuted(Turtle turtle, AbstractCommand command,
			Method[] methods) throws IllegalAccessException, InvocationTargetException {
		for(Method m: methods){
			if(m.getName().equals("setTurtle")){
				m.invoke(command, turtle);
			}
			if(m.getName().equals("receiveVariableManager")){
				m.invoke(command, myVariableManager);
			}
		}
	}



	protected String executeCommand(AbstractCommand command, Method[] methods) 
			throws IllegalAccessException, InvocationTargetException {
		String answer = "";
		for (Method cur: methods){
			if (cur.getName().equals("execute")){
				answer += (Double) cur.invoke(command);
		    }	
		}
		return answer;
	}
	
	
	/*
	 * This method should not be called from the outside.
	 * If the command has no magnitude variable, then pass in DEFAULT_MAGNITUDE for magnitude1 and magnitude2
	 * If the command has only 1 magnitude variable, then pass in DEFAULT_MAGNITUDE for magnitude2
	 */
	protected String makeCommand(String cmd, String magnitude1, String magnitude2, Turtle turtle) throws IllegalCommandException, IllegalParameterException{
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(cmd));
			//System.out.println("current command: "+myCommands.getString(cmd) + " " + magnitude1 + magnitude2);
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			firstMethodsExecuted(turtle, command, methods);
			for (Method m: methods){
				if(m.getName().equals("setMagnitude")){
					m.invoke(command, magnitude1);
				}
				if(m.getName().equals("setDoubleMagnitude")){
					m.invoke(command, magnitude1, magnitude2);
				}
		    }
			return executeCommand(command, methods);
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
	
	/*
	 * Called only within the control commands
	 */
	public void setVariableManager(VariableManager variableManager){
		myVariableManager = variableManager;
	}
}
