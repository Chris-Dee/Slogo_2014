package parser;

import java.util.*;

import backEnd.Managers.LanguageManager;
import backEnd.Managers.VariableManager;
import parser.tree.StringNode;

public abstract class AbstractParser {
	protected LanguageManager myLanguageManager;
	protected static final String DEFAULT_PROGRAM_LANGUAGE_FILE = "resources/ProgramLanguage";
	protected static final String DEFAULT_RESOURCE_PATH = "backEnd/";
	protected static final String DEFAULT_PARAMETER_FILE = "CommandParameters";
	protected static final String DEFAULT_CONTROL_FILE = "CommandTypes";
	protected ResourceBundle myResources;
	protected ResourceBundle myControlCommands;
	protected ResourceBundle mySymbols;
	
	public AbstractParser() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + DEFAULT_PARAMETER_FILE);
		myControlCommands = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + DEFAULT_CONTROL_FILE);
		mySymbols = ResourceBundle.getBundle(DEFAULT_PROGRAM_LANGUAGE_FILE);
	}

	public abstract List<StringNode> parse(String s);
	
	protected abstract int buildTree(StringNode node, int index);
	protected List<String> myCommandList;
	
	public boolean isParameter(String s) {
	    if (isVariable(s))
	    	return true;
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }

	    return true;
	}
	
	private boolean isVariable(String s) {
		// TODO Auto-generated method stub
		return s.startsWith(mySymbols.getString(VariableManager.VARIABLE_PROGRAM_SYNTAX));
	}

	public static double convertToDouble(String s) {
		return Double.parseDouble(s);
	}
	
	protected String convertTextToSingleLine(String s) {
		String singleLineString;
		if (s.contains("\n"))
			singleLineString = s.replaceAll("\n", " ");
		else
			singleLineString = s;
		
		return singleLineString.toUpperCase();
	}
	
	public void setLanguageManager(LanguageManager manager){
		myLanguageManager = manager;
	}

	/*
	 * Used to print the command tree structure for test only
	 * Should not be called or used in this project
	 */
	public static void printTree(StringNode current) {
		if (current == null) return;
		System.out.println(current.getCommandString() +" ");
		if(current.getChildren().size() > 0){
			printTree(current.getChildren().get(0));
		}
		else{
			System.out.println("X ");
		}
		
		if(current.getChildren().size() > 1){
			printTree(current.getChildren().get(1));
		}
		else{
			System.out.println("X ");
		}
	}
	
	public void setLanguage(String s) {
		myLanguageManager.setLanguage(s);
	}
	
	public static void printListNodes(List<StringNode> current){
		System.out.println("List of StringNodes: ");
		for(StringNode cur: current){
			System.out.print(cur.getCommandString());
		}
		System.out.println("Print Ends");
	}
}
