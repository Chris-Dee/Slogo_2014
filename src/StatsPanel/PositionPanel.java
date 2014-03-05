package StatsPanel;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PositionPanel extends JPanel {
ResourceBundle myResources;
JTextField xPos;
JTextField yPos;
public PositionPanel(ResourceBundle res, JTextField xpos, JTextField ypos){
	super();
	myResources=res;
	xPos=xpos;
	yPos=ypos;
	makePosPanel(this);
}
	
	private void makePosPanel(JPanel dataPanel){
		JPanel posPanel=new JPanel();
		JPanel xPanel=new JPanel();
		xPanel.setLayout(new BoxLayout(xPanel,BoxLayout.Y_AXIS));
		JPanel yPanel=new JPanel();
		yPanel.setLayout(new BoxLayout(yPanel,BoxLayout.Y_AXIS));
		xPos=new JTextField(4);
		StatsPanel.xPos=xPos;
		xPos.setEditable(false);
		yPos=new JTextField(4);
		StatsPanel.yPos=yPos;
		yPos.setEditable(false);
		JLabel xLabel=new JLabel(myResources.getString("Xposition"));
		xPanel.add(xLabel);
		xPanel.add(xPos);
		yPanel.add(new JLabel(myResources.getString("Yposition")));
		yPanel.add(yPos);
		posPanel.add(xPanel);
		posPanel.add(yPanel);
		dataPanel.add(posPanel);

	}
}
