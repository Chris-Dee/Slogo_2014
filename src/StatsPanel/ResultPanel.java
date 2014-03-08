package StatsPanel;

import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ResultPanel extends JPanel {
 ResourceBundle myResources;
 JTextField results;
public ResultPanel(ResourceBundle res, JTextField result){
	super();
	myResources=res;
	results=result;
	makeResultsPanel(this);
}
 private void makeResultsPanel(JPanel homePanel){
		JPanel resultsPanel=new JPanel();
		resultsPanel.setLayout(new BoxLayout(resultsPanel,BoxLayout.Y_AXIS ));
		resultsPanel.add(new JLabel(myResources.getString("EquationResults")));
		results=new JTextField();
		StatsPanel.results=results;
		results.setEditable(false);
		resultsPanel.add(results);
		homePanel.add(resultsPanel);
	}

}
