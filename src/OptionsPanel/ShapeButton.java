package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;

import frontEnd.SlogoView;

public class ShapeButton extends JPanel {
	ResourceBundle myResources;
	TurtleDrawer TurtleSpace;
	TurtleManager manager;
	public ShapeButton(ResourceBundle myRes, TurtleDrawer TurtSpace, TurtleManager manage){
		super();
		myResources=myRes;
		TurtleSpace=TurtSpace;
		makePanel();
	}
	public void makeSunButton(JPanel homePanel){
		Button sunButton=new Button(myResources.getString("FillDrawer"));
		sunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{      
				SlogoView.updateInfo();
				
				manager.addRotations(45);
				for(int k=0;k<3;k++){
					manager.addRotations(90);
					manager.moveForward(30);
				}
				manager.rotateImage();
				}
			
		});  
		homePanel.add(sunButton);
	}
	public void makePanel(){
		makeSunButton(this);
	}
}
