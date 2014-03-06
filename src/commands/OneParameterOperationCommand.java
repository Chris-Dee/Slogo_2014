package commands;

import backEnd.VariableManager;
import parser.AbstractParser;

public abstract class OneParameterOperationCommand implements AbstractCommand{

	protected double myMagnitude;
	protected VariableManager myVariableManager;
	
	/*
	 * Must set setVariableManager before setMagnitude
	 */
	public void setMagnitude(String magnitude) {
		myMagnitude = convertMagnitudeToValue(magnitude);
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
