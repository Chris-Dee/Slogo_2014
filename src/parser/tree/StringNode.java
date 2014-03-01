package parser.tree;

import java.util.*;

public class StringNode {
	private String myData;
	private StringNode myParent;
	private List<StringNode> myChildren;
	
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
}
