package parser;

import java.util.*;

import backEnd.LanguageManager;
import parser.tree.StringNode;

public abstract class AbstractParser {
	protected LanguageManager myLanguageManager;
	
	public abstract List<StringNode> parse(String s);
	
	protected abstract int buildTree(StringNode node, int index);
	protected List<String> myCommandList;
	
	public static boolean isParameter(String s) {
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
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
}
