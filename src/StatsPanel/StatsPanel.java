package StatsPanel;

import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import TurtleStuff.Stats;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleDrawer;

public class StatsPanel extends JPanel {
	static JTextField xPos;
	static JTextField yPos ;
	static JTextField xTarget;
	static JTextField yTarget;
	static JTextField results;
	static JTextField angle;
	ResourceBundle myResources;
	private static TurtleDrawer TurtleSpace;
	public StatsPanel(ResourceBundle resources,TurtleDrawer turtSpace ){
		super();
		myResources=resources;
		TurtleSpace=turtSpace;
		makeStatsPanel();
	}
	public void setResultsBox(String s){
		results.setText(s);
	}
	public void makeStatsPanel(){
		JPanel dataPanel=new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel,BoxLayout.Y_AXIS));
		dataPanel.setBackground(new java.awt.Color(100,100,100));
		dataPanel.add(new ResultPanel(myResources,results));
		dataPanel.add(new PositionPanel(myResources,xPos,yPos));
		dataPanel.add(new TargetPanel(myResources,xTarget,yTarget));
		dataPanel.add(new AnglePanel(angle,myResources));
		add(dataPanel);
		this.add(dataPanel);
		}
	public void updateInfo(){
		DecimalFormat decFor=new DecimalFormat("0.000");
		Stats s=TurtleSpace.displayStats();
		xPos.setText(decFor.format(s.getPos().xPos()-Turtle.TURTLE_INIT_X)+"");
		yPos.setText(decFor.format(s.getPos().yPos()-Turtle.TURTLE_INIT_Y)+"");
		yTarget.setText(s.getTarget().yPos()-Turtle.TURTLE_INIT_X+"");
		xTarget.setText(s.getTarget().xPos()-Turtle.TURTLE_INIT_Y+"");
		angle.setText(decFor.format(s.getRot()%360)+"");
	}
}
