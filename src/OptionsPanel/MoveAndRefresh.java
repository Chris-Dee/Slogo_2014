package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import frontEnd.SlogoView;
import frontEnd.TurtleDrawer;

public class MoveAndRefresh extends JPanel {
	ResourceBundle myResources;
	TurtleDrawer TurtleSpace;
	JTextArea textInput;
	int backNumber;
	List<JTextArea> savedBoxes;
public MoveAndRefresh(ResourceBundle myRes,TurtleDrawer TurtSpace, int backNum,
		JTextArea input,List<JTextArea> savedBox){
	super();
	myResources=myRes;
	TurtleSpace=TurtSpace;
	backNumber=backNum;
	textInput=input;
	savedBoxes=savedBox;
	makePanel();
}
public void makeRefreshButton(JPanel homePanel){
	Button refresh=new Button(myResources.getString("Refresh"));
	refresh.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{             
			//write a static method 'clear'?
			TurtleSpace.refresh();
			textInput.setText("");
			for(JTextArea text:savedBoxes)
				text.setText("");
			TurtleSpace.refresh();
		}
	});   
	homePanel.add(refresh);
}
private void makeForwardPanel(JPanel forwardPanel){
	final JPanel forPanel=new JPanel();
	forPanel.setLayout(new BoxLayout(forPanel,BoxLayout.Y_AXIS));
	
	Button forwardButton=new Button(myResources.getString("MoveForward"));
	final JTextField forwardInput=new JTextField(5);
	forwardButton.addActionListener(new ActionListener() {

		public void actionPerformed(ActionEvent e)
		{              
			try{
				TurtleSpace.moveForward(Integer.parseInt(forwardInput.getText()));
				SlogoView.updateInfo();
				backNumber++;
			}catch(Exception e1){
				SlogoView.showError(forPanel,myResources.getString("NumberFormat"));}
		}
	});   
	forPanel.add(forwardButton);
	forPanel.add(forwardInput);
	forwardPanel.add(forPanel);
}
private void makeRotatePanel(JPanel rotationPanel){
	final JPanel rotatePanel=new JPanel();
	rotatePanel.setLayout(new BoxLayout(rotatePanel, BoxLayout.Y_AXIS));
	Button rotationButton=new Button(myResources.getString("RotateTurtle"));
	//rotationPanel.setLayout(new BoxLayout(rotationPanel,BoxLayout.Y_AXIS));
	final JTextField rotationInput=new JTextField(4);
	rotationButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{             
			try{
				TurtleSpace.addRotations(Double.parseDouble(rotationInput.getText()));
			}catch(Exception e1){
				SlogoView.showError(rotatePanel,myResources.getString("NumberFormat"));
				backNumber++;
			}
			TurtleSpace.rotateImage();
			SlogoView.updateInfo();
		}
	});   
	rotatePanel.add(rotationButton);
	rotatePanel.add(rotationInput);
	rotationPanel.add(rotatePanel);
}
public void makePanel(){
	makeRefreshButton(this);
	makeRotatePanel(this);
	makeForwardPanel(this);
}
}
