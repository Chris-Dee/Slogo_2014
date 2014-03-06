package commands;

public class Make extends TwoParameterOperationCommand{
	
	public Make(){}
	
	protected String myVariable;
	
	@Override
	public double execute() {
		myVariableManager.setValueToVariable(myVariable, myExpression2);
		return myExpression2;
	}
	
	@Override
	public void setDoubleMagnitude(String expression1, String expression2) {
		if(expression1 != null) myVariable = expression1;
		myExpression2 = convertMagnitudeToValue(expression2);
	}
}
