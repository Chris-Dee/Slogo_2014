import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class FrameLayout extends JFrame{
JTextArea textInput;
	public JPanel makeInputPanel(){
		JPanel textPanel=new JPanel(new FlowLayout());
		textPanel.setBackground(new java.awt.Color(0,0,0));
		 textInput=new JTextArea(5,30);
		textInput.setSize(100,300);
		textPanel.add(textInput);
		Button submit=new Button("Submit Text");
		submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {                //Execute when button is pressed
              //submitMethods();
            }
        });   
		textPanel.add(submit);
		return textPanel;
	}
	public JPanel makeDrawingPanel(){
		JPanel drawingPanel=new JPanel();
		drawingPanel.setBackground(new java.awt.Color(200, 200, 200));
		//JPanel panel=new JPanel(){
			
		//}
		TurtleDrawer turt=new TurtleDrawer(30,30);
		drawingPanel.setBackground(new java.awt.Color(200,200,200));
		drawingPanel.setSize(200,200);
		drawingPanel.setMinimumSize(new Dimension(200,200));
		turt.setMinimumSize(new Dimension(200,200));
		turt.setSize(200,200);
		drawingPanel.add(turt);
		return drawingPanel;
	}
	public JPanel makeOptionsPanel(){
		JPanel optionsPanel=new JPanel();
		optionsPanel.setBackground(new java.awt.Color(0,0,0));
		JButton refresh=new JButton("Refresh");
		refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {             
               //refresh();
            	textInput.setText("");
            }
        });   
		optionsPanel.add(refresh);
		return optionsPanel;
	}
	public void createMainPanel(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400,600));
		setVisible(true);
		JPanel mainPanel=(JPanel) getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(makeDrawingPanel(),BorderLayout.CENTER);
		mainPanel.add(makeOptionsPanel(),BorderLayout.NORTH);
		mainPanel.add(makeInputPanel(),BorderLayout.SOUTH);
		pack();
		setTitle("Slow Go Team 16");
		//mainPanel.add()
	}
	
	public static void main(String[] args) {
		FrameLayout x=new FrameLayout();
		x.createMainPanel();
		x.setVisible(true);
		// TODO Auto-generated method stub

	}
	

}
