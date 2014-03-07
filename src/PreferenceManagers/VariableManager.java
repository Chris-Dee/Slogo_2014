package PreferenceManagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

import backEnd.Managers.LanguageManager;

public class VariableManager {
	public static final String VARIABLE_PROGRAM_SYNTAX = "Variable";
	
    private Map<String, Double> myVariableMap;
    private ResourceBundle myProgramLanguage;
    private static final String DEFAULT_VARPATH="src/PreferenceManagers/VariablePrefs";
    
    public VariableManager(){
		myVariableMap = new HashMap<String, Double>();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
		readFromFile(new File(DEFAULT_VARPATH));
		System.out.println(myVariableMap);
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
    	if(v.contains(VARIABLE_PROGRAM_SYNTAX))
    	return v.substring(myProgramLanguage.getString(VARIABLE_PROGRAM_SYNTAX).length());
    	return v;
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
    	System.out.println(variable);
    	return myVariableMap.get(variable);
    }
    public void readFromFile(File file){
    	myVariableMap=new HashMap<String,Double>();
    	try {
			Scanner scanner=new Scanner(file);
			while(scanner.hasNext()){
			myVariableMap.put(scanner.next(),scanner.nextDouble());
			System.out.println(myVariableMap);
			if(scanner.hasNext())
				scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public String mapToString(String separatingCharacter){
    	String result="";
    	for(String s:myVariableMap.keySet()){
    		result+=(s+separatingCharacter+getValueOfVariable(s)+"\n");
    	}
    	return result;
    }
}
