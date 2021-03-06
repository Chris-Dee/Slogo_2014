package backEnd.Managers;

import java.util.HashMap;
import java.util.Map;

import commands.UserCommand;

public class UserCommandManager {
	
	protected Map<String, UserCommand> myCommandMap;
	protected Map<String, Integer> myCommandParameterMap;
	
	public UserCommandManager(){
		myCommandMap = new HashMap<String, UserCommand>();
		myCommandParameterMap = new HashMap<String, Integer>();
	}

	public void createNewUserCommand(String name, int parameterNum, UserCommand command){
		myCommandMap.put(name, command);
		myCommandParameterMap.put(name,  parameterNum);
	}
	
	/*
	 * return -1 if commandName is not stored as a user command
	 */
	public int getNumParameterCommand(String commandName){
		if(!myCommandParameterMap.containsKey(commandName)){ return -1; }
		return myCommandParameterMap.get(commandName);
	}
	
	public void removeUserCommand(String name){
		myCommandMap.remove(name);
		myCommandParameterMap.remove(name);
	}
	
	public boolean hasUserCommand(String commandName){
		return myCommandParameterMap.containsKey(commandName);
	}
	
	public UserCommand getUserCommand(String commandName){
		return myCommandMap.get(commandName);
	}
	
	public Map<String, Integer> getCommandParameterMap(){
		return myCommandParameterMap;
	}
}
