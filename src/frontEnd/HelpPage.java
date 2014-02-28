package frontEnd;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class HelpPage extends JFrame {
	public HelpPage(){
		addToMain();

	}
	/**
	 * @param args
	 */
	public JScrollPane createHelpPage(){
		 JEditorPane helpPage;
	JScrollPane helpPagePane=null;
		try {
			helpPage = new  JEditorPane
					("http://www.cs.duke.edu/courses/compsci308/current/assign/03_slogo/commands.php");
			helpPage.setEditable(false);
			helpPage.setVisible(true);
			JScrollPane helpPanel=new JScrollPane(helpPage,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			helpPagePane=helpPanel;
					
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Not connected to the internet");
		}

		return helpPagePane;
	}
	public void addToMain(){
		JPanel main=(JPanel) getContentPane();
		main.add(createHelpPage());
		pack();
		setMinimumSize(new Dimension(400,600));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelpPage h=new HelpPage();
		h.setVisible(true);
		h.addToMain();
	}

}
