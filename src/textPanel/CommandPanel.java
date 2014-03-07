package textPanel;

import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import TurtleStuff.TurtleManager;
import backEnd.SlogoModel;
import backEnd.Managers.LanguageManager;

public class CommandPanel extends JPanel {
ResourceBundle myResources;
SlogoModel model;
LanguageManager myLanguageManager;
TurtleManager manager;
List<JTextArea> savedBox;
public CommandPanel(ResourceBundle res, SlogoModel mod, TurtleManager manage, LanguageManager lang,List<JTextArea> savedBoxes){
	super();
	myResources=res;
	model=mod;
	manager=manage;
	myLanguageManager=lang;
	savedBox=savedBoxes;
	makePanel();
}
private void makeCommandPanel(JPanel homePanel){
	homePanel.setLayout(new BoxLayout(homePanel,BoxLayout.Y_AXIS));
	homePanel.add(new LanguageBar(myLanguageManager));
	homePanel.add(new TextInput(myResources, manager,model, savedBox ));
}
private void makePanel(){
	makeCommandPanel(this);
}
}
