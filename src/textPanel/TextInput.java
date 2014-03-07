package textPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import backEnd.SlogoModel;

import DrawingPanel.FunctionViewer;
import TurtleStuff.TurtleManager;

import frontEnd.SlogoView;

public class TextInput extends JPanel {
JTextArea textInput;
ResourceBundle myResources;
TurtleManager manager;
SlogoModel model;
List<JTextArea> savedBox;
public TextInput(ResourceBundle res, TurtleManager manage, SlogoModel modeler, List<JTextArea> savedBoxes){
	super();
	savedBox=savedBoxes;
	myResources=res;
	manager=manage;
	model=modeler;
	createTextInput(this);
}
private void createTextInput(JPanel inputTextPanel){
	inputTextPanel.setLayout(new BoxLayout(inputTextPanel,BoxLayout.Y_AXIS));
	textInput=new JTextArea(5,20);
	JScrollPane inputPane=new JScrollPane(textInput);
	textInput.setText("");
	textInput.setSize(100,300);
	Button submit=new Button(myResources.getString("SubmitText"));
	submit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{ 
			try{
				SlogoView.viewStats().setResultsBox(model.receiveTextInput(textInput.getText())+"");
				model.updateHistory(textInput.getText());
				//manager.rotateImage();
				savePanel();
			}
			catch(Exception e1){
				e1.printStackTrace();
				SlogoView.showError(SlogoView.viewStats(),myResources.getString("IllegalCommand"));
			}
			SlogoView.viewStats().updateInfo();
			FunctionViewer.updateViewer();
		}
	});   
	inputTextPanel.add(inputPane);
	inputTextPanel.add(submit);
}
private void savePanel(){
	for(int i=savedBox.size()-1;i>0;i--)
		savedBox.get(i).setText(savedBox.get(i-1).getText());
	savedBox.get(0).setText(textInput.getText());
}

}
