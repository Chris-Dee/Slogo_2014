package parser;

import java.util.*;

import parser.tree.StringNode;

public abstract class AbstractParser {
	
	protected List<String> myCommandList;
	
	public abstract StringNode parse(String s);
	
	protected abstract int buildTree(StringNode node, int index);
	
	public boolean isParameter(String s) {
	    try { 
	        Double.parseDouble(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	
	public double convertToDouble(String s) {
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
	
	public abstract boolean checkForErrors();

	/*
	 * Used to print the command tree structure for test only
	 * Should not be called or used in this project
	 */
	public void printTree(StringNode current) {
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
