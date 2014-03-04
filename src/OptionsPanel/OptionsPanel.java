package OptionsPanel;

import java.awt.Dimension;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTextArea;


import TurtleStuff.TurtleDrawer;
import backEnd.SlogoModel;

public class OptionsPanel extends JPanel {
	ResourceBundle myResources;
	TurtleDrawer turtleSpace;
	SlogoModel model;
	int backNumber;
	JTextArea textInput;
	List<JTextArea> savedBoxes;
public OptionsPanel(ResourceBundle myRes,TurtleDrawer turtSpace,SlogoModel slogModel,
		int backNum,JTextArea input, List<JTextArea> savedBox){
	super();
	myResources=myRes;
	turtleSpace=turtSpace;
	model=slogModel;
	backNumber=backNum;
	textInput=input;
	savedBoxes=savedBox;
	makeOptionsPanel();
}

public void makeOptionsPanel(){
	
	this.add(new VeloSlider(myResources, turtleSpace));
	this.add(new DirectionButtons(myResources, model, turtleSpace));
	this.add(new HelpButton(myResources));
	this.add(new MoveAndRefresh(myResources,turtleSpace, backNumber,
			textInput, savedBoxes));
	this.add(new ImageChooser(myResources,turtleSpace));
	this.add(new ShapeButton(myResources,turtleSpace));
	this.add(new NewFilterTurtle(myResources,turtleSpace));
	setVisible(true);
	setSize(600,600);
	setMinimumSize(new Dimension(200,200));

}


}
