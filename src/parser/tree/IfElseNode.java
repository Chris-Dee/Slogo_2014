package parser.tree;

public class IfElseNode extends ControlNode{
	
	private String myElseCommands;

	public IfElseNode(String data, String expression, String commands,
			String elseCommands) {
		super(data, expression, commands);
		// TODO Auto-generated constructor stub
		myElseCommands = elseCommands;
	}
	
	public String getElseCommands() {
		return myElseCommands;
	}

}
