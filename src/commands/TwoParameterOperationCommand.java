package commands;

import parser.AbstractParser;
import PreferenceManagers.VariableManager;

public abstract class TwoParameterOperationCommand implements AbstractCommand{

	protected double myExpression1;
	protected double myExpression2;
	protected VariableManager myVariableManager;
	
	/*
	 * Must set setVariableManager before setMagnitude
	 */
	public void setDoubleMagnitude(String expression1, String expression2) {
		myExpression1 = convertMagnitudeToValue(expression1);
		myExpression2 = convertMagnitudeToValue(expression2);
	}
	
	public void setVariableManager(VariableManager manager){
		myVariableManager = manager;
	}
	
	protected double convertMagnitudeToValue(String magnitude){
		if(myVariableManager.isVariable(magnitude)){
			return myVariableManager.getValueOfVariable(magnitude);
		}
		return AbstractParser.convertToDouble(magnitude);
	}
}
