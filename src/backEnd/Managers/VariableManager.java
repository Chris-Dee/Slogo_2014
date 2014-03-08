package backEnd.Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;

public class VariableManager {
	public static final String VARIABLE_PROGRAM_SYNTAX = "Variable";
	
    private Map<String, Double> myVariableMap; // map variable name (without syntax) to value
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
    
    public void setVariableMap(Map<String, Double> map){
    	myVariableMap = map;
    }

    public void setValueToVariable(String v, double value){
    	System.out.println("setValueToVariable: "+v+" "+value);
    	String variable = deleteVariableSyntax(v);
    	myVariableMap.put(variable, value);
    }
    
    /*
     * Returns the variable name by deleting its Variable syntax (e.g. :)
     */
    private String deleteVariableSyntax(String v){
    	System.out.println("deleteVariableSyntax called: "+v);
    	System.out.println("substring index: "+myProgramLanguage.getString(VARIABLE_PROGRAM_SYNTAX).length());
    	System.out.println("v length: "+v.length() +" "+v);
    	
    	if(v.contains(myProgramLanguage.getString(VARIABLE_PROGRAM_SYNTAX))){
        	v = v.substring(myProgramLanguage.getString(VARIABLE_PROGRAM_SYNTAX).length());	
    	}
    	System.out.println("deleteVariableSyntax Done: "+v);
    	return v;
    }
    
    public void removeVariable(String v){
    	System.out.println("removeVariable: "+v);
    	if(v.startsWith(myProgramLanguage.getString(VARIABLE_PROGRAM_SYNTAX))){
        	v = deleteVariableSyntax(v);
    	}
    	for(String s: myVariableMap.keySet()){
    		System.out.print("key: "+s+" ");
    		System.out.println();
    	}
    	myVariableMap.remove(v);
    }
    
    /*
     * Return the value of the variable v
     * If v is not initialized, return null
     */
    public double getValueOfVariable(String v){
    	if(v.startsWith(myProgramLanguage.getString(VARIABLE_PROGRAM_SYNTAX))){
        	String variable = deleteVariableSyntax(v);	
        	return myVariableMap.get(variable);
    	}
    	return myVariableMap.get(v);
    }
    
    public void readFromFile(File file) throws FileNotFoundException{
    	myVariableMap=new HashMap<String,Double>();
    	try {
			Scanner scanner=new Scanner(file);
			while(scanner.hasNext()){
			myVariableMap.put(scanner.next(),scanner.nextDouble());
			if(scanner.hasNext())
				scanner.nextLine();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException();
		}
    }
    
    public String mapToString(String separatingCharacter){
    	String result="";
    	for(String s:myVariableMap.keySet()){
    		result+=(s.substring(1)+separatingCharacter+getValueOfVariable(s)+"\n");
    	}
    	return result;
    }
}
