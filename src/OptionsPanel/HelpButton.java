package OptionsPanel;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import frontEnd.HelpPage;

public class HelpButton extends JPanel {
	HelpPage helpPage;
	ResourceBundle myResources;
	public static final String DEFAULT_BASIC_COMMANDS_URL="http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php";
	public static final String DEFAULT_ADV_COMMANDS_URL="http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands2.php";
	public HelpButton(ResourceBundle myRes){
		super();
		myResources=myRes;
		makePanel();
	}
	
	public void makeHelpButton(JPanel homePanel, String res, String url){
		homePanel.setLayout(new BoxLayout(homePanel,BoxLayout.Y_AXIS));
		
		Button helpButton=new Button(myResources.getString(res));
		boolean page=true;
		if(helpPage!=null)
			page=helpPage.isVisible();
		final boolean finalPage=page;
		final String u=url;
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{     
				helpPage=new HelpPage(u);
				helpPage.setVisible(finalPage);
			}
		});   
		homePanel.add(helpButton);
	}
	public void makePanel(){
		makeHelpButton(this, "BasicHelpButton",DEFAULT_BASIC_COMMANDS_URL);
		makeHelpButton(this,"AdvHelpButton",DEFAULT_ADV_COMMANDS_URL);
	};
}
