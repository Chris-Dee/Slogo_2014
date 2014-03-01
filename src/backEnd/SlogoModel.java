package backEnd;

import java.util.ArrayList;
import java.util.List;

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
	
	public SlogoModel(){
		myParser = new TextParser();
		myHistory = new ArrayList<String>();
		myCommandFactory = new CommandFactory();
	}
	
	public void setViewer(SlogoView viewer){
		myViewer = viewer;
	}
	
	/*
	 * This method should be called by the front-end "main" class to pass into the text input
	 */
	public double receiveTextInput(String userCommands, Turtle turtle){
		myHistory.add(userCommands);
		//System.out.println("userCommands passed in SLogoModel: "+userCommands);
//		System.out.println();
//		System.out.println();
//		System.out.println("userCommands passed in SLogoModel: "+userCommands);

		StringNode root = myParser.parse(userCommands);
		if(!myParser.checkForErrors()){
			myViewer.showError(SlogoView.ILLEGAL_COMMAND_MESSAGE);
			return 0;
		}
		//System.out.println("Pass Legality Check");
//		//System.out.println("root data: "+root.getCommandString());
//		//System.out.println("root child data: "+root.getChildren().get(0).getCommandString());
		return myCommandFactory.runCommands(root, turtle);
	}
	
	public List<String> getHistory(){
		return myHistory;
	}

}
