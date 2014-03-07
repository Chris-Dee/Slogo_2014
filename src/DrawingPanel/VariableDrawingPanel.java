package DrawingPanel;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import backEnd.Managers.*;
import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;

public class VariableDrawingPanel extends JPanel {
ResourceBundle myResources;
VariableManager myVars;
JTextArea variableList;
TurtleDrawer TurtleSpace;
TurtleManager manager;

public VariableDrawingPanel(ResourceBundle res, VariableManager vars, 
		TurtleDrawer turtSpace, TurtleManager manage){
	super();
	TurtleSpace=turtSpace;
	myResources=res;
	myVars=vars;
	manager=manage;
	this.add(makeDrawingPanel());
}
	private JPanel makeDrawingPanel(){
		JPanel wholePanel=new JPanel();
		wholePanel.setBackground(new java.awt.Color(0,0,0));
		JPanel drawingPanel=new JPanel();
		JScrollPane scroller=new JScrollPane(drawingPanel);
		drawingPanel.setBackground(new java.awt.Color(200, 200, 200));
		manager.findEngine(TurtleSpace);
		drawingPanel.setBackground(new java.awt.Color(100,100,100));
		drawingPanel.setSize(270,270);
		drawingPanel.setMinimumSize(new Dimension(270,200));
		TurtleSpace.setSize(270,200);
		drawingPanel.add(TurtleSpace);
		wholePanel.add(variableList());
		wholePanel.add(scroller);
		fillVariables();
		return wholePanel;
}
	private JPanel variableList(){
		JPanel variablePanel=new JPanel();
		variablePanel.setBackground(new java.awt.Color(0,0,0));
		variablePanel.setLayout(new BoxLayout(variablePanel,BoxLayout.Y_AXIS));
		variableList=new JTextArea(20,10);
		variableList.setEditable(false);
		variableList.setText(myVars.mapToString("="));
		variablePanel.add(variableList);
		variablePanel.add(saveButton());
		variablePanel.add(loadButton());
		return variablePanel;
	}
	private Button saveButton(){
		Button variable=new Button(myResources.getString("LoadVariable"));
		variable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{  
				JFileChooser chooser=new JFileChooser();
				if(chooser.showSaveDialog(variableList)==JFileChooser.APPROVE_OPTION);
				File file=chooser.getSelectedFile();
				(myVars).readFromFile(file);
			}
		});
		return variable;
	}
	private Button loadButton(){
		Button load=new Button(myResources.getString("SaveVariable"));
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{  
				JFileChooser chooser=new JFileChooser();
				//chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnCal=chooser.showSaveDialog(variableList);
				File file=chooser.getSelectedFile();
				try {
					BufferedWriter saver=new BufferedWriter(new FileWriter(file));
					saver.write(myVars.mapToString(" "));
					saver.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		return load;
	}
	public void fillVariables(){
		System.out.println(myVars);
		variableList.setText(myVars.mapToString("="));
	}
}