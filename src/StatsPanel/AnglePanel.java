package StatsPanel;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AnglePanel extends JPanel {
ResourceBundle myResources;
JTextField angle;
public AnglePanel(JTextField cosntrAngle,ResourceBundle resources){
	super();
	myResources=resources;
	angle=StatsPanel.angle;
	
	makeAnglePanel(this);
}
	private void makeAnglePanel(JPanel dataPanel){
		JPanel anglePanel=new JPanel();
		anglePanel.setLayout(new BoxLayout(anglePanel,BoxLayout.Y_AXIS));
		angle=new JTextField();
		StatsPanel.angle=angle;
		angle.setEditable(false);
		JLabel label=new JLabel(myResources.getString("Rotation"));
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		anglePanel.add(label);
		anglePanel.add(angle);
		angle.setHorizontalAlignment(JTextField.CENTER);
		dataPanel.add(anglePanel);
	}
}
