package OptionsPanel;

import java.awt.Dimension;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTextArea;


import PreferenceManagers.ColorManager;
import PreferenceManagers.ImageManager;
import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;
import backEnd.SlogoModel;

public class OptionsPanel extends JPanel {
	ResourceBundle myResources;
	TurtleDrawer turtleSpace;
	SlogoModel model;
	int backNumber;
	JTextArea textInput;
	TurtleManager myManager;
	List<JTextArea> savedBoxes;
	ColorManager color;
	ImageManager images;
public OptionsPanel(ResourceBundle myRes,TurtleDrawer turtSpace,SlogoModel slogModel,
		int backNum,JTextArea input, List<JTextArea> savedBox, TurtleManager manage, ColorManager colors, ImageManager image){
	super();
	color=colors;
	myResources=myRes;
	turtleSpace=turtSpace;
	model=slogModel;
	backNumber=backNum;
	textInput=input;
	savedBoxes=savedBox;
	myManager=manage;
	images=image;
	makeOptionsPanel();
}

public void makeOptionsPanel(){
	
	this.add(new VeloSlider(myResources, turtleSpace, myManager));
	this.add(new ColorPanels(myResources, myManager, color));
	this.add(new ImageChooser(myResources,myManager, turtleSpace, images));
	this.add(new DirectionButtons(myResources, model, turtleSpace, myManager));
	this.add(new HelpButton(myResources));
	this.add(new MoveAndRefresh(myResources,turtleSpace, backNumber,
			textInput, savedBoxes, myManager));
	//this.add(new ShapeButton(myResources,turtleSpace, myManager));
	this.add(new NewFilterTurtle(myResources,turtleSpace, myManager));
	setVisible(true);
	setSize(600,600);
	setMinimumSize(new Dimension(200,200));

}


}
