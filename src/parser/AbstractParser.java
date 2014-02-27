package parser;

import java.util.*;

import parser.tree.StringNode;

public abstract class AbstractParser {
	
	protected List<String> myCommandList = new ArrayList<String>();
	
	public abstract StringNode parse(String s);
	
	protected abstract void buildTree(List<String> commands, StringNode node);
	
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
		// TODO Auto-generated method stub
		String singleLineString;
		if (s.contains("\n"))
			singleLineString = s.replaceAll("\n", "");
		else
			singleLineString = s;
		
		return singleLineString.toUpperCase();
	}
	
	protected boolean checkValidInput(String s) {
		return true;
	}
}
