package backEnd;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import parser.AbstractParser;
import parser.tree.StringNode;

public class LanguageManager {
	
    public static final String DEFAULT_LANGUAGE_PACKAGE = "resources/";
    public static final String DEFAULT_PROGRAM_LANGUAGE = "ProgramLanguage";
    
    private ResourceBundle myUserLanguage;
    private ResourceBundle myProgramLanguage;
    private Map<String, String> myLanguageMap;
	
	public LanguageManager(){
		myProgramLanguage = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE + DEFAULT_PROGRAM_LANGUAGE);
		myLanguageMap = new HashMap<String, String>();
	}
	
	public StringNode convertLanguage(StringNode root, String language) {
		//myParser.printTree(root);
		myUserLanguage = ResourceBundle.getBundle(DEFAULT_LANGUAGE_PACKAGE + language);
		makeLanguageMap();
		loopTree(root);
		//myParser.printTree(root);
		return root;
	}
	
	protected void makeLanguageMap(){
		for(String s: myUserLanguage.keySet()){
			String commands = myUserLanguage.getString(s);
			String[] commandList = commands.split(",");
			for(String cmd: commandList){
				myLanguageMap.put(cmd, s);
			}
		}
	}
	
	protected void loopTree(StringNode current){
		if(current == null) return;
		processNode(current);
		for(StringNode child: current.getChildren()){
			loopTree(child);
		}
	}
	
	// need revision: check syntax (e.g. a, b, :a) for base case, variableCommmand
	protected void processNode(StringNode current){
		if(AbstractParser.isParameter(current.getCommandString()) 
				|| AbstractParser.isVariable(current.getCommandString()) 
				|| AbstractParser.isVariableCommand(current.getCommandString())) return;
		String nonProgramLanguage = myLanguageMap.get(current.getCommandString());
		current.setCommandString(myProgramLanguage.getString(nonProgramLanguage));
	}

}
