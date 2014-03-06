package DrawingPanel;

import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import PreferenceManagers.VariableManager;

import com.sun.org.apache.regexp.internal.RESyntaxException;

public class VariableList extends JPanel {
ResourceBundle myResources;
VariableManager myVariableManager;
JTextArea variableList;
public VariableList(ResourceBundle res,VariableManager var){
	myResources=res;
	myVariableManager=var;
	makePanel();
}
public void makePanel(){
	variableLists=new JTextArea(40,10);
	variableLists.setEditable(false);
}
public static
}
