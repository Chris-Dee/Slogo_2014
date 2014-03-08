package StatsPanel;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TargetPanel extends JPanel {
	ResourceBundle myResources;
	JTextField targetx;
	JTextField targety;
	public TargetPanel(ResourceBundle res, JTextField targx, JTextField targy){
		super();
		myResources=res;
		targetx=targx;
		targety=targy;
		makeTargetPanel(this);
	}

	
	private void makeTargetPanel(JPanel dataPanel){
		JPanel headingPanel=new JPanel();
		JPanel x=new JPanel();
		x.setLayout(new BoxLayout(x,BoxLayout.Y_AXIS));
		JPanel y=new JPanel();
		y.setLayout(new BoxLayout(y,BoxLayout.Y_AXIS));
		targetx=new JTextField(4);
		StatsPanel.xTarget=targetx;
		targetx.setEditable(false);
		JLabel xLabel=new JLabel(myResources.getString("XTarget"));
		//xLabel.setHorizontalAlignment(SwingConstants.CENTER);
		x.add(xLabel);
		x.add(targetx);
		targety=new JTextField(4);
		StatsPanel.yTarget=targety;
		targety.setEditable(false);
		JLabel yLabel= new JLabel(myResources.getString("YTarget"));
		//yLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		y.add(yLabel);
		y.add(targety);
		headingPanel.add(x);
		headingPanel.add(y);
		dataPanel.add(headingPanel);
	}
}
