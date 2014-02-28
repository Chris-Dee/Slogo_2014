package parser;

import java.util.Arrays;
import java.util.List;

import parser.tree.StringNode;


public class TextParser extends AbstractParser {
	//This is super hardcoded MUST BE REFACTORED
	private final String[] twoParams = {"SETXY", "GOTO", "SUM", "+", "DIFFERENCE",
			"-", "PRODUCT", "*", "QUOTIENT", "/", "REMAINDER", "%", "POW", "LESS?", 
			"LESSP", "GREATER?", "GREATERP", "EQUAL?", "EQUALP", "NOTEQUAL?", 
			"NOTEQUALP", "AND", "OR"};
	private StringNode myRoot;
	
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
		while (!commands.isEmpty()) {
			if (!isParameter(commands.get(0))) {
				int parameterNumber = getNumberOfParameters(node.getCommandString());
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
		
	}

	private void initializeTree(List<String> commands) {
		// TODO Auto-generated method stub
		myRoot = new StringNode(commands.get(0));
		commands.remove(0);
	}

	private int getNumberOfParameters(String commandString) {
		// TODO Auto-generated method stub
		if(Arrays.asList(twoParams).contains(commandString))
			return 2;
		return 1;
	}
	
}
