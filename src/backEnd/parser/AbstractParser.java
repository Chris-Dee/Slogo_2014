package backEnd.parser;

import java.util.*;

public abstract class AbstractParser {
	
	public abstract List<String> parse(String s);

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
