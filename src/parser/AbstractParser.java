package parser;

import java.util.*;

import exception.IllegalCommandException;
import backEnd.Managers.VariableManager;
import parser.tree.StringNode;

public abstract class AbstractParser {
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

	public abstract List<StringNode> parse(String s) throws IllegalCommandException;
	
	protected abstract int buildTree(StringNode node, int index) throws IllegalCommandException;
	protected List<String> myCommandList;
	
	public static boolean isParameter(String s) {
//	    if (isVariable(s))
//	    	return true;
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }

	    return true;
	}
	
	public boolean isVariable(String s) {
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
		
		int startSpace = 0;
		while(singleLineString.charAt(startSpace) == ' ') {
			startSpace ++;
		}
		int endSpace = singleLineString.length()-1;
		while(singleLineString.charAt(endSpace) == ' ') {
			endSpace --;
		}
		
		return singleLineString.toUpperCase().substring(startSpace, endSpace+1);
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
	
	public static List<String> convertFromStringToList(String s){
		List<String> answer = new ArrayList<String>();
		String[] list = s.split(" ");
		for(String a: list){
			answer.add(a);
		}
		return answer;
	}
	
	public static void printListNodes(List<StringNode> current){
		System.out.println("List of StringNodes: ");
		for(StringNode cur: current){
			System.out.print(cur.getCommandString());
		}
		System.out.println("Print Ends");
	}
}
