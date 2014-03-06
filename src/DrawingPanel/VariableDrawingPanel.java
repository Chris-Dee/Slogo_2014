package DrawingPanel;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import PreferenceManagers.VariableManager;
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
		variablePanel.add(variableList);
		variablePanel.add(variableButton());
		return variablePanel;
	}
	private Button variableButton(){
		Button variable=new Button(myResources.getString("SaveVariable"));
		variable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{  
				
			}
		});
		return variable;
	}
	public void fillVariables(){
		System.out.println(myVars);
		variableList.setText(myVars.mapToString());
	}
}