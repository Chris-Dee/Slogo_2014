package factories;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import backEnd.Managers.UserCommandManager;
import backEnd.Managers.VariableManager;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleManager;
import parser.AbstractParser;
import parser.tree.ControlNode;
import parser.tree.StringNode;
import parser.tree.UserDefinedCommandNode;
import commands.AbstractCommand;
import commands.UserCommand;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import exception.UndefinedVariableException;

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
    protected List<String> myCreateUserCommands;
    protected VariableManager myVariableManager;
    protected UserCommandManager myUserCommandManager;
    protected TurtleManager myTurtleManager;
	
	public CommandFactory(){
		myCommands = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_COMMANDPATH);
		myParameters = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_NUMPARAMETERS);
		myCommandTypes = ResourceBundle.getBundle(DEFAULT_BACKEND_PACKAGE + DEFAULT_COMMANDTYPES);
		
		myControlCommands = new ArrayList<String>();
		myModifyVariableCommands = new ArrayList<String>();
		myVariableManager = new VariableManager();
		myUserCommandManager = new UserCommandManager();
		myTurtleManager = new TurtleManager();
		myCreateUserCommands = new ArrayList<String>();
		
		initCommandTypes(myControlCommands, "Control");
		initCommandTypes(myModifyVariableCommands, "ModifyVariable");
		initCommandTypes(myCreateUserCommands, "CreateUserCommand");
	}
	
	public void setUserCommandManager(UserCommandManager manager){
		myUserCommandManager = manager;
	}
	
	public void setTurtleManager(TurtleManager manager){
		myTurtleManager = manager;
	}

	protected void initCommandTypes(List<String> myCmdList, String type) {
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
	public double runCommands(List<StringNode> roots, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException{
		String answer = "";
		for(StringNode root: roots){
			answer = processStringNode(root, turtles);	
		}
		return AbstractParser.convertToDouble(answer);
	}
	
	/*
	 * This method should not be called from the outside.
	 * Used to build a command or a parameter for the current StringNode
	 */
	protected String processStringNode(StringNode current, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException{
		if(current == null){ return null; } // make sure the current node is not null
		if(current.getChildren().isEmpty()){ // base case: leaf StringNode
			if (AbstractParser.isParameter(current.getCommandString()) // a number in the leaf
					|| myVariableManager.isVariable(current.getCommandString())){ // or a variable in the leaf
				return current.getCommandString();	
			}
			else if (hasNoParameter(current)){ // a non-parameter command in the leaf
				if (isUserCommand(current)){ 
					UserDefinedCommandNode cur = (UserDefinedCommandNode) current;
					return makeUserCommand(cur, turtles); }
				if(ifControlCommand(current)){
					ControlNode cur = (ControlNode) current;
					if(isModifyUserCommand(current)){ return makeModifyUserCommand(cur, turtles); }
					return makeControlCommand(cur, turtles);
				}
				return makeCommand(current.getCommandString(), DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtles);
			}
		}
		
		if(hasOneParameter(current)){
			String answer = processStringNode(current.getChildren().get(0), turtles);
			return makeCommand(current.getCommandString(), answer, DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtles);
		}
		else if(hasTwoParameters(current)){
			String leftAnswer = processStringNode(current.getChildren().get(0), turtles);
			String rightAnswer = processStringNode(current.getChildren().get(1), turtles);
			return makeCommand(current.getCommandString(), leftAnswer, rightAnswer, DEFAULT_MAGNITUDE, DEFAULT_MAGNITUDE, turtles);
		}
		else if(hasFourParameters(current)){
			String firstAnswer = processStringNode(current.getChildren().get(0), turtles);
			String secondAnswer = processStringNode(current.getChildren().get(1), turtles);
			String thirdAnswer = processStringNode(current.getChildren().get(2), turtles);
			String fourthAnswer = processStringNode(current.getChildren().get(3), turtles);
			return makeCommand(current.getCommandString(), firstAnswer, secondAnswer, thirdAnswer, fourthAnswer, turtles);
			
		}
		return "0"; // should not reach here
	}
	
	/*
	 * This method should not be called from the outside.
	 * Used to check if a StringNode is a ControlNode
	 */
	protected boolean ifControlCommand(StringNode current){
		return myControlCommands.contains(current.getCommandString());
	}
	
	protected boolean isModifyUserCommand(StringNode current){
		return myCreateUserCommands.contains(current.getCommandString());
	}
	
	protected boolean isUserCommand(StringNode current){
		return myUserCommandManager.hasUserCommand(current.getCommandString());
	}
	
	protected String makeUserCommand(ControlNode node, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException {
		try { 
			UserCommand command = myUserCommandManager.getUserCommand(node.getCommandString());
			Method[] methods = command.getClass().getMethods();
			firstMethodsExecuted(turtles, command, methods);
			for (Method m: methods){
				if(m.getName().equals("setValueToParameter")){ m.invoke(command, node.getExpression()); }
		    }
			return executeCommand(command, methods);
		} catch (IllegalAccessException e) {
			throw new IllegalCommandException();
		} catch (IllegalArgumentException e) {
			throw new IllegalParameterException();
		} catch (InvocationTargetException e) {
			throw new IllegalCommandException();
		} 
	}
	
	/*
	 * This method should not be called from the outside.
	 * Used to make a control command out of the current ControlNode in the command tree structure
	 */
	protected String makeModifyUserCommand(ControlNode node, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException {
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(node.getCommandString()));
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			firstMethodsExecuted(turtles, command, methods);
			for (Method m: methods){
				if(m.getName().equals("setCommandName")){
					m.invoke(command, node.getExpression());
				}
				if(m.getName().equals("setExpression")){
					m.invoke(command, node.getCommands());
				}
				if(m.getName().equals("setCommands")){
					m.invoke(command, node.getElseCommands());
				}
				if(m.getName().equals("setTurtleManager")){
					m.invoke(command, myTurtleManager);
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
	
	/*
	 * This method should not be called from the outside.
	 * Used to make a control command out of the current ControlNode in the command tree structure
	 */
	protected String makeControlCommand(ControlNode node, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException {
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(node.getCommandString()));
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			firstMethodsExecuted(turtles, command, methods);
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
				if(m.getName().equals("setTurtleManager")){
					m.invoke(command, myTurtleManager);
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
			e.printStackTrace();
			throw new IllegalCommandException();
		}
	}

	protected void firstMethodsExecuted(List<Turtle> turtles, AbstractCommand command,
			Method[] methods) throws IllegalAccessException, InvocationTargetException {
		for(Method m: methods){
			if(m.getName().equals("setTurtles")){
				m.invoke(command, turtles);
			}
			if(m.getName().equals("setVariableManager")){
				m.invoke(command, myVariableManager);
			}
			if(m.getName().equals("setUserCommandManager")){
				m.invoke(command, myUserCommandManager);
			}
		}
	}

	protected String executeCommand(AbstractCommand command, Method[] methods) 
			throws IllegalAccessException, InvocationTargetException, IllegalCommandException, IllegalParameterException, UndefinedVariableException {
		String answer = "";
		for (Method cur: methods){
			if (cur.getName().equals("execute")){
				answer += (Double) cur.invoke(command);
		    }	
		}
		return answer;
	}
	
	protected String makeCommand(String cmd, String magnitude1, String magnitude2, String magnitude3, String magnitude4, List<Turtle> turtles) throws IllegalCommandException, IllegalParameterException, UndefinedVariableException{
		try { 
			Class<?> commandClass = Class.forName(myCommands.getString(cmd));
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			firstMethodsExecuted(turtles, command, methods);
			for (Method m: methods){
				if(m.getName().equals("setMagnitude")){
					m.invoke(command, magnitude1);
				}
				if(m.getName().equals("setDoubleMagnitude")){
					m.invoke(command, magnitude1, magnitude2);
				}
				if(m.getName().equals("setQuadrupleMagnitude")){
					m.invoke(command, magnitude1, magnitude2, magnitude3, magnitude4);
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
		return isUserCommand(current) || myParameters.getString(current.getCommandString()).equals("0");
	}
	
	protected boolean hasOneParameter(StringNode current){
		return myParameters.getString(current.getCommandString()).equals("1");
	}
	
	protected boolean hasTwoParameters(StringNode current){
		return myParameters.getString(current.getCommandString()).equals("2");
	}
	
	protected boolean hasFourParameters(StringNode current){
		return myParameters.getString(current.getCommandString()).equals("4");
	}
	
	public void setVariableManager(VariableManager variableManager){
		myVariableManager = variableManager;
	}
}
