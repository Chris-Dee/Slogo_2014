package backEnd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TurtleStuff.Turtle;
import TurtleStuff.TurtleManager;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import frontEnd.SlogoView;
import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;

public class SlogoModel {
	
	private CommandFactory myCommandFactory;
	private AbstractParser myParser;
    private List<String> myHistory;
    private SlogoView myViewer;
    private LanguageManager myLanguageManager;
    private String myLanguage;
    private Map<String, Double> myVariableMap;
    private Map<String, StringNode> myVariableCommandMap;
    private TurtleManager myManager;
    
	
	public SlogoModel(){
		myParser = new TextParser();
		myHistory = new ArrayList<String>();
		myCommandFactory = new CommandFactory();
		myLanguageManager = new LanguageManager();
		myVariableMap = new HashMap<String, Double>();
		myVariableCommandMap = new HashMap<String, StringNode>();
	}
	
	public void setParameters(SlogoView viewer, TurtleManager manager){
		myViewer = viewer;
		myManager=manager;
	}
	
	/*
	 * Set the current language
	 * The first letter of language should be capitalized while the rest should not (e.g. Chinese)
	 */
	public void setLanguage(String language){
		myLanguage = language;
	}
	
	/*
	 * This method should be called by the front-end "main" class to pass into the text input
	 * Receive the list of all turtles on the grid
	 * @return a list of all turtles on the grid
	 */

	public double receiveTextInput(String userCommands, List<Turtle> turtles){
		try{
			StringNode root = myParser.parse(userCommands);
			root = myLanguageManager.convertLanguage(root, myLanguage);
			return myCommandFactory.runCommands(root, turtles.get(0));
		} catch(IllegalCommandException e){
			SlogoView.showError(myViewer.getPanel(), e.getMessage());
		} catch (IllegalParameterException e) {
			SlogoView.showError(myViewer.getPanel(), e.getMessage());
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!");
		return 0; // should not be reached here
	}
//	if(!myParser.checkForErrors()){
//	myViewer.showError(myErrorMessages.getString("IllegalCommand"));
//	return 0;
//}
	
	public List<String> getHistory(){
		return myHistory;
	}
	
	/*
	 * Append the String userCommands to the end of the String list myHistory
	 */
	public void updateHistory(String userCommands){
		myHistory.add(userCommands);
	}
}
