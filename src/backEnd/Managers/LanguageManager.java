package backEnd.Managers;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import parser.AbstractParser;
import parser.tree.StringNode;

public class LanguageManager {
	
    public static final String DEFAULT_LANGUAGE_PACKAGE = "resources/";
    public static final String DEFAULT_PROGRAM_LANGUAGE = "ProgramLanguage";
    public static final String DEFAULT_LANGUAGE = "English";
    
    private ResourceBundle myUserLanguage;
    private ResourceBundle myProgramLanguage;
    private Map<String, String> myLanguageMap;
    private String myLanguage;
	
	public LanguageManager(){
		myProgramLanguage = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE + DEFAULT_PROGRAM_LANGUAGE);
		myLanguageMap = new HashMap<String, String>();
		setLanguage(DEFAULT_LANGUAGE);
	}
	
	public StringNode convertLanguage(StringNode root) {
		return loopTree(root);
	}
	
	public String getLanguage(){
		return myLanguage;
	}
	
	public void setLanguage(String language){
		if(language != null) myLanguage = language;
		myLanguageMap.clear();
		myUserLanguage = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE + myLanguage);
		makeLanguageMap();
	}
	
	protected void makeLanguageMap(){
		for(String s: myUserLanguage.keySet()){
			String commands = myUserLanguage.getString(s);
			String[] commandList = commands.split(",");
			for(String cmd: commandList){
				cmd.toUpperCase();
				myLanguageMap.put(cmd, s);
			}
		}
	}
	
	protected StringNode loopTree(StringNode current){
		if(current == null) return current;
		current = processNode(current);
		for(StringNode child: current.getChildren()){
			loopTree(child);
		}
		return current;
	}
	
	protected StringNode processNode(StringNode current){
		if(AbstractParser.isParameter(current.getCommandString())) return current;
		if(current.getCommandString().startsWith(myUserLanguage.getString("Variable"))){
			current.setCommandString(processVariableNode(current));
			return current;
		}
		String nonProgramLanguage = myLanguageMap.get(current.getCommandString());
		if(nonProgramLanguage ==  null){ return current; } // is a User Command
		current.setCommandString(myProgramLanguage.getString(nonProgramLanguage));
		return current;
	}
	
	/*
	 * Make sure that the variable notation is converted to the program language standard as well
	 */
	protected String processVariableNode(StringNode current){
		StringBuilder builder = new StringBuilder();
		builder.append(myProgramLanguage.getString("Variable"));
		String next = current.getCommandString().substring(myUserLanguage.getString("Variable").length());
		builder.append(next);
		return builder.toString();
	}
}
