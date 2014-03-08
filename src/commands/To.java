package commands;

import java.util.ArrayList;
import java.util.List;

import parser.AbstractParser;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;

public class To extends ControlCommand {
	
	public static final int DEFAULT_TEST_PARAMETERNUM = 0;
	
	public To(){
		super();
	}

	protected String myName;
	
	public void setCommandName(String n){
		if(n != null) myName = n;
	}

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		initExecute();
		System.out.println("To executed: "+myName+" "+myExpression+" "+myCommands);
		if(myName == null || myExpression == null || myCommands == null){
			System.out.println("null name or expression or commands");
			return 0;
		}
		
		List<StringNode> exprRoots = myParser.parse(myExpression);
		if(!ifLegalParameter(exprRoots)) {
			System.out.println("parameter not legal");
			return 0;
		}
		List<String> paraVariables = getVariableListFromListNode(exprRoots);
		for(String v: paraVariables){
			myVariableManager.setValueToVariable(v, DEFAULT_TEST_PARAMETERNUM);	
		}
		
		List<StringNode> commandRoots = myParser.parse(myCommands);
		if(myParser.hasErrors(commandRoots)){
			System.out.println("commands not legal");
			return 0;
		}
		List<String> cmdVariables = getVariableListFromListNode(commandRoots);
		if(!ifTwoStringListsEqual(cmdVariables, paraVariables)) return 0;
		for(String v: paraVariables){
			myVariableManager.removeVariable(v);	
		}
		
		System.out.println("TO: is Legal");
		
		UserCommand cmd = new UserCommand();
		cmd.setExpression(myExpression);
		cmd.setCommands(myCommands);
		myUserCommandManager.createNewUserCommand(myName, exprRoots.size(), cmd);
		return 1;
	}
	
	protected boolean ifTwoStringListsEqual(List<String> a, List<String> b){
		for(String s: a){
			if(!b.contains(s)) return false;
		}
		return true;
	}
	
	protected boolean ifLegalParameter(List<StringNode> roots){
		for(StringNode root: roots){
			if(!myParser.isVariable(root.getCommandString())){
				if(!AbstractParser.isParameter(root.getCommandString())) return false;
			}
		}
		return true;
	}
	
	protected List<String> getVariableListFromListNode(List<StringNode> roots){
		List<String> vList = new ArrayList<String>();
		for(StringNode root: roots){
			vList = appendTwoLists(vList, getVariables(root));
		}
		return vList;
	}
	
	protected List<String> getVariables(StringNode current){
		if(current == null) return null;
		List<String> variables = new ArrayList<String>();
		if(myParser.isVariable(current.getCommandString())){
			variables.add(current.getCommandString());
		}
		if(current.getChildren() != null){
			for(StringNode child: current.getChildren()){
				variables = appendTwoLists(variables, getVariables(child));
			}
		}
		return variables;
	}
	
	protected List<String> appendTwoLists(List<String> a, List<String> b){
		for(String s: b){
			a.add(s);
		}
		return a;
	}

}
