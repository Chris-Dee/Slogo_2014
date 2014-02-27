package backEnd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import commands.AbstractCommand;
import frontEnd.Turtle;

public class CommandFactory {
    public static final String DEFAULT_COMMAND_PACKAGE = "commands/";
    public static final String COMMAND_INVALID_MESSAGE = "Please enter a valid command!";
    public static final double DEFAULT_MAGNITUDE = 0;
    
    private ResourceBundle myResources;
	
	public CommandFactory(){
        myResources = ResourceBundle.getBundle(DEFAULT_COMMAND_PACKAGE);
	}
	
	/*
	 * Called by TextParser to process a tree of Strings of commands
	 * Passed in the root of the tree
	 */
	public void runCommands(String root){
		makeCommand("FD", 50);
		makeCommand("GOTO", 10, 10);
	}
	
	/*
	 * This method should not be called from the outside.
	 */
	private void makeCommand(String cmd, double magnitude1, double magnitude2){
		Turtle turtle = new Turtle(); // need to be deleted later
		// Turtle turtle = FrameLayoutNew.getTurtle();
		
		try { 
			Class<?> commandClass = Class.forName(myResources.getString(cmd));
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			for (Method m: methods){
				passInTurtle(turtle, command, m);
				if(m.getName().equals("setMagnitude")){
					m.invoke(command, magnitude1, magnitude2);
				}
		    }
			callExecuteMethod(command, methods);
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
			//System.out.println(COMMAND_INVALID_MESSAGE);
			e.printStackTrace();
		}
	}
	
	/*
	 * This method should not be called from the outside.
	 */
	protected void makeCommand(String cmd, double magnitude){
		Turtle turtle = new Turtle(); // need to be deleted later
		// Turtle turtle = FrameLayoutNew.getTurtle();
		try { 
			Class<?> commandClass = Class.forName(myResources.getString(cmd));
			AbstractCommand command = (AbstractCommand)commandClass.newInstance();
			Method[] methods = commandClass.getMethods();
			for (Method m: methods){
				passInTurtle(turtle, command, m);
				if(m.getName().equals("setMagnitude")){
					m.invoke(command, magnitude);
				}
		    }
			callExecuteMethod(command, methods);
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
			//System.out.println(COMMAND_INVALID_MESSAGE);
			e.printStackTrace();
		}
	}

	protected void callExecuteMethod(AbstractCommand command, Method[] methods)
			throws IllegalAccessException, InvocationTargetException {
		for (Method cur: methods){
			if (cur.getName().equals("execute")){
				cur.invoke(command);
		    }	
		}
	}
	
	protected void passInTurtle(Turtle turtle, AbstractCommand command, Method m)
			throws IllegalAccessException, InvocationTargetException {
		if(m.getName().equals("setTurtle")){
			m.invoke(command, turtle);
		}
	}
}
