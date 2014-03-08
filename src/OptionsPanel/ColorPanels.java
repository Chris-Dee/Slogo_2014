package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import PreferenceManagers.ColorManager;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleManager;

import frontEnd.SlogoView;

@SuppressWarnings("serial")
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
private void makeButton(JPanel homePanel){
	Button loader=new Button(myResources.getString("LoadColor"));
	loader.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser chooser=new JFileChooser();
			if(chooser.showSaveDialog(SlogoView.viewStats())==JFileChooser.APPROVE_OPTION);
			File file=chooser.getSelectedFile();
			(colors).readColorFile(file);
		}
	});
	homePanel.add(loader);
}
	private void makeColorChooserButton(JPanel homePanel){
		final JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		makeButton(panel);
		JPanel smallerPanel=new JPanel();
		JLabel label=new JLabel(myResources.getString("ColorChooserLabel"));
		final JTextField colorChooser = new JTextField(3);
		smallerPanel.add(label);
		smallerPanel.add(colorChooser);
		//System.out.println("ddd");
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
