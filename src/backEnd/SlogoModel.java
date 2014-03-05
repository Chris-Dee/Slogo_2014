package backEnd;

import java.util.ArrayList;
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
    private Map<String, StringNode> myVariableCommandMap;
    private TurtleManager myManager;

    
	
	public SlogoModel(){
		myParser = new TextParser();
		myHistory = new ArrayList<String>();
		myCommandFactory = new CommandFactory();
		myLanguageManager = new LanguageManager();
//		myVariableCommandMap = new HashMap<String, StringNode>();
		myParser.setLanguageManager(myLanguageManager);
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
		myLanguageManager.setLanguage(language);
		myParser.setLanguageManager(myLanguageManager);
	}
	
	/*
	 * This method should be called by the front-end "main" class to pass into the text input
	 * Receive the list of all turtles on the grid
	 * @return a list of all turtles on the grid
	 */

	public double receiveTextInput(String userCommands, List<Turtle> turtles){
		try{
			List<StringNode> roots = myParser.parse(userCommands);
			return myCommandFactory.runCommands(roots, turtles.get(0));
		} catch(IllegalCommandException e){
			SlogoView.showError(myViewer.getPanel(), e.getMessage());
		} catch (IllegalParameterException e) {
			SlogoView.showError(myViewer.getPanel(), e.getMessage());
		}
		System.out.println("There is something wrong!!!!!!!!!!");
		return 0; // should not be reached here
	}
	
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
