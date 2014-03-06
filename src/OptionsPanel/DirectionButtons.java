package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JPanel;


import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;
import backEnd.SlogoModel;

public class DirectionButtons extends JPanel {
	Button backButton;
	Button forwardButton;
	int backNumber=0;
	ResourceBundle myResources;
	TurtleDrawer TurtleSpace;
	SlogoModel model;
	TurtleManager manager;
public DirectionButtons(ResourceBundle myRes,SlogoModel slogoMod, TurtleDrawer TurtSpace, TurtleManager manage){
	super();
	manager=manage;
	myResources=myRes;
	TurtleSpace=TurtSpace;
	model=slogoMod;
	makePanel();
}
public void makePanel(){
	createDirectionButtons(this);
}
public void createDirectionButtons(JPanel homePanel){
	backButton=new Button(myResources.getString("BackButton"));
	backButton.setEnabled(false);
	backButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{	
			//TurtleSpace.refresh();
			System.out.println(model.getHistory().get(0));
			List<String> histList=model.getHistory();
			for(int i=0;i<(histList.size()-(backNumber+1));i++){
				//System.out.println(i+"   "+(histList.size()-(backNumber-1)));
			model.receiveTextInput(histList.get(i));
			model.getHistory().remove(model.getHistory().size()-1);
			System.out.println(histList.get(0));
			}
			backNumber++;
			enableBackForward();
		}
	});
	
	forwardButton=new Button(myResources.getString("ForwardButton"));
	forwardButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			backNumber--;
			List<String> histList=model.getHistory();
			for(int i=0;i<histList.size()-(1+backNumber);i++)
			model.receiveTextInput(histList.get(i));
		enableBackForward();
		}
	});
	forwardButton.setEnabled(false);
	homePanel.add(forwardButton);
	homePanel.add(backButton);
}
private void enableBackForward(){
	System.out.println(backNumber+"   "+model.getHistory().size());
		if(backNumber<=0){
			forwardButton.setEnabled(false);
		backButton.setEnabled(true);
		}
		else if(backNumber>=model.getHistory().size()){
			backButton.setEnabled(false);
		forwardButton.setEnabled(true);
		}
		else{
			backButton.setEnabled(true);
			forwardButton.setEnabled(true);
		}	
	}

}
