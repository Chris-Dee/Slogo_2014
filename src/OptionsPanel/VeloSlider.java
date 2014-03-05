package OptionsPanel;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;


public class VeloSlider extends JPanel {
	ResourceBundle myResources;
	TurtleDrawer TurtleSpace;
	TurtleManager manager;
	public VeloSlider (ResourceBundle myRes,TurtleDrawer TurtSpace, TurtleManager manage){
		super();
		myResources=myRes;
		TurtleSpace=TurtSpace;
		manager=manage;
		makePanel();
	}
	public void makeScroller(JPanel homePanel){
		JPanel scrollPanel=new JPanel();
		scrollPanel.setLayout(new BoxLayout(scrollPanel,BoxLayout.Y_AXIS));
		
		scrollPanel.add(new JLabel(myResources.getString("VelocitySlider")));
		JSlider veloSlider=new JSlider(JSlider.HORIZONTAL, 1,10,5);
		veloSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				manager.setVelocities(((JSlider)arg0.getSource()).getValue()*0.01);
			}
		});
		scrollPanel.add(veloSlider);
		homePanel.add(scrollPanel);
	}
public void	makePanel(){
	makeScroller(this);
}
}
