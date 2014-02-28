package frontEnd;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//to-do
//need to put in different preferences
//need to put in panel of previous scripts
//need to add pen

import backEnd.SlogoModel;

@SuppressWarnings("serial")
public class SlogoView extends JFrame{
	private Stats turtleStats;
	private SlogoModel model;
	private static JTextField xPos;
	private static JTextField yPos;
	private static JTextField headingx;
	private static JTextField headingy;
	private static JTextField angle;
	private static TurtleDrawer turtleSpace;
	private static final int NUM_BOXES=3;
	private List<JTextArea> savedBoxes=new ArrayList<JTextArea>();
	private static final HelpPage helpPage=new HelpPage();
	private JTextArea textInput;
	public SlogoView(){
		super();
		initiate();
	}
	public SlogoView(SlogoModel modelSlog){
		super();
		initiate();
		model=modelSlog;
	}
	private void makeForwardPanel(JPanel forwardPanel){
		JPanel forPanel=new JPanel();
		forPanel.setLayout(new BoxLayout(forPanel,BoxLayout.Y_AXIS));
		Button forwardButton=new Button("Move Forward");
		final JTextField forwardInput=new JTextField(5);
		forwardButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{              
				updateInfo();
				turtleSpace.turt.goForward(Integer.parseInt(forwardInput.getText()));
			}
		});   

		forPanel.add(forwardButton);
		forPanel.add(forwardInput);
		forwardPanel.add(forPanel);
	}
	public static void updateInfo(){
		DecimalFormat decFor=new DecimalFormat("0.000");
		Stats s=turtleSpace.getStats();
		xPos.setText(decFor.format(s.getPos().xPos()-Turtle.TURTLE_INIT_X)+"");
		yPos.setText(decFor.format(s.getPos().yPos()-Turtle.TURTLE_INIT_Y)+"");
		headingy.setText(s.getHeading().y+"");
		headingx.setText(s.getHeading().x+"");
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
		JLabel xLabel=new JLabel("x position");
		xPanel.add(xLabel);
		xPanel.add(xPos);
		yPanel.add(new JLabel("y position"));
		yPanel.add(yPos);
		posPanel.add(xPanel);
		posPanel.add(yPanel);
		dataPanel.add(posPanel);
		
	}
	private void makeHeadingPanel(JPanel dataPanel){
		JPanel headingPanel=new JPanel();
		JPanel x=new JPanel();
		x.setLayout(new BoxLayout(x,BoxLayout.Y_AXIS));
		JPanel y=new JPanel();
		y.setLayout(new BoxLayout(y,BoxLayout.Y_AXIS));
		headingx=new JTextField(4);
		headingx.setEditable(false);
		JLabel xLabel=new JLabel("x Heading");
		//xLabel.setHorizontalAlignment(SwingConstants.CENTER);
		x.add(xLabel);
		x.add(headingx);
		headingy=new JTextField(4);
		headingy.setEditable(false);
		JLabel yLabel= new JLabel("y heading");
		//yLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		y.add(yLabel);
		y.add(headingy);
		headingPanel.add(x);
		headingPanel.add(y);
		dataPanel.add(headingPanel);
	}
	private void makeAnglePanel(JPanel dataPanel){
		JPanel anglePanel=new JPanel();
		anglePanel.setLayout(new BoxLayout(anglePanel,BoxLayout.Y_AXIS));
		angle=new JTextField();
		angle.setEditable(false);
		JLabel label=new JLabel("Rotation");
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
		
		
		makePosPanel(dataPanel);
		makeHeadingPanel(dataPanel);
		makeAnglePanel(dataPanel);
		homePanel.add(dataPanel);
	}
	private void makeRotatePanel(JPanel rotationPanel){
		JPanel rotatePanel=new JPanel();
		rotatePanel.setLayout(new BoxLayout(rotatePanel, BoxLayout.Y_AXIS));
		Button rotationButton=new Button("Rotate Turtle");
		//rotationPanel.setLayout(new BoxLayout(rotationPanel,BoxLayout.Y_AXIS));
		final JTextField rotationInput=new JTextField(4);
		rotationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{                
				turtleSpace.turt.addRotation(Double.parseDouble(rotationInput.getText()));
			}
		});   
		rotatePanel.add(rotationButton);
		rotatePanel.add(rotationInput);
		rotationPanel.add(rotatePanel);
	}
	private void makeCommandPanel(JPanel inputTextPanel){
		inputTextPanel.setLayout(new BoxLayout(inputTextPanel,BoxLayout.Y_AXIS));
		createSaveButton(inputTextPanel);

		textInput=new JTextArea(5,20);
		JScrollPane inputPane=new JScrollPane(textInput);
		textInput.setText("");
		//"Hit submit a lot.It makes hexagons or octagons or something :D \n TODO \n get changing images working.\n refactor JStuff code in FrameLayout \n work on wall hit conditions \n make lines a little less flaky /n changing turtle image(?)");
		textInput.setSize(100,300);
		Button submit=new Button("Submit Text");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{     
				updateInfo();          
				//turtleSpace.turt.setTarget(new Point((int)(Math.random()*200),(int)(Math.random()*200)));
				turtleSpace.turt.goForward(30);
				turtleSpace.turt.addRotation(45);
				//turtcommands.setText(textInput.getText());
				savePanel(textInput);
				
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


	private void fillSavedBoxes(JPanel oneBox, int i){

		oneBox.setLayout(new BoxLayout(oneBox,BoxLayout.Y_AXIS));
		final JTextArea savedText=new JTextArea(28,10);
		JScrollPane scrollSave=new JScrollPane(savedText);
		savedBoxes.add(savedText);
		savedText.setEditable(false);
		Button loader=new Button("Run Script"+" "+(i+1));
		loader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{       
				updateInfo();    
				//TODO Need to make this run hte script in textFile
				turtleSpace.turt.goForward(10);
				
			}
		});   
		oneBox.add(loader);
		oneBox.add(scrollSave);
	}
	private void createSaveButton(JPanel savePanel){
		Button saveButton=new Button("save");
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
		turtleSpace=new TurtleDrawer(30,30);
		drawingPanel.setBackground(new java.awt.Color(100,100,100));
		drawingPanel.setSize(200,200);
		drawingPanel.setMinimumSize(new Dimension(200,200));
		turtleSpace.setMinimumSize(new Dimension(200,200));
		turtleSpace.setSize(200,200);
		drawingPanel.add(turtleSpace);
		return scroller;
	}
	public Button makeHelpButton(){
		Button helpButton=new Button("Help Button");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{             
				helpPage.setVisible(!helpPage.isVisible());
			}
		});   
		return helpButton;
	}
	public Button makeRefreshButton(){
		Button refresh=new Button("Refresh");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{             
				//need to reset turtle and clear lines
				turtleSpace.refresh();
				textInput.setText("");
				for(JTextArea text:savedBoxes)
					text.setText("");
				turtleSpace.refresh();
			}
		});   
		return refresh;
	}
	private void makeImageChooserButton(JPanel panel){
		final JTextField imageChooser = new JTextField(10);
		panel.add(imageChooser);
		Button selectImage= new Button("Select Image");
		selectImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String imageFile = imageChooser.getText();
				turtleSpace.newTurtle(imageFile);
			}
		});
		panel.add(selectImage);
	}
	public JPanel makeOptionsPanel(){
		JPanel optionsPanel=new JPanel();

		optionsPanel.add(makeHelpButton());
		optionsPanel.setBackground(new java.awt.Color(100,100,100));
		// create textArea and button to decide on image
		makeImageChooserButton(optionsPanel);
		optionsPanel.add(makeRefreshButton());
		makeRotatePanel(optionsPanel);
		makeForwardPanel(optionsPanel);
		return optionsPanel;
	}
	public void createMainPanel(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400,600));
		setVisible(true);
		JPanel mainPanel=(JPanel) getContentPane();
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		rightPanel.add(makeSavedTextBoxes());
		rightPanel.add(makeInputPanel());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(makeDrawingPanel(),BorderLayout.CENTER);
		mainPanel.add(makeOptionsPanel(),BorderLayout.NORTH);
		mainPanel.add(rightPanel,BorderLayout.EAST);
		setResizable(false);
		pack();
		setTitle("Slow Go Team 16");
	}


	public void  initiate() {
		
		createMainPanel();
		setVisible(true);
		// TODO Auto-generated method stub

	}


}
