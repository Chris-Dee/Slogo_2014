package parser;

import java.util.*;

import parser.tree.ControlNode;
import parser.tree.IfElseNode;
import parser.tree.StringNode;


public class TextParser extends AbstractParser {
	
	private static final String DEFAULT_RESOURCE_PATH = "backEnd/";
	private static final String DEFAULT_PARAMETER_FILE = "CommandParameters";
	private static final String DEFAULT_CONTROL_FILE = "CommandTypes";
	private ResourceBundle myResources;
	private ResourceBundle myControlCommands;
	private StringNode myRoot;
	private List<StringNode> myLeaves;
	private List<StringNode> myCommands;
	
	public TextParser() {
		myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + DEFAULT_PARAMETER_FILE);
		myControlCommands = ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH + DEFAULT_CONTROL_FILE);
		myCommandList = new ArrayList<String>();
		myLeaves = new ArrayList<StringNode>();
		myCommands = new ArrayList<StringNode>();
	}
	
	@Override
	public List<StringNode> parse(String s) {
		myCommandList.clear();
		String singleLineString = convertTextToSingleLine(s);
		formatStringArray(singleLineString);
		//int start = initializeTree(myCommandList);
		//buildTree(myRoot, start);
		while (!myCommandList.isEmpty()) {
			buildCommandList(myCommandList);
		}
		//myRoot = myLanguageManager.convertLanguage(myRoot);
		return myCommands;
	}

	private void buildCommandList(List<String> commands) {
		// TODO Auto-generated method stub
		StringNode newCommand;
		if (myControlCommands.containsKey(commands.get(0))) { //control statement
			if (myControlCommands.getString(commands.get(0)).equals("3")) {
				newCommand = new IfElseNode(commands.get(0), null, null, null);
				handleIfElseNode((IfElseNode) newCommand, 0);
				myCommands.add(newCommand);
				
			}
			else {
				newCommand = new ControlNode(commands.get(0), null, null);
				handleControlNode((ControlNode) newCommand, 0);
				myCommands.add(newCommand);
			}

		}
		else {
			newCommand = new StringNode(commands.get(0));
		}
		myCommands.add(newCommand);
	}

	private void formatStringArray(String s) {
		int depth = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
		    char c = s.charAt(i);
	        if(c=='['){
	            depth++;
	        }else if(c==']'){
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
			StringNode child1 = current.addChild(myCommandList.get(index+1));
			numChildren = buildTree(child1, index+1);
			StringNode child2 = current.addChild(myCommandList.get(index + numChildren + 1));
			numChildren += buildTree(child2, index + numChildren + 1);
		}
		else {

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
		numChildren ++;
		return numChildren;
	}
	

	private int handleIfElseNode(IfElseNode node, int index) {
		StringBuilder sb = new StringBuilder();
		String truecommands = null;
		String falsecommands = null;
		int i = index+1;

		if (!myControlCommands.containsKey(myCommandList.get(index+1))) {
			while (!myCommandList.get(i).startsWith("[")) {
				sb.append(myCommandList.get(i));
				i++;
			}
		}
		else {
			while (!myCommandList.get(i).endsWith("]")) {
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
			while (!myCommandList.get(i).startsWith("[")) {
				sb.append(myCommandList.get(i));
				i++;
			}
		}
		else {
			while (!myCommandList.get(i).endsWith("]")) {
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
		myCommandList.clear(); //REMOVE THIS LATER
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
//	public boolean checkForErrors() {
//		boolean answer = true;
//		for (StringNode leaf : myLeaves) {
//			if( (!hasRightNumChildren(leaf) || !allParentsHaveParameters(leaf)) 
//					&& getNumberOfParameters(leaf.getCommandString()) != 0 ){ answer = false; }
//		}
//		myLeaves.clear();
//		return answer;
//	}

	protected boolean hasRightNumChildren(StringNode node) {
		return node.getChildren().size() == getNumberOfParameters(node.getCommandString());
	}

	private int initializeTree(List<String> commands) {
		int index = 0;
		StringNode newCommand;
		if (myControlCommands.containsKey(myCommandList.get(0))) { //control statement
			if (myControlCommands.getString(myCommandList.get(0)).equals("3")) {
				newCommand = new IfElseNode(commands.get(0), null, null, null);
				index = handleIfElseNode((IfElseNode) newCommand, 0);
				myCommands.add(newCommand);
				return index;
				
			}
			else {
				newCommand = new ControlNode(commands.get(0), null, null);
				index = handleControlNode((ControlNode) newCommand, 0);
				myCommands.add(newCommand);
				return index;
			}

		}
		else {
			newCommand = new StringNode(commands.get(0));
		}
		myCommands.add(newCommand);
		return index;
	}

	private int getNumberOfParameters(String commandString) {
		if (isParameter(commandString) || commandString.contains(" ")) return 0;
		return Integer.parseInt(myResources.getString(commandString));
	}
	
}
