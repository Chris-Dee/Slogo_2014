package frontEnd;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.List;
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
	int workspaceno =0;
	HashMap<Integer , List<Turtle>> tMap= new HashMap<Integer, List<Turtle>>();
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
						
						
						tMap.put(workspaceno, TurtleSpace.getAllTurtles());
						System.out.print(workspaceno);
						System.out.print(TurtleSpace.getAllTurtles().size());
						TurtleSpace.clear();
						String s=workspace.getText();
						workspaceno = Integer.parseInt(s);
						if(!(tMap.containsKey(workspaceno))){
							TurtleSpace.initGame();
						} else{
							System.out.print(workspaceno);
							System.out.print(tMap.get(workspaceno).size());
							TurtleSpace.reAddTurtles(tMap.get(workspaceno));
						}
						
						
						
			}
		});
		
		homePanel.add(workspaceloader);
		homePanel.add(workspace);
	}
	
}
