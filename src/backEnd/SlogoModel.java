package backEnd;

import java.util.ArrayList;
import java.util.List;

import backEnd.Managers.LanguageManager;
import backEnd.Managers.UserCommandManager;
import backEnd.Managers.VariableManager;
import TurtleStuff.TurtleManager;
import exception.IllegalCommandException;
import exception.IllegalParameterException;
import exception.UndefinedVariableException;
import factories.CommandFactory;
import frontEnd.SlogoView;
import parser.TextParser;
import parser.tree.StringNode;

public class SlogoModel {
	
	private CommandFactory myCommandFactory;
	private TextParser myParser;
    private List<String> myHistory;
    private SlogoView myViewer;
    private LanguageManager myLanguageManager;
    private TurtleManager myTurtleManager;

    
	
	public SlogoModel(){
		myParser = new TextParser();
		myHistory = new ArrayList<String>();
		myCommandFactory = new CommandFactory();
	}
	
	public void setLanguageManager(LanguageManager manager){
		myLanguageManager = manager;
		myParser.setLanguageManager(myLanguageManager);
	}
	
	public void setViewer(SlogoView viewer){
		myViewer = viewer;
	}
	
	public void setTurtleManager(TurtleManager manager){
		myTurtleManager=manager;
		myCommandFactory.setTurtleManager(myTurtleManager);
	}
	
	/*
	 * Set the current language
	 * The first letter of language should be capitalized while the rest should not (e.g. Chinese)
	 */
	public void setLanguage(String language){
		myLanguageManager.setLanguage(language);
		myParser.setLanguageManager(myLanguageManager);
	}
	
	public void setVariableManager(VariableManager manager){
		myCommandFactory.setVariableManager(manager);
	}
	
	public void setUserCommandManager(UserCommandManager manager){
		myCommandFactory.setUserCommandManager(manager);
		myParser.setUserCommandManager(manager);
	}
	
	/*
	 * This method should be called by the front-end "main" class to pass into the text input
	 * Receive the list of all turtles on the grid
	 */

	public double receiveTextInput(String userCommands){
		try{
			List<StringNode> roots = myParser.parse(userCommands);
			double answer = myCommandFactory.runCommands(roots, myTurtleManager.getFilteredTurtles());
			return answer;
		} catch(IllegalCommandException e){
			e.printStackTrace();
			SlogoView.showError(SlogoView.viewStats(), e.getMessage());
		} catch (IllegalParameterException e) {
			e.printStackTrace();
			SlogoView.showError(SlogoView.viewStats(), e.getMessage());
		} catch( UndefinedVariableException e){
			e.printStackTrace();
			SlogoView.showError(SlogoView.viewStats(), e.getMessage());
		} catch(Exception e){
			e.printStackTrace();
			SlogoView.showError(SlogoView.viewStats(), e.getMessage());
		}
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
