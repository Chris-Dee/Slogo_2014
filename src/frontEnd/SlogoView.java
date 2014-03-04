package frontEnd;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import frontEnd.HelpPage;
//to-do
//need to put in different preferences
//need to put in panel of previous scripts
//need to add pen


import OptionsPanel.OptionsPanel;
import TurtleStuff.Stats;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleDrawer;
import backEnd.SlogoModel;

@SuppressWarnings("serial")
public class SlogoView extends JFrame{
	private ResourceBundle myResources;
	private ResourceBundle commResources;
	public int backNumber=0;
	private JPanel mainPanel;
	private SlogoModel model;
	private static JTextField xPos;
	private static JTextField yPos;
	private static JTextField results;
	private static JTextField targetx;
	private static JTextField targety;
	private static JTextField angle;
	private static TurtleDrawer TurtleSpace;
	private Button forwardButton;
	private Button backButton;
	private String imageString;
	private static final int NUM_BOXES=3;
	private List<JTextArea> savedBoxes=new ArrayList<JTextArea>();
	private static HelpPage helpPage;
	private JTextArea textInput;
	public static final String DEFAULT_RESOURCE_PATH="frontEnd/";
	public static final String DEFAULT_BUTTON_TEXT="Buttons";
	public static final String DEFAULT_COMM_PATH="backEnd/";
	public static final String DEFAULT_COMM_TEXT="CommandPath";
	public SlogoView(){
		super();
		initiate();
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH+DEFAULT_BUTTON_TEXT);
		commResources=ResourceBundle.getBundle(DEFAULT_COMM_PATH+DEFAULT_COMM_TEXT);
		
	}
	public SlogoView(SlogoModel modelSlog){
		super();
		model=modelSlog;
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH+DEFAULT_BUTTON_TEXT);
		initiate();
		
	}
	//Refactor code--especially text boxes with buttons on top. Can make one method that passes in Strings and action Listener objects
	public static void updateInfo(){
		DecimalFormat decFor=new DecimalFormat("0.000");
		Stats s=TurtleSpace.displayStats();
		xPos.setText(decFor.format(s.getPos().xPos()-Turtle.TURTLE_INIT_X)+"");
		yPos.setText(decFor.format(s.getPos().yPos()-Turtle.TURTLE_INIT_Y)+"");
		targety.setText(s.getTarget().yPos()-Turtle.TURTLE_INIT_X+"");
		targetx.setText(s.getTarget().xPos()-Turtle.TURTLE_INIT_Y+"");
		angle.setText(decFor.format(s.getRot()%360)+"");
	}
	private void makePosPanel(JPanel dataPanel){
		JPanel posPanel=new JPanel();
		JPanel xPanel=new JPanel();
		xPanel.setLayout(new BoxLayout(xPanel,BoxLayout.Y_AXIS));
		JPanel yPanel=new JPanel();
		yPanel.setLayout(new BoxLayout(yPanel,BoxLayout.Y_AXIS));
		xPos=new JTextField(4);
		xPos.setEditable(false);
		yPos=new JTextField(4);
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
	public static void showError(JPanel p,String s){
		JOptionPane.showMessageDialog(p,s);
	}
	private void makeResultsPanel(JPanel homePanel){
		JPanel resultsPanel=new JPanel();
		resultsPanel.setLayout(new BoxLayout(resultsPanel,BoxLayout.Y_AXIS ));
		resultsPanel.add(new JLabel(myResources.getString("EquationResults")));
		results=new JTextField();
		results.setEditable(false);
		resultsPanel.add(results);
		homePanel.add(resultsPanel);
	}
	private void makeTargetPanel(JPanel dataPanel){
		JPanel headingPanel=new JPanel();
		JPanel x=new JPanel();
		x.setLayout(new BoxLayout(x,BoxLayout.Y_AXIS));
		JPanel y=new JPanel();
		y.setLayout(new BoxLayout(y,BoxLayout.Y_AXIS));
		targetx=new JTextField(4);
		targetx.setEditable(false);
		JLabel xLabel=new JLabel(myResources.getString("XTarget"));
		//xLabel.setHorizontalAlignment(SwingConstants.CENTER);
		x.add(xLabel);
		x.add(targetx);
		targety=new JTextField(4);
		targety.setEditable(false);
		JLabel yLabel= new JLabel(myResources.getString("YTarget"));
		//yLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		y.add(yLabel);
		y.add(targety);
		headingPanel.add(x);
		headingPanel.add(y);
		dataPanel.add(headingPanel);
	}
	private void makeAnglePanel(JPanel dataPanel){
		JPanel anglePanel=new JPanel();
		anglePanel.setLayout(new BoxLayout(anglePanel,BoxLayout.Y_AXIS));
		angle=new JTextField();
		angle.setEditable(false);
		JLabel label=new JLabel(myResources.getString("Rotation"));
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		anglePanel.add(label);
		anglePanel.add(angle);
		angle.setHorizontalAlignment(JTextField.CENTER);
		dataPanel.add(anglePanel);
	}
	private void makeDataPanel(JPanel homePanel){
		JPanel dataPanel=new JPanel();
		dataPanel.setLayout(new BoxLayout(dataPanel,BoxLayout.Y_AXIS));
		dataPanel.setBackground(new java.awt.Color(100,100,100));
		makeResultsPanel(dataPanel);
		makePosPanel(dataPanel);
		makeTargetPanel(dataPanel);
		makeAnglePanel(dataPanel);
		homePanel.add(dataPanel);
	}
    protected JPanel makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	
	private void makeCommandPanel(JPanel inputTextPanel){
		inputTextPanel.setLayout(new BoxLayout(inputTextPanel,BoxLayout.Y_AXIS));
		createSaveButton(inputTextPanel);

		textInput=new JTextArea(5,20);
		JScrollPane inputPane=new JScrollPane(textInput);
		textInput.setText("");
		//"Hit submit a lot.It makes hexagons or octagons or something :D \n TODO \n get changing images working.\n refactor JStuff code in FrameLayout \n work on wall hit conditions \n make lines a little less flaky /n changing Turtle image(?)");
		textInput.setSize(100,300);
		Button submit=new Button(myResources.getString("SubmitText"));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				try{
					results.setText(model.receiveTextInput(textInput.getText(), TurtleSpace.getTurtle())+"");
					TurtleSpace.rotateImage();
					savePanel(textInput);

				}
				catch(MissingResourceException e1){
					showError(mainPanel,myResources.getString("IllegalCommand"));
				}

				updateInfo();
			}
		});   
		inputTextPanel.add(inputPane);
		inputTextPanel.add(submit);
	}
	public JPanel makeInputPanel(){
		JPanel textPanel=new JPanel();
		textPanel.setBackground(new java.awt.Color(100,100,100));
		JPanel inputTextPanel=new JPanel();
		makeDataPanel(textPanel);
		makeCommandPanel(inputTextPanel);
		textPanel.add(inputTextPanel);
		return textPanel;
	}


	private void fillSavedBoxes(JPanel oneBox, final int i){

		oneBox.setLayout(new BoxLayout(oneBox,BoxLayout.Y_AXIS));
		final JTextArea savedText=new JTextArea(20,10);
		JScrollPane scrollSave=new JScrollPane(savedText);
		savedBoxes.add(savedText);
		savedText.setEditable(false);
		Button loader=new Button(myResources.getString("RunScript")+" "+(i+1));
		loader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{       
				results.setText(model.receiveTextInput(savedBoxes.get(i).getText(), TurtleSpace.getTurtle())+"");
				updateInfo();    

			}
		});   
		oneBox.add(loader);
		oneBox.add(scrollSave);
	}
	private void createSaveButton(JPanel savePanel){
		Button saveButton=new Button(myResources.getString("Save"));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{  
				savePanel(textInput);
			}
		});
		savePanel.add(saveButton);
	}
	public JPanel makeSavedTextBoxes(){
		JPanel savePanel=new JPanel();
		savePanel.setLayout(new BoxLayout(savePanel,BoxLayout.Y_AXIS));
		JPanel boxPanel=new JPanel();
		for(int i=0;i<NUM_BOXES;i++){
			JPanel oneBox=new JPanel();
			fillSavedBoxes(oneBox,i);
			boxPanel.add(oneBox);}
		savePanel.add(boxPanel);

		savePanel.setBackground(new java.awt.Color(100,100,100));
		return savePanel;
	}
	private void savePanel(JTextArea text){
		for(int i=savedBoxes.size()-1;i>0;i--)
			savedBoxes.get(i).setText(savedBoxes.get(i-1).getText());
		savedBoxes.get(0).setText(textInput.getText());
	}
	public JScrollPane makeDrawingPanel(){
		JPanel drawingPanel=new JPanel();
		JScrollPane scroller=new JScrollPane(drawingPanel);
		drawingPanel.setBackground(new java.awt.Color(200, 200, 200));
		TurtleSpace=new TurtleDrawer();
		drawingPanel.setBackground(new java.awt.Color(100,100,100));
		drawingPanel.setSize(270,270);
		drawingPanel.setMinimumSize(new Dimension(270,270));
		TurtleSpace.setSize(200,200);
		drawingPanel.add(TurtleSpace);
		return scroller;
	}

	//Needs refactoring. Look nicer
	  private  ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = SlogoView.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println(myResources.getString("FileNotFoundError")+": " + path);
	            return null;
	        }
	    }
	
	public void CreateTabs(){
        //createMainPanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        setMinimumSize(new Dimension(1000,800));
        ImageIcon icon = createImageIcon("turtle.gif");
       
        JPanel panel1 = makeTab();
        tabbedPane.addTab("Tab 1", icon, panel1,
                "Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
         
        JPanel panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Tab 2", icon, panel2,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        JPanel panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Tab 3", icon, panel3,
                "Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_3);
        
        setTitle("Slow Go Team 16");
         
        //Add the tabbed pane to this panel.
        add(tabbedPane);
}
	public JPanel getPanel(){
		return mainPanel;
	}
	public JPanel makeTab(){
		JPanel mainPanel= new JPanel();
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		rightPanel.add(makeSavedTextBoxes());
		rightPanel.add(makeInputPanel());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(makeDrawingPanel(),BorderLayout.CENTER);
		mainPanel.add(rightPanel,BorderLayout.EAST);
		mainPanel.add(new OptionsPanel(myResources, TurtleSpace, model, backNumber, textInput, savedBoxes),BorderLayout.NORTH);
		setSize(1000,400);
		setMinimumSize(new Dimension(1000,500));
		//setSize(1000,400);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		pack();
		return mainPanel;
	}
	public void createMainPanel(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		 JTabbedPane tabbedPane = new JTabbedPane();
	    ImageIcon icon = createImageIcon("turtle.gif");
		mainPanel=(JPanel) getContentPane();
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		rightPanel.add(makeSavedTextBoxes());
		rightPanel.add(makeInputPanel());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(makeDrawingPanel(),BorderLayout.CENTER);
		mainPanel.add(rightPanel,BorderLayout.EAST);
		setResizable(true);
		
		setTitle("Slow Go Team 16");
		
//		 //JPanel panel1 = makeTextPanel("Panel #1");
//	    // tabbedPane.addTab("Tab 1", icon, panel1,
//	     //        "Does nothing");
//	     //tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
//	       //  
//	       // JPanel panel2 = makeTextPanel("Panel #2");
//	       // tabbedPane.addTab("Tab 2", icon, panel2,
//	       //         "Does twice as much nothing");
//	       // tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
//
//		//mainPanel.add()
	}


	public void  initiate() {
		//createMainPanel();
		CreateTabs();
		setVisible(true);
		// TODO Auto-generated method stub

	}



}
