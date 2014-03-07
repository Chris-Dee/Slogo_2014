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
}