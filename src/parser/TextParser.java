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
		buildTree(myRoot, 0);
		return myRoot;
	}

	@Override
	protected int buildTree(StringNode current, int index) {
		int parameterNumber = getNumberOfParameters(current.getCommandString());

		if(index >= myCommandList.size()) return 0;
		//if leafnode
		if( parameterNumber == 0 && !allParentsHaveParameters(current) ){
			return 1;
		}
		
		if(parameterNumber == 2) {
			StringNode child1 = current.addChild(myCommandList.get(index+1));
			int offset = buildTree(child1, index+1) + 1;
			StringNode child2 = current.addChild(myCommandList.get(index+offset));
			buildTree(child2, index+offset);
		}
		else {
			StringNode child = current.addChild(myCommandList.get(index+1));
			buildTree(child, index+1);
		}

		return 1;
		
	}
	
	private boolean allParentsHaveParameters(StringNode current){
		while(current.getParent() != null){ // not a root
			current = current.getParent();
			int parameterNum = getNumberOfParameters(current.getCommandString());
			if(current.getChildren().size() != parameterNum){
				return false;
			}
		}
		return true;
	}

	private void initializeTree(List<String> commands) {
		// TODO Auto-generated method stub
		myRoot = new StringNode(commands.get(0));
	}

	private int getNumberOfParameters(String commandString) {
		// TODO Auto-generated method stub
		if (isParameter(commandString)) return 0;
		return Integer.parseInt(myResources.getString(commandString));
	}
	
}
