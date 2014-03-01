package parser.tree;

public class ControlNode extends StringNode{
	
	protected String myExpression;
	protected String myCommands;
	protected String myElseCommands;

	public ControlNode(String data, String expression, String commands) {
		super(data);
		myExpression = expression;
		myCommands = commands;
		myElseCommands=null;
	}
	
	public String getExpression() {
		return myExpression;
	}
	
	public void setExpression(String s) {
		myExpression = s;
	}
	
	public String getCommands() {
		return myCommands;
	}
	
	public void setCommands(String s) {
		myCommands = s;
}

	public String getElseCommands() {
		return myElseCommands;
	}

}
