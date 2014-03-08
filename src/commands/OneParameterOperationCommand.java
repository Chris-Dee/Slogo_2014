package commands;

import exception.UndefinedVariableException;
import backEnd.Managers.VariableManager;
import parser.AbstractParser;

public abstract class OneParameterOperationCommand implements AbstractCommand{

	protected double myMagnitude;
	protected VariableManager myVariableManager;
	
	/*
	 * Must call setVariableManager before setMagnitude
	 */
	public void setMagnitude(String magnitude) throws UndefinedVariableException {
		myMagnitude = convertMagnitudeToValue(magnitude);
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
