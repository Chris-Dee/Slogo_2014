package parser;

import java.util.*;

import backEnd.Managers.LanguageManager;
import backEnd.Managers.UserCommandManager;
import backEnd.Managers.VariableManager;
import exception.IllegalCommandException;
import parser.tree.ControlNode;
import parser.tree.IfElseNode;
import parser.tree.StringNode;
import parser.tree.UserDefinedCommandNode;


public class TextParser extends AbstractParser {
	
	protected LanguageManager myLanguageManager;
	private StringNode myRoot;
	private List<StringNode> myLeaves;
	private List<StringNode> myCommands;
	private UserCommandManager myUserCommandManager;
	
	public TextParser() {
		super();
		myLanguageManager = new LanguageManager();
		myCommandList = new ArrayList<String>();
		myLeaves = new ArrayList<StringNode>();
		myCommands = new ArrayList<StringNode>();
		myUserCommandManager = new UserCommandManager();
 	}
	
	
	public void setLanguageManager(LanguageManager manager){
		myLanguageManager = manager;
	}
	
	@Override
	public List<StringNode> parse(String s) throws IllegalCommandException {
		System.out.println("parse() called: "+ s);
		myCommandList.clear();
		myCommands.clear();
		String singleLineString = convertTextToSingleLine(s);
		System.out.println("convertTextToSingleLine() called");
		System.out.println("Input string: " + singleLineString);
		formatStringArray(singleLineString);
		System.out.println("formateStringArray() called");
		int start = initializeTree(myCommandList);
		System.out.println("parse start index: "+start);
		buildTree(myLanguageManager.translateNode(myRoot), start);
		System.out.println("Root: " + myCommands.get(0).getCommandString());
		for (StringNode s1 : myCommands) {
			System.out.println("COMMANDS: " + s1.getCommandString());
		}
		return myCommands;
	}
	
	public void setUserCommandManager(UserCommandManager manager){
		myUserCommandManager = manager;
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
	protected int buildTree(StringNode current, int index) throws IllegalCommandException {
		int parameterNumber = 0;
		try{
			parameterNumber = getNumberOfParameters(current.getCommandString());
			System.out.println("ParameterNumber from build tree is: " + parameterNumber);
		} catch(Exception e){
			System.out.println("Error: "+current.getCommandString());
			throw new IllegalCommandException();
		}
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
			if (myControlCommands.containsKey(myCommandList.get(index+1)) || 
					myUserCommandManager.hasUserCommand(myCommandList.get(index+1))) { //control statement or user defined
				if (myUserCommandManager.hasUserCommand(myCommandList.get(index+1)) || 
						myControlCommands.getString(myCommandList.get(index+1)).equals("1")) {
					UserDefinedCommandNode child = current.addUserDefinedCommandChild(myCommandList.get(index+1));
					numChildren = handleUserDefinedCommandNode(child, index+1);
					numChildren += buildTree(child, index+numChildren+1);
				}
				else if (myControlCommands.getString(myCommandList.get(index + 1)).equals("3")) {
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
			
			System.out.println("Next node is: " + myCommandList.get(index+1));
			System.out.println("There are 0 parameters with " + myCommandList.get(index));
			StringNode nextRoot = new StringNode(myCommandList.get(index+1));
			nextRoot = myLanguageManager.translateNode(nextRoot);
			myCommands.add(nextRoot);
			buildTree(nextRoot, index+1);
			
		}
		numChildren ++;
		return numChildren;
	}
	

	private int handleUserDefinedCommandNode(UserDefinedCommandNode child, int index) {
		StringBuilder sb = new StringBuilder();
		int parameterNumber = myUserCommandManager.getNumParameterCommand(child.getCommandString());
		int i = index+1;
		
		if (myCommandList.get(i).startsWith(mySymbols.getString("ListStart"))) {
			int startSpace = 1;
			while(myCommandList.get(i).charAt(startSpace) == ' ') {
				startSpace ++;
			}
			int endSpace = myCommandList.get(i).length()-2;
			while(myCommandList.get(i).charAt(endSpace) == ' ') {
				endSpace --;
			}
			sb.append(myCommandList.get(i).substring(startSpace, endSpace+1));
		}
		else {
			while(parameterNumber != 0) {
				while (getNumberOfParameters(myCommandList.get(i)) != 0) {
					sb.append(myCommandList.get(i));
					i++;
				}
				sb.append(myCommandList.get(i));
				parameterNumber--;
				i++;
			}
		}

		
		child.setExpression(sb.toString());

		return i-index;
	}

	private int handleIfElseNode(IfElseNode node, int index) {
		StringBuilder sb = new StringBuilder();
		String truecommands = null;
		String falsecommands = null;
		int i = index+1;

		if (!myControlCommands.containsKey(myCommandList.get(i))) {
			System.out.println("handleifelse: " + myCommandList.get(i));
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
			if (myCommandList.get(i).startsWith(mySymbols.getString("ListStart"))) {
				sb.append(myCommandList.get(i));
				System.out.println("1" + sb.toString());

				i++;
			}
			else {
				while (!myCommandList.get(i).startsWith(mySymbols.getString("ListStart"))) {
					sb.append(myCommandList.get(i) + " ");
					System.out.println("2" + sb.toString());

					i++;
				}
			}
		}
		else {
			while (!myCommandList.get(i).endsWith(mySymbols.getString("ListEnd"))) {
				sb.append(myCommandList.get(i));
				System.out.println("3" + sb.toString());

				i++;
			}
			sb.append(myCommandList.get(i));
			i++;	
			System.out.println("4" + sb.toString());
		}
		commands = myCommandList.get(i);
		if (sb.toString().startsWith(mySymbols.getString("ListStart"))) {
			int startSpace = 1;
			while(sb.toString().charAt(startSpace) == ' ') {
				startSpace ++;
			}
			int endSpace = sb.toString().length()-2;
			while(sb.toString().charAt(endSpace) == ' ') {
				endSpace --;
			}
			node.setExpression(sb.toString().substring(startSpace, endSpace+1));
		}
		else {
			node.setExpression(sb.toString());
		}
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
	public boolean hasErrors(List<StringNode> roots) {	
		for (StringNode node: roots) {
			if(!childrenAndCommandAreValid(node)) return false;
		}
		return true;
	}
	private boolean commandIsValid(StringNode node) {
			return mySymbols.keySet().contains(node.getCommandString()) || 
					myUserCommandManager.hasUserCommand(node.getCommandString());
	}
	
	private boolean childrenAndCommandAreValid(StringNode current) {
		boolean answer = false;
		if (commandIsValid(current) && hasRightNumChildren(current)) {
			answer = true;
			if(current.getChildren() == null){
				return true;
			}
			for(StringNode child: current.getChildren()){
				if(!childrenAndCommandAreValid(child)){
					answer = false;
				}
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
				myRoot = myLanguageManager.translateNode(myRoot);
				myCommands.add(myRoot);
				return index;

			}
			else if (myControlCommands.getString(myCommandList.get(0)).equals("2")){
				myRoot = new ControlNode(commands.get(0), null, null);
				index = handleControlNode((ControlNode) myRoot, 0);
				myRoot = myLanguageManager.translateNode(myRoot);
				myCommands.add(myRoot);
				return index;
			}
			else {
				myRoot = new UserDefinedCommandNode(commands.get(0), null);
				index = handleUserDefinedCommandNode((UserDefinedCommandNode) myRoot, 0);
				myRoot = myLanguageManager.translateNode(myRoot);
				myCommands.add(myRoot);
				return index;
			}
		}
		else {
			if (myUserCommandManager.hasUserCommand(myCommandList.get(0))) {
				myRoot = new UserDefinedCommandNode(commands.get(0), null);
				index = handleUserDefinedCommandNode((UserDefinedCommandNode) myRoot, 0);
				myRoot = myLanguageManager.translateNode(myRoot);
				myCommands.add(myRoot);
				return index;
			}
			else {
				System.out.println("Not control statement: "+commands.get(0));
				myRoot = new StringNode(commands.get(0));
			}
		}
		myRoot = myLanguageManager.translateNode(myRoot);
		myCommands.add(myRoot);
		return index;
	}
	

	private int getNumberOfParameters(String commandString) {
//		System.out.println("getNumberOfParameters called");
		if (isParameter(commandString) || commandString.contains(" ") || 
				isVariable(commandString) || myUserCommandManager.hasUserCommand(commandString)) return 0;
//		System.out.println("getNumberOfParameters hasUserCommand: " + myUserCommandManager.hasUserCommand(commandString));
		return Integer.parseInt(myResources.getString(commandString));
	}
	
}
