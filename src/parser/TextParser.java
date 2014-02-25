package parser;
import java.util.*;
public class TextParser extends AbstractParser {
	
	private List<String> myCommandList = new ArrayList<String>();

	@Override
	public List<String> parse(String s) {
		// TODO Auto-generated method stub
		String singleLineString = convertTextToSingleLine(s);
		String[] stringArray = singleLineString.split(" ");
		for (int i = 0; i < stringArray.length; i++) {
			myCommandList.add(stringArray[i]);
		}
		return myCommandList;
	}
	
	
}
