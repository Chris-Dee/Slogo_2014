package frontEnd;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;

import PreferenceManagers.ImageManager;
import StatsPanel.StatsPanel;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;

public class workspacepanel extends JPanel{
	ResourceBundle myResources;
	TurtleManager manager;
	TurtleDrawer TurtleSpace;
	ImageManager images;
	int i=1;
	public workspacepanel(ResourceBundle myRes,TurtleManager manage,TurtleDrawer turtleSpace){
		super();
		myResources=myRes;
		TurtleSpace=turtleSpace;
		manager=manage;
		makePanel();
		
}
	public void makePanel(){
		makeWorkspacePanel(this);
	}
	
	private void makeWorkspacePanel(JPanel homePanel){
		final JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		makeButton(panel);
		
		homePanel.add(panel);
	}
	
	private void makeButton(final JPanel homePanel){
		Button workspaceloader=new Button("Switch Workspace");
		final JTextField workspace = new JTextField(3);
		workspaceloader.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
						//StatsPanel Stats = SlogoView.viewStats();
						TurtleSpace.clear();
						String s=workspace.getText();
						int workspaceno = Integer.parseInt(s);
						
			}
		});
		
		homePanel.add(workspaceloader);
		homePanel.add(workspace);
	}
	
}
