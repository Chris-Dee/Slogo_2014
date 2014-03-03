package backEnd;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import frontEnd.SlogoView;
import frontEnd.Turtle;
import parser.AbstractParser;
import parser.TextParser;
import parser.tree.StringNode;

public class SlogoModel {
	
	private CommandFactory myCommandFactory;
	private AbstractParser myParser;
    private List<String> myHistory;
    private SlogoView myViewer;
    private ResourceBundle myErrorMessages;
	
	public SlogoModel(){
		myParser = new TextParser();
		myHistory = new ArrayList<String>();
		myCommandFactory = new CommandFactory();
		myErrorMessages = ResourceBundle.getBundle(SlogoView.DEFAULT_RESOURCE_PATH + SlogoView.DEFAULT_BUTTON_TEXT);
	}
	
	public void setViewer(SlogoView viewer){
		myViewer = viewer;
	}
	
	/*
	 * This method should be called by the front-end "main" class to pass into the text input
	 */
	public double receiveTextInput(String userCommands, Turtle turtle){
//		System.out.println("userCommands passed in SLogoModel: "+userCommands);
//		System.out.println();
		StringNode root = myParser.parse(userCommands);
		if(!myParser.checkForErrors()){

			myViewer.showError(myErrorMessages.getString("IllegalCommand"));
			return 0;
		}
//		System.out.println("Pass Legality Check");
//		//System.out.println("root data: "+root.getCommandString());
//		//System.out.println("root child data: "+root.getChildren().get(0).getCommandString());
		return myCommandFactory.runCommands(root, turtle);
	}
	
	public List<String> getHistory(){
		return myHistory;
	}
	
	public void updateHistory(List<String> history){
		myHistory = history;
	}

}
