package backEnd;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class VariableManager {
	public static final String VARIABLE_PROGRAM_SYNTAX = "Variable";
	
    private Map<String, Double> myVariableMap;
    private ResourceBundle myProgramLanguage;
    
    public VariableManager(){
		myVariableMap = new HashMap<String, Double>();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
    }
    
    public boolean isVariable(String current){
    	return current.startsWith(myProgramLanguage.getString(VARIABLE_PROGRAM_SYNTAX));
    }
    
    public Map<String, Double> getVariableMap(){
    	return myVariableMap;
    }

    public void setValueToVariable(String v, double value){
    	String variable = deleteVariableSyntax(v);
    	myVariableMap.put(variable, value);
    }
    
    /*
     * Returns the variable name by deleting its Variable syntax (e.g. :)
     */
    private String deleteVariableSyntax(String v){
    	return v.substring(myProgramLanguage.getString(VARIABLE_PROGRAM_SYNTAX).length());
    }
    
    public void removeVariable(String v){
    	myVariableMap.remove(v);
    }
    
    /*
     * Return the value of the variable v
     * If v is not initialized, return null
     */
    public double getValueOfVariable(String v){
    	String variable = deleteVariableSyntax(v);
    	return myVariableMap.get(variable);
    }
}
