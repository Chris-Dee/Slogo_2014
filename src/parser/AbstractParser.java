package parser;

import java.util.*;

public abstract class AbstractParser {
	
	protected List<String> myCommandList = new ArrayList<String>();
	
	public abstract List<String> parse(String s);
	
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
}
