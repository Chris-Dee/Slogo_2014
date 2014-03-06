package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import PreferenceManagers.ColorManager;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleManager;

import frontEnd.SlogoView;

public class ColorPanels extends JPanel {
	ResourceBundle myResources;
	TurtleManager manager;
	ColorManager colors;
	public ColorPanels(ResourceBundle res, TurtleManager manage,ColorManager color){
		super();		
		myResources=res;
		manager=manage;
		colors=color;
		
		makePanel();
	}

	private void makeColorChooserButton(JPanel homePanel){
		final JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JPanel smallerPanel=new JPanel();
		JLabel label=new JLabel(myResources.getString("ColorChooserLabel"));
		final JTextField colorChooser = new JTextField(3);
		smallerPanel.add(label);
		smallerPanel.add(colorChooser);
		System.out.println("ddd");
		Button selectImage= new Button(myResources.getString("ColorChooser"));
		selectImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				for(Turtle t:manager.getFilteredTurtles()){
					int i=Integer.parseInt(colorChooser.getText());
					t.setPen(i,colors.getColorByIndex(i));
				}
			}
		});
		panel.add(selectImage);
		panel.add(smallerPanel);
		homePanel.add(panel);
	}
	public void makePanel(){
		makeColorChooserButton(this);
	}
}
