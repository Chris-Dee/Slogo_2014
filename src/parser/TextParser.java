package parser;

import java.util.*;

import parser.tree.StringNode;


public class TextParser extends AbstractParser {
	
	private static final String DEFAULT_RESOURCE_PATH = "backEnd/";
	private static final String DEFAULT_PARAMETER_FILE = "CommandParameters";
	
	private ResourceBundle myResources;
	private StringNode myRoot;
	
	public TextParser() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + DEFAULT_PARAMETER_FILE);
		myCommandList = new ArrayList<String>();
	}
	
	@Override
	public StringNode parse(String s) {
		String singleLineString = convertTextToSingleLine(s);
		String[] stringArray = singleLineString.split(" ");
		for (int i = 0; i < stringArray.length; i++) {
			myCommandList.add(stringArray[i]);
		}
		System.out.println("myCommandList: " + myCommandList.size());
		initializeTree(myCommandList);
		buildTree(myRoot, 0);
		myCommandList.clear();
		return myRoot;
	}

	@Override
	// This method needs revision
	protected int buildTree(StringNode current, int index) {
		int parameterNumber = getNumberOfParameters(current.getCommandString());

		if(index == myCommandList.size()) return 0;
		if( parameterNumber == 0 && !allParentsHaveParameters(current) ){ // if leaf node
			return 1;
		}
		
		if(parameterNumber == 2) {
			StringNode child1 = current.addChild(myCommandList.get(index+1));
			int offset = buildTree(child1, index+1) + 1;
			StringNode child2 = current.addChild(myCommandList.get(index + offset));
			buildTree(child2, index + offset);
		}
		else if (index+1 < myCommandList.size()){
			StringNode child = current.addChild(myCommandList.get(index+1));
			buildTree(child, index+1);
		}

		return 1;
		
	}
	
	private boolean allParentsHaveParameters(StringNode current){
		while(current.getParent() != null){ // not a root
			current = current.getParent();
			if(current.getChildren().size() != getNumberOfParameters(current.getCommandString()) ){
				return false;
			}
		}
		return true;
	}

	private void initializeTree(List<String> commands) {
		myRoot = new StringNode(commands.get(0));
	}

	private int getNumberOfParameters(String commandString) {
		if (isParameter(commandString)) return 0;
		return Integer.parseInt(myResources.getString(commandString));
	}
	
}
