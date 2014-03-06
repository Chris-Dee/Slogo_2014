package backEnd;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import parser.tree.StringNode;

public class VariableManager {
	
    private Map<String, Double> myVariableMap;
    private ResourceBundle myProgramLanguage;
    
    public VariableManager(){
		myVariableMap = new HashMap<String, Double>();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
    }
    
    public boolean isVariable(String current){
    	return current.startsWith(myProgramLanguage.getString("Variable"));
    }
    
//    public StringNode replaceWithCurrentValues(StringNode root){
//    	loopTree(root);
//    	return root;
//    }
    
    public Map<String, Double> getVariableMap(){
    	return myVariableMap;
    }
    
//    private void loopTree(StringNode current){
//		if(current == null) return;
//		if(isVariable(current)){
//			processNode(current);	
//		}
//		for(StringNode child: current.getChildren()){
//			loopTree(child);
//		}
//    }

    public void setValueToVariable(String v, double value){
    	String variable = deleteVariableSyntax(v);
    	myVariableMap.put(variable, value);
    }
    
    /*
     * Returns the variable name by deleting its Variable syntax (e.g. :)
     */
    private String deleteVariableSyntax(String v){
    	return v.substring(myProgramLanguage.getString("Variable").length());
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
