package backEnd;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import parser.tree.StringNode;

public class VariableManager {
	
    protected Map<String, Double> myVariableMap;
    protected ResourceBundle myProgramLanguage;
    
    public VariableManager(){
		myVariableMap = new HashMap<String, Double>();
		myProgramLanguage = ResourceBundle.getBundle(LanguageManager.DEFAULT_LANGUAGE_PACKAGE + LanguageManager.DEFAULT_PROGRAM_LANGUAGE);
    }
    
    public boolean isVariable(StringNode current){
    	return current.getCommandString().startsWith(myProgramLanguage.getString("Variable"));
    }
    
    public StringNode replaceWithCurrentValues(StringNode root){
    	loopTree(root);
    	return root;
    }
    
    protected void loopTree(StringNode current){
		if(current == null) return;
		if(isVariable(current)){
			processNode(current);	
		}
		for(StringNode child: current.getChildren()){
			loopTree(child);
		}
    }

    public void setValueToVariable(String variable, double value){
    	myVariableMap.put(variable, value);
    }
    
    protected void processNode(StringNode current){
    	
    }
}
