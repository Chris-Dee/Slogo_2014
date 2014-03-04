package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import TurtleStuff.TurtleDrawer;

import frontEnd.SlogoView;

public class ShapeButton extends JPanel {
	ResourceBundle myResources;
	TurtleDrawer TurtleSpace;
	public ShapeButton(ResourceBundle myRes, TurtleDrawer TurtSpace){
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
				
				TurtleSpace.addRotations(45);
				for(int k=0;k<3;k++){
					TurtleSpace.addRotations(90);
					TurtleSpace.moveForward(30);
				}
				TurtleSpace.rotateImage();
				}
			
		});  
		homePanel.add(sunButton);
	}
	public void makePanel(){
		makeSunButton(this);
	}
}
