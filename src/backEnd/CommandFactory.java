package backEnd;

import java.util.List;

import commands.FDBKCommand;

public class CommandFactory {
	
	public CommandFactory(){
		
	}
	
	public void makeCommands(List<String> cmds){
		
	}
	
	// For test only
	private void executeCommand(String cmd, double magnitude){
		if(cmd.startsWith("FD") || cmd.startsWith("FORWARD")){
			FDBKCommand current = new FDBKCommand("FORWARD", 50, );
		}
		
		try{
			Class command = Class.forName(cmd);
			
			
//		     Class c = Class.forName(“java.lang.String”);
//		     String s = (String)(c.newInstance());
//		     Method[] methods = c.getMethods();
//		     for (Method m: methods){
//		          System.out.println(m); // print out all the methods of String class
//		          if (m.getName().equals(“toLowerCase”)){
//		               System.out.println(m.invoke(s, new Object[] {})); // call the method m, avoid hardcoding
//		          }
//		     }
		}
		catch{
		}
	}
}
