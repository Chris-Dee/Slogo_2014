package parser.tree;

import java.util.*;

public class StringNode implements IPrintable{
	protected String myData;
	protected StringNode myParent;
	protected List<StringNode> myChildren;
	
	public StringNode(String data) {
		myData = data;
		myParent = null;
		myChildren = new ArrayList<StringNode>();
	}
	
	public StringNode addChild(String data) {
		StringNode child = new StringNode(data);
		child.myParent = this;
		myChildren.add(child);
		return child;
	}
	
	public ControlNode addControlChild(String data)  {
		ControlNode child = new ControlNode(data, null, null);
		child.myParent = this;
		myChildren.add(child);
		return child;
	}
	
	public IfElseNode addIfElseChild(String data) {
		IfElseNode child = new IfElseNode(data, null, null, null);
		child.myParent = this;
		myChildren.add(child);
		return child;
	}
	
	public UserDefinedCommandNode addUserDefinedCommandChild(String data) {
		UserDefinedCommandNode child = new UserDefinedCommandNode(data, null);
		child.myParent = this;
		myChildren.add(child);
		return child;
	}
	
	public StringNode getParent() {
		return myParent;
	}
	
	public String getCommandString() {
		return myData;
	}
	
	public List<StringNode> getChildren() {
		return myChildren;
	}
	
	public void setCommandString(String s){
		if(s != null){ myData = s; }
	}

	@Override
	public void printParameters() {
		// TODO Auto-generated method stub
		for(StringNode child : myChildren) {
			System.out.println(child.getCommandString());
		}
	}
	
}
