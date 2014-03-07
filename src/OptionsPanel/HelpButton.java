package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import frontEnd.HelpPage;

public class HelpButton extends JPanel {
	HelpPage helpPage;
	ResourceBundle myResources;
	public HelpButton(ResourceBundle myRes){
		super();
		myResources=myRes;
		makePanel();
	}
	
	public void makeHelpButton(JPanel homePanel){
		Button helpButton=new Button(myResources.getString("HelpButton"));
		boolean page=true;
		if(helpPage!=null)
			page=helpPage.isVisible();
		final boolean finalPage=page;
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{     
				helpPage=new HelpPage();
				helpPage.setVisible(finalPage);
			}
		});   
		homePanel.add(helpButton);
	}
	public void makePanel(){
		makeHelpButton(this);
	};
}
