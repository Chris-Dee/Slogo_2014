package backEnd;

import java.util.ArrayList;
import java.util.List;
import frontEnd.Turtle;
import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;

public class SlogoModel {
	
	private CommandFactory myCommandFactory;
	private AbstractParser myParser;
    private List<String> myHistory;
	
	public SlogoModel(){
		myParser = new TextParser();
		myCommandFactory = new CommandFactory(myParser);
		myHistory = new ArrayList<String>();
	}
	
	/*
	 * This method should be called by the front-end "main" class to pass into the text input
	 */
	public void receiveTextInput(String userCommands, Turtle turtle){
		StringNode root = myParser.parse(userCommands);
		myHistory.add(userCommands);
		if (myCommandFactory.runCommands(root, turtle) == -1){ // error messages
			
		}
	}
	
	public List<String> getHistory(){
		return myHistory;
	}

}
