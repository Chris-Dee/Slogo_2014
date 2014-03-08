package commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backEnd.Managers.UserCommandManager;
import backEnd.Managers.VariableManager;
import TurtleStuff.Turtle;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import parser.TextParser;

public abstract class ControlCommand implements AbstractCommand{

	protected TextParser myParser;
	protected String myExpression;
	protected String myCommands;
	protected List<Turtle> myTurtles;
	protected VariableManager myVariableManager;
	protected UserCommandManager myUserCommandManager;
	protected VariableManager myLocalVariableManager;
	
	public ControlCommand(){
		myParser = new TextParser();
		myUserCommandManager = new UserCommandManager();
		myVariableManager = new VariableManager();
		myLocalVariableManager = new VariableManager();
	}
	
	public void setUserCommandManager(UserCommandManager userCommandManager){
		myUserCommandManager = userCommandManager;
		myParser.setUserCommandManager(myUserCommandManager);
	}
	
	public void setVariableManager(VariableManager manager){
		myVariableManager = manager;
		Map<String, Double> lastVCopy = getCopyOfMapFromVariableManager(myVariableManager);
		myLocalVariableManager.setVariableMap(lastVCopy);
	}
	
	public void setExpression(String s){
		myExpression = s;
	}
	
	protected Map<String, Double> getCopyOfMapFromVariableManager(VariableManager manager) {
		Map<String, Double> lastV = manager.getVariableMap();
		Map<String, Double> lastVCopy = new HashMap<String, Double>();
		for(String key: lastV.keySet()){ lastVCopy.put(key, lastV.get(key)); }
		return lastVCopy;
	}
	
	public void setTurtles(List<Turtle> turtles){
		if(turtles != null){
			myTurtles = turtles;
		}
	}
	
	protected void initExecute(){
		myParser.setUserCommandManager(myUserCommandManager);
//		myParser.setVariableManager(myLocalVariableManager);
	}
	
	protected void backToLastVariableSpace(Map<String, Double> lastVCopy) {
		Map<String, Double> newV = myLocalVariableManager.getVariableMap();
		for(String key: newV.keySet()){
			if(!lastVCopy.containsKey(key)){ myLocalVariableManager.removeVariable(key); }
		}
		myVariableManager.setVariableMap(myLocalVariableManager.getVariableMap());
	}
	
	public void setCommands(String s){
		if(s != null) myCommands = s;
	}
	
	public abstract double execute() throws IllegalCommandException, IllegalParameterException;
}
