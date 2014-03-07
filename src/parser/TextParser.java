package parser;

import java.util.*;

import parser.tree.ControlNode;
import parser.tree.IfElseNode;
import parser.tree.StringNode;


public class TextParser extends AbstractParser {
	

	private StringNode myRoot;
	private List<StringNode> myLeaves;
	private List<StringNode> myCommands;
	private List<StringNode> myTranslatedCommands;
	
	public TextParser() {

		myCommandList = new ArrayList<String>();
		myLeaves = new ArrayList<StringNode>();
		myCommands = new ArrayList<StringNode>();
		myTranslatedCommands = new ArrayList<StringNode>();
	}
	
	@Override
	public List<StringNode> parse(String s) {
		myCommandList.clear();
		myCommands.clear();
		String singleLineString = convertTextToSingleLine(s);
		formatStringArray(singleLineString);
		int start = initializeTree(myCommandList);
		buildTree(myLanguageManager.translateNode(myRoot), start);
		System.out.println("Root: " + myCommands.get(0).getCommandString());
		for (StringNode s1 : myCommands) {
			System.out.println("COMMANDS: " + s1.getCommandString());
		}
		return myTranslatedCommands;
	}

	private void formatStringArray(String s) {
		int depth = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
		    char c = s.charAt(i);
	        if((c+"").equals(mySymbols.getString("ListStart"))){
	            depth++;
	        }else if((c+"").equals(mySymbols.getString("ListEnd"))){
	            depth--;
	        }else if(c==' ' && depth==0){
	        	myCommandList.add(sb.toString());
	            sb = new StringBuilder();
	            continue;
	        }
	        sb.append(c);
		}

		myCommandList.add(sb.toString());
	}

	@Override
	protected int buildTree(StringNode current, int index) {
		int parameterNumber = getNumberOfParameters(current.getCommandString());
		if(index == myCommandList.size()) return 0;
		if( (parameterNumber == 0 && !allParentsHaveParameters(current)) || 
				index + 1 == myCommandList.size()){ // if leaf node
			//System.out.println(current.getCommandString() + " is the base case");
			myLeaves.add(current);
			return 1;
		}
		int numChildren = 0;
		
		if(parameterNumber == 2) {
			if (myControlCommands.containsKey(myCommandList.get(index+1))) { //control statement
				if (myControlCommands.getString(myCommandList.get(index + 1)).equals("3")) {
					IfElseNode child = current.addIfElseChild(myCommandList.get(index+1));
					numChildren = handleIfElseNode(child, index+1);
					numChildren += buildTree(child, index+numChildren+1);

				}
				else {
					ControlNode child = current.addControlChild(myCommandList.get(index+1));
					numChildren = handleControlNode(child, index+1);
					numChildren += buildTree(child, index+numChildren+1);
				}

			}
			else {
				StringNode child1 = current.addChild(myCommandList.get(index+1));
				numChildren = buildTree(child1, index+1);
			}
			if (myControlCommands.containsKey(myCommandList.get(index+numChildren+1))) { //control statement
				if (myControlCommands.getString(myCommandList.get(index + numChildren+1)).equals("3")) {
					IfElseNode child = current.addIfElseChild(myCommandList.get(index+numChildren+1));
					numChildren = handleIfElseNode(child, index+numChildren+1);
					numChildren += buildTree(child, index+numChildren+1);

				}
				else {
					ControlNode child = current.addControlChild(myCommandList.get(index+numChildren+1));
					numChildren = handleControlNode(child, index+numChildren+1);
					numChildren += buildTree(child, index+numChildren+1);
				}
			}
			else {
				StringNode child2 = current.addChild(myCommandList.get(index + numChildren + 1));
				numChildren += buildTree(child2, index + numChildren + 1);
			}

		}
		else if (parameterNumber == 1) {
			if (myControlCommands.containsKey(myCommandList.get(index+1))) { //control statement
				if (myControlCommands.getString(myCommandList.get(index + 1)).equals("3")) {
					IfElseNode child = current.addIfElseChild(myCommandList.get(index+1));
					numChildren = handleIfElseNode(child, index+1);
					numChildren += buildTree(child, index+numChildren+1);

				}
				else {
					ControlNode child = current.addControlChild(myCommandList.get(index+1));
					numChildren = handleControlNode(child, index+1);
					numChildren += buildTree(child, index+numChildren+1);
				}

			}
			else {
				StringNode child = current.addChild(myCommandList.get(index+1));
				numChildren += buildTree(child, index+1);
			}
		}
		else {
			StringNode nextRoot = new StringNode(myCommandList.get(index+1));
			myTranslatedCommands.add(myLanguageManager.translateNode(nextRoot));
			myCommands.add(nextRoot);
			buildTree(nextRoot, index+1);
			
		}
		numChildren ++;
		return numChildren;
	}
	

	private int handleIfElseNode(IfElseNode node, int index) {
		StringBuilder sb = new StringBuilder();
		String truecommands = null;
		String falsecommands = null;
		int i = index+1;

		if (!myControlCommands.containsKey(myCommandList.get(index+1))) {
			while (!myCommandList.get(i).startsWith(mySymbols.getString("ListStart"))) {
				sb.append(myCommandList.get(i));
				i++;
			}
		}
		else {
			while (!myCommandList.get(i).endsWith(mySymbols.getString("ListEnd"))) {
				sb.append(myCommandList.get(i));
				i++;
			}
			sb.append(myCommandList.get(i));
			i++;		
		}
		truecommands = myCommandList.get(i);
		i++;
		falsecommands = myCommandList.get(i);
		
		node.setExpression(sb.toString());

		int startSpace = 1;
		while(truecommands.charAt(startSpace) == ' ') {
			startSpace ++;
		}
		int endSpace = truecommands.length()-2;
		while(truecommands.charAt(endSpace) == ' ') {
			endSpace --;
		}
		node.setCommands(truecommands.substring(1, truecommands.length()-1));
		
		startSpace = 1;
		while(truecommands.charAt(startSpace) == ' ') {
			startSpace ++;
		}
		endSpace = truecommands.length()-2;
		while(truecommands.charAt(endSpace) == ' ') {
			endSpace --;
		}
		
		node.setElseCommand(falsecommands.substring(1, falsecommands.length()-1));

		i++;
		return i-index;
	}

	
	
	private int handleControlNode(ControlNode node, int index) {
		StringBuilder sb = new StringBuilder();
		String commands = null;
		int i = index+1;

		if (!myControlCommands.containsKey(myCommandList.get(i))) {
			while (!myCommandList.get(i).startsWith(mySymbols.getString("ListStart"))) {
				sb.append(myCommandList.get(i));
				i++;
			}
		}
		else {
			while (!myCommandList.get(i).endsWith(mySymbols.getString("ListEnd"))) {
				sb.append(myCommandList.get(i));
				i++;
			}
			sb.append(myCommandList.get(i));
			i++;		
		}
		commands = myCommandList.get(i);

		node.setExpression(sb.toString());
		int startSpace = 1;
		while(commands.charAt(startSpace) == ' ') {
			startSpace ++;
		}
		int endSpace = commands.length()-2;
		while(commands.charAt(endSpace) == ' ') {
			endSpace --;
		}
		node.setCommands(commands.substring(startSpace, endSpace+1));

		i++;
		return i-index;
	}
	
	


	private boolean allParentsHaveParameters(StringNode current){
		while(current.getParent() != null){ // not a root
			current = current.getParent();
			if(!hasRightNumChildren(current) && getNumberOfParameters(current.getCommandString()) != 0 ){ return false; }
		}
		return true;
	}
	
	/*
	 * May not be needed anymore; exceptions are passed automatically to SlogoModel
	 */
	public boolean checkForErrors(List<StringNode> roots) {
		boolean answer = true;
		
		for (StringNode node: roots) {
			while(node != null){
				if( (!hasRightNumChildren(node)) 
						&& getNumberOfParameters(node.getCommandString()) != 0 ){ answer = false;}	
			}
		}
		return answer;
	}

	protected boolean hasRightNumChildren(StringNode node) {
		return node.getChildren().size() == getNumberOfParameters(node.getCommandString());
	}

	private int initializeTree(List<String> commands) {
		int index = 0;
		if (myControlCommands.containsKey(myCommandList.get(0))) { //control statement
			if (myControlCommands.getString(myCommandList.get(0)).equals("3")) {
				myRoot = new IfElseNode(commands.get(0), null, null, null);
				index = handleIfElseNode((IfElseNode) myRoot, 0);
				myTranslatedCommands.add(myLanguageManager.translateNode(myRoot));
				myCommands.add(myRoot);
				return index;

			}
			else {
				myRoot = new ControlNode(commands.get(0), null, null);
				index = handleControlNode((ControlNode) myRoot, 0);
				myTranslatedCommands.add(myLanguageManager.translateNode(myRoot));
				myCommands.add(myRoot);
				return index;
			}
		}
		else {
			myRoot = new StringNode(commands.get(0));
			myTranslatedCommands.add(myLanguageManager.translateNode(myRoot));
		}
		
		myCommands.add(myRoot);
		return index;
	}
	

	private int getNumberOfParameters(String commandString) {
		if (isParameter(commandString) || commandString.contains(" ")) return 0;
		return Integer.parseInt(myResources.getString(commandString));
	}
	
}
