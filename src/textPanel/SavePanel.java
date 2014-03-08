package textPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import backEnd.SlogoModel;
import backEnd.Managers.UserCommandManager;
import frontEnd.SlogoView;

@SuppressWarnings("serial")
public class SavePanel extends JPanel{
	private static final int NUM_BOXES=3;
	private SlogoModel model;
	private ResourceBundle myResources;
	private List<JTextArea> savedBoxes=new ArrayList<JTextArea>();
	public SavePanel(SlogoModel modeler, ResourceBundle res, List<JTextArea> saveBox,UserCommandManager ucManager){
		super();
		savedBoxes=saveBox;
		model=modeler;
		myResources=res;
		this.add(makeSavedTextBoxes());
	}
	public JPanel makeSavedTextBoxes(){
		JPanel savePanel=new JPanel();
		savePanel.setLayout(new BoxLayout(savePanel,BoxLayout.Y_AXIS));
		JPanel boxPanel=new JPanel();
		for(int i=0;i<NUM_BOXES;i++){
			JPanel oneBox=new JPanel();
			fillSavedBoxes(oneBox,i);
			boxPanel.add(oneBox);}
		savePanel.add(boxPanel);

		savePanel.setBackground(new java.awt.Color(100,100,100));
		return savePanel;
	}

	private void fillSavedBoxes(JPanel oneBox, final int i){

		oneBox.setLayout(new BoxLayout(oneBox,BoxLayout.Y_AXIS));
		final JTextArea savedText=new JTextArea(20,10);
		JScrollPane scrollSave=new JScrollPane(savedText);
		savedBoxes.add(savedText);
		savedText.setEditable(false);
		JPanel saveFunction=new JPanel();
		Button functionSaver=new Button(myResources.getString("FunctionSave"));
		functionSaver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{       
				
			}
		});   
		Button loader=new Button(myResources.getString("RunScript")+" "+(i+1));
		loader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{       
				SlogoView.viewStats().setResultsBox((model.receiveTextInput(savedBoxes.get(i).getText())+""));
				SlogoView.viewStats().updateInfo();    
			}
		});   
		oneBox.add(saveFunction);
		oneBox.add(loader);
		oneBox.add(scrollSave);
	}

}
