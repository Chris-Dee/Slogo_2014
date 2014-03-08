package parser.tree;

public class ControlNode extends StringNode implements IPrintable{
	
//	protected String myExpression;
//	protected String myCommands;
//	protected String myElseCommands;

	public ControlNode(String data, String expression, String commands) {
		super(data);
		myExpression = expression;
		System.out.println("controlnode: " + myExpression);
		myCommands = commands;
		System.out.println("controlnode: " + myCommands);
		myElseCommands=null;
	}
//	
//	public String getExpression() {
//		return myExpression;
//	}
//	
//	public void setExpression(String s) {
//		myExpression = s;
//	}
//	
//	public String getCommands() {
//		return myCommands;
//	}
//	
//	public void setCommands(String s) {
//		myCommands = s;
//}
//
//	public String getElseCommands() {
//		return myElseCommands;
//	}

	@Override
	public void printParameters() {
		// TODO Auto-generated method stub
		System.out.println("Control Node \n ============");
		System.out.println("Parameters 1: " + myExpression);
		System.out.println("Parameter 2: " + myCommands);
		System.out.println("Parameter 3: " + myElseCommands);
	}

}
