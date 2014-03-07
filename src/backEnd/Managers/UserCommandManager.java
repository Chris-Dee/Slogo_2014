package backEnd.Managers;

import java.util.Map;

import commands.UserCommand;

public class UserCommandManager {
	
	protected Map<String, UserCommand> myCommandMap;
	protected Map<String, Integer> myCommandParametermap;

	public void createNewUserCommand(String name, UserCommand command){
		myCommandMap.put(name, command);
	}
}
