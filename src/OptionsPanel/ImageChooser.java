package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frontEnd.SlogoView;
import frontEnd.TurtleDrawer;

public class ImageChooser extends JPanel {
	ResourceBundle myResources;
	TurtleDrawer TurtleSpace;
public ImageChooser(ResourceBundle myRes,TurtleDrawer TurtSpace){
	super();
	myResources=myRes;
	TurtleSpace=TurtSpace;
	makePanel();
}


private void makeImageChooserButton(JPanel homePanel){
	final JPanel panel=new JPanel();
	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	final JTextField imageChooser = new JTextField(10);
	Button selectImage= new Button(myResources.getString("SelectImage"));
	selectImage.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			String imageFile = imageChooser.getText();
			try{
				TurtleSpace.newTurtle(imageFile);
			}
			catch(Exception e1){
				SlogoView.showError(panel,"File was not found");
			}
		}
	});
	panel.add(selectImage);
	panel.add(imageChooser);
	homePanel.add(panel);
}
public void makePanel(){
	makeImageChooserButton(this);
}
}
