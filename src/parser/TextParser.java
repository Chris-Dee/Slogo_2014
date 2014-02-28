package parser;

import java.util.*;

import parser.tree.StringNode;


public class TextParser extends AbstractParser {
	//This is super hardcoded MUST BE REFACTORED
//	private final String[] twoParams = {"SETXY", "GOTO", "SUM", "+", "DIFFERENCE",
//			"-", "PRODUCT", "*", "QUOTIENT", "/", "REMAINDER", "%", "POW", "LESS?", 
//			"LESSP", "GREATER?", "GREATERP", "EQUAL?", "EQUALP", "NOTEQUAL?", 
//			"NOTEQUALP", "AND", "OR"};
	private static final String DEFAULT_RESOURCE_PATH = "backEnd/";
	private static final String DEFAULT_PARAMETER_FILE = "CommandParameters.properties";
	private ResourceBundle myResources;
	private StringNode myRoot;
	
	public TextParser() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + DEFAULT_PARAMETER_FILE);
	}
	
	@Override
	public StringNode parse(String s) {
		String singleLineString = convertTextToSingleLine(s);
		String[] stringArray = singleLineString.split(" ");
		for (int i = 0; i < stringArray.length; i++) {
			myCommandList.add(stringArray[i]);
		}
		initializeTree(myCommandList);
		buildTree(myCommandList, myRoot);
		return myRoot;
	}

	@Override
	protected void buildTree(List<String> commands, StringNode node) {
		// TODO Auto-generated method stub
		if(commands.isEmpty()) return;
		int parameterNumber = getNumberOfParameters(node.getCommandString());
		if(parameterNumber == 0) {
			StringNode child = node.addChild(commands.get(0));
			commands.remove(0);
			buildTree(commands, child);
		}
		else {
			for (int i = 0; i < parameterNumber; i++) {
				node.addChild(commands.get(i));
			}
		}
		
		if (!isParameter(commands.get(0))) {
			for (int i = 0; i < parameterNumber; i++) {
				node.addChild(commands.get(i));
			}
			for (int i = 0; i < parameterNumber; i++) {
				myCommandList.remove(i);
			}
			for (StringNode child : node.getChildren()) {
				if (!isParameter(child.getCommandString()))
					buildTree(commands, child);
			}
		}
		else {
			StringNode child = node.addChild(commands.get(0));
			commands.remove(0);
			buildTree(commands, child);
		}
		
	}

	private void initializeTree(List<String> commands) {
		// TODO Auto-generated method stub
		myRoot = new StringNode(commands.get(0));
		commands.remove(0);
	}

	private int getNumberOfParameters(String commandString) {
		// TODO Auto-generated method stub
		return Integer.parseInt(myResources.getString(commandString));
	}
	
}
