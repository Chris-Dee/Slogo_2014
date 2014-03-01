package parser.tree;

public class ControlNode extends StringNode{
	
	protected String myExpression;
	protected String myCommands;

	public ControlNode(String data, String expression, String commands) {
		super(data);
		// TODO Auto-generated constructor stub
		myExpression = expression;
		myCommands = commands;
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

}
