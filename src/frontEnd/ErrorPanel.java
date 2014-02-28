package frontEnd;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ErrorPanel extends JFrame {
	private String string;
public ErrorPanel(String s){
	string=s;
	//init();
	//setUpMainPanel();
}
public static void main(String[] args) {
	ErrorPanel x=new ErrorPanel("tester");
	x.init();
	
}
private void init(){
	setUpMainPanel();
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setVisible(true);
	setSize(300,200);
	setMinimumSize(new Dimension(300,200));
}
private void setUpMainPanel(){
	JPanel mainPanel=(JPanel) getContentPane();
	mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
	//makeOKButton(mainPanel);
	makeOptionPane(mainPanel);
	mainPanel.setSize(300,200);
	mainPanel.setMinimumSize(new Dimension(300,200));
	
}
private void makeOptionPane(JPanel homePanel){
	JOptionPane optionPane=new JOptionPane();
	optionPane.showMessageDialog(homePanel,"Could not find class");
}
private void makeOKButton(JPanel homePanel){
	Button okButton=new Button("OK");
	okButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{     
			setVisible(false);
			dispose();
		}
	});   
	homePanel.add(okButton);
}
private void makeTextWarning(){}
}
