package backEnd;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ResourceBundle;

import commands.AbstractCommand;
import frontEnd.Turtle;

public class CommandFactory {
    public static final String DEFAULT_COMMAND_PACKAGE = "commands/";
    public static final String COMMAND_INVALID_MESSAGE = "Please enter a valid command!";
    
    private ResourceBundle myResources;
	
	public CommandFactory(){
        myResources = ResourceBundle.getBundle(DEFAULT_COMMAND_PACKAGE);
	}
	
	public void makeCommands(String root){
		executeCommand("FD", 50);
		
	}
	
	private void executeCommand(String cmd, double magnitude){
		Turtle testTurtle = new Turtle();
		try { 
			Class<?> command = Class.forName(myResources.getString(cmd));
			AbstractCommand s = (AbstractCommand)command.newInstance();
			Method[] methods = command.getMethods();
			for (Method m: methods){
				if(m.getName().equals("setTurtle")){
					m.invoke(s, testTurtle);
				}
				if(m.getName().equals("setMagnitude")){
					m.invoke(s, magnitude);
				}
		    }
			for (Method cur: methods){
				if (cur.getName().equals("execute")){
					cur.invoke(s);
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
			//System.out.println(COMMAND_INVALID_MESSAGE);
			e.printStackTrace();
		}
	}
}
