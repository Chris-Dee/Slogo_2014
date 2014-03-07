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
import backEnd.Managers.UserCommandManager;

public class InputPanel extends JPanel {
SlogoModel model;
ResourceBundle myResources;
TurtleManager manager;
LanguageManager language;
JTextArea textInput;
List<JTextArea> savedBoxes=new ArrayList<JTextArea>();
UserCommandManager ucManager;
public InputPanel(SlogoModel modeler, ResourceBundle res, TurtleManager manage, LanguageManager lang, UserCommandManager ucManage){
	super();
	ucManager=ucManage;
	model=modeler;
	myResources=res;
	manager=manage;
	language=lang;
	makeInputPanel(this);
}
private void makeInputPanel(JPanel homePanel){
	JPanel panel=new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	panel.add(new SavePanel(model,myResources, savedBoxes, ucManager));
	JPanel smallPanel=new JPanel();
	panel.add(new CommandPanel(myResources, model, manager, language,savedBoxes));
	homePanel.add(panel);
}
}
