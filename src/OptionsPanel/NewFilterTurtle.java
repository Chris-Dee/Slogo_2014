package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;

import frontEnd.SlogoView;

public class NewFilterTurtle extends JPanel {
	ResourceBundle myResources;
	TurtleDrawer TurtleSpace;
	TurtleManager manager;
public NewFilterTurtle(ResourceBundle myRes,TurtleDrawer TurtSpace, TurtleManager manage){
	super();
	manager=manage;
	myResources=myRes;
	TurtleSpace=TurtSpace;
	makePanel();
}

	private void makeFilterPanel(JPanel homePanel){
		JPanel filterPanel=new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
		Button filterButton=new Button(myResources.getString("FilterTurtle"));
		//rotationPanel.setLayout(new BoxLayout(rotationPanel,BoxLayout.Y_AXIS));
		final JTextField filterInput=new JTextField(3);
		filterInput.setText("0");
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{             

				manager.setFilter(Integer.parseInt(filterInput.getText()));
			}
		});   
		filterPanel.add(filterButton);
		filterPanel.add(filterInput);
		homePanel.add(filterPanel);
	}
	public void makeNewTurtleButton(JPanel homePanel){
		final JPanel homePane=homePanel;
		Button newTurtButton = new Button(myResources.getString("NewTurtle"));
		JPanel newPanel=new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel,BoxLayout.Y_AXIS));
		JPanel labelPanel=new JPanel();
		JLabel idToAdd=new JLabel("Id of turtle");
		final JTextField turtId=new JTextField(2);
		turtId.setText("0");
		labelPanel.add(idToAdd);
		labelPanel.add(turtId);
		newTurtButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				SlogoView.updateInfo();
				try{
				TurtleSpace.addnewTurtle(Integer.parseInt(turtId.getText()));
				}catch(Exception e1){
					SlogoView.showError(homePane,myResources.getString("MustBeInt"));
				}
			}
		});
		newPanel.add(newTurtButton);
		newPanel.add(labelPanel);
		homePanel.add(newPanel);
	}
	public void makePanel(){
		makeNewTurtleButton(this);
		makeFilterPanel(this);
	}
}
