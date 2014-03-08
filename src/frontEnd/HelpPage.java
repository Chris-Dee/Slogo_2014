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
	public HelpPage(String url){
		addToMain(url);

	}
	/**
	 * @param args
	 */
	public JScrollPane createHelpPage(String url){
		 JEditorPane helpPage;
	JScrollPane helpPagePane=null;
		try {
			helpPage = new  JEditorPane
					(url);
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
	public void addToMain(String url){
		JPanel main=(JPanel) getContentPane();
		main.add(createHelpPage(url));
		pack();
		setMinimumSize(new Dimension(400,600));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
