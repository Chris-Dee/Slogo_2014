package commands;

public abstract class VariableCommand implements AbstractCommand{
	
	protected String myVariable;
	
	public void setVariable(String variable) {
		myVariable = variable;
	}
}
