package textPanel;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import StatsPanel.StatsPanel;
import TurtleStuff.TurtleManager;
import backEnd.SlogoModel;
import backEnd.Managers.LanguageManager;

public class InputPanel extends JPanel {
SlogoModel model;
ResourceBundle myResources;
TurtleManager manager;
LanguageManager language;
JTextArea textInput;
List<JTextArea> savedBoxes=new ArrayList<JTextArea>();
public InputPanel(SlogoModel modeler, ResourceBundle res, TurtleManager manage, LanguageManager lang){
	super();
	model=modeler;
	myResources=res;
	manager=manage;
	language=lang;
	makeInputPanel(this);
}
private void makeInputPanel(JPanel homePanel){
	JPanel panel=new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	panel.add(new SavePanel(model,myResources, savedBoxes));
	JPanel smallPanel=new JPanel();
	panel.add(new CommandPanel(myResources, model, manager, language,savedBoxes));
	homePanel.add(panel);
}
}
