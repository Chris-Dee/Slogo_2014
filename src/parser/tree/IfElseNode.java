package parser.tree;

public class IfElseNode extends ControlNode{

	public IfElseNode(String data, String expression, String commands,
			String elseCommands) {
		super(data, expression, commands);
		myElseCommands = elseCommands;
	}
	
	public void setElseCommand(String s) {
		myElseCommands = s;
	}
	


}
