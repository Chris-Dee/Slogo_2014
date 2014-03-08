package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import jgame.platform.JGEngine;

import PreferenceManagers.ImageManager;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;

import frontEnd.SlogoView;

public class ImageChooser extends JPanel {
	ResourceBundle myResources;
	TurtleManager manager;
	TurtleDrawer TurtleSpace;
	ImageManager images;
public ImageChooser(ResourceBundle myRes,TurtleManager manage,TurtleDrawer turtleSpace, ImageManager image){
	super();
	myResources=myRes;
	TurtleSpace=turtleSpace;
	manager=manage;
	images=image;
	makePanel();
}

private void makeButton(JPanel homePanel){
	Button loader=new Button(myResources.getString("LoadColor"));
	loader.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser=new JFileChooser();
			if(chooser.showSaveDialog(SlogoView.viewStats())==JFileChooser.APPROVE_OPTION);
			File file=chooser.getSelectedFile();
			(images).readImageFile(file);
		}
	});
	homePanel.add(loader);
}
private void makeImageChooserButton(JPanel homePanel){
	final JPanel panel=new JPanel();
	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	makeButton(panel);
	JPanel smallerPanel=new JPanel();
	JLabel label=new JLabel(myResources.getString("ImageChooserLabel"));
	final JTextField imageChooser = new JTextField(3);
	smallerPanel.add(label);
	smallerPanel.add(imageChooser);
	
	Button selectImage= new Button(myResources.getString("ImageChooser"));
	selectImage.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			String s=imageChooser.getText();
			
			TurtleSpace.defineImage(s, "null",0,images.getPathByIndex(Integer.parseInt(s)), "-");
			for(Turtle t:manager.getFilteredTurtles()){
				t.setImage(s);
				t.setImageID(Integer.parseInt(s));
			}
		}
	});
	panel.add(selectImage);
	panel.add(smallerPanel);
	homePanel.add(panel);
}


public void makePanel(){
	makeImageChooserButton(this);
}
}
