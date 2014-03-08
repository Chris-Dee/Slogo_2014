package commands;

import exception.UndefinedVariableException;
import parser.AbstractParser;
import backEnd.Managers.VariableManager;

public abstract class FourParameterOperationCommand implements AbstractCommand{

	protected double myExpression1;
	protected double myExpression2;
	protected double myExpression3;
	protected double myExpression4;
	protected VariableManager myVariableManager;
	
	/*
	 * Must set setVariableManager before setMagnitude
	 */
	public void setQuadrupleMagnitude(String expression1, String expression2, String expression3, String expression4) throws UndefinedVariableException {
		myExpression1 = convertMagnitudeToValue(expression1);
		myExpression2 = convertMagnitudeToValue(expression2);
		myExpression3 = convertMagnitudeToValue(expression3);
		myExpression4 = convertMagnitudeToValue(expression4);

	}
	
	public void setVariableManager(VariableManager manager){
		myVariableManager = manager;
	}
	
	protected double convertMagnitudeToValue(String magnitude) throws UndefinedVariableException{
		if(myVariableManager.isVariable(magnitude)){
			try{
				return myVariableManager.getValueOfVariable(magnitude);		
			} catch(Exception e){
				throw new UndefinedVariableException();
			}
		}
		return AbstractParser.convertToDouble(magnitude);
	}
}
