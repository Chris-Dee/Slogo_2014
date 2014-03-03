package frontEnd;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
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
import frontEnd.Stats;
import frontEnd.Turtle;
import frontEnd.TurtleDrawer;
//to-do
//need to put in different preferences
//need to put in panel of previous scripts
//need to add pen


import backEnd.SlogoModel;

@SuppressWarnings("serial")
public class SlogoView extends JFrame{
	private ResourceBundle myResources;
	private JPanel mainPanel;
	private SlogoModel model;
	private static JTextField xPos;
	private static JTextField yPos;
	private static JTextField results;
	private static JTextField headingx;
	private static JTextField headingy;
	private static JTextField angle;
	private static TurtleDrawer TurtleSpace;
	private String imageString;
	private static final int NUM_BOXES=3;
	private List<JTextArea> savedBoxes=new ArrayList<JTextArea>();
	private static HelpPage helpPage;
	private JTextArea textInput;
	private static final String DEFAULT_RESOURCE_PATH="frontEnd/";
	private static final String DEFAULT_BUTTON_TEXT="Buttons";
	public SlogoView(){
		super();
		initiate();
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH+DEFAULT_BUTTON_TEXT);
		
	}
	public SlogoView(SlogoModel modelSlog){
		super();
		model=modelSlog;
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH+DEFAULT_BUTTON_TEXT);
		initiate();
		
	}
	//Refactor code--especially text boxes with buttons on top. Can make one method that passes in Strings and action Listener objects
	private void makeForwardPanel(JPanel forwardPanel){
		JPanel forPanel=new JPanel();
		forPanel.setLayout(new BoxLayout(forPanel,BoxLayout.Y_AXIS));
		
		Button forwardButton=new Button(myResources.getString("MoveForward"));
		final JTextField forwardInput=new JTextField(5);
		forwardButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e)
			{              


				try{
					TurtleSpace.moveForward(Integer.parseInt(forwardInput.getText()));
					updateInfo();
				}catch(Exception e1){
					showError(myResources.getString("NumberFormat"));}
			}
		});   

		forPanel.add(forwardButton);
		forPanel.add(forwardInput);
		forwardPanel.add(forPanel);
	}
	public static void updateInfo(){
		DecimalFormat decFor=new DecimalFormat("0.000");
		Stats s=TurtleSpace.displayStats();
		xPos.setText(decFor.format(s.getPos().xPos()-Turtle.TURTLE_INIT_X)+"");
		yPos.setText(decFor.format(s.getPos().yPos()-Turtle.TURTLE_INIT_Y)+"");
		headingy.setText(s.getTarget().y+"");
		headingx.setText(s.getTarget().x+"");
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
	public void showError(String s){
		JOptionPane.showMessageDialog(mainPanel,s);
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
		headingx=new JTextField(4);
		headingx.setEditable(false);
		JLabel xLabel=new JLabel(myResources.getString("XTarget"));
		//xLabel.setHorizontalAlignment(SwingConstants.CENTER);
		x.add(xLabel);
		x.add(headingx);
		headingy=new JTextField(4);
		headingy.setEditable(false);
		JLabel yLabel= new JLabel(myResources.getString("YTarget"));
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
	private void makeRotatePanel(JPanel rotationPanel){
		JPanel rotatePanel=new JPanel();
		rotatePanel.setLayout(new BoxLayout(rotatePanel, BoxLayout.Y_AXIS));
		Button rotationButton=new Button(myResources.getString("RotateTurtle"));
		//rotationPanel.setLayout(new BoxLayout(rotationPanel,BoxLayout.Y_AXIS));
		final JTextField rotationInput=new JTextField(4);
		rotationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{             
				try{
					TurtleSpace.addRotations(Double.parseDouble(rotationInput.getText()));
				}catch(Exception e1){
					showError(myResources.getString("NumberFormat"));
				}
				TurtleSpace.rotateImage();
				updateInfo();
			}
		});   
		rotatePanel.add(rotationButton);
		rotatePanel.add(rotationInput);
		rotationPanel.add(rotatePanel);
	}
	private void makeFilterPanel(JPanel homePanel){
		JPanel filterPanel=new JPanel();
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
		Button filterButton=new Button(myResources.getString("FilterTurtle"));
		//rotationPanel.setLayout(new BoxLayout(rotationPanel,BoxLayout.Y_AXIS));
		final JTextField filterInput=new JTextField(3);
		filterInput.setText("0");
		filterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{             

				TurtleSpace.setFilter(Integer.parseInt(filterInput.getText()));
			}
		});   
		filterPanel.add(filterButton);
		filterPanel.add(filterInput);
		homePanel.add(filterPanel);
	}
	public void makeScroller(JPanel homePanel){
		JPanel scrollPanel=new JPanel();
		scrollPanel.setLayout(new BoxLayout(scrollPanel,BoxLayout.Y_AXIS));
		scrollPanel.add(new JLabel(myResources.getString("VelocitySlider")));
		JSlider veloSlider=new JSlider(JSlider.HORIZONTAL, 1,10,5);
		veloSlider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				TurtleSpace.setVelocities(((JSlider)arg0.getSource()).getValue()*0.01);
			}
		});
		scrollPanel.add(veloSlider);
		homePanel.add(scrollPanel);
	}
	public void makeSunButton(JPanel homePanel){
		Button sunButton=new Button(myResources.getString("FillDrawer"));
		sunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{      
				updateInfo();
				
				TurtleSpace.addRotations(45);
				for(int k=0;k<3;k++){
					TurtleSpace.addRotations(90);
					TurtleSpace.moveForward(30);
				}
				TurtleSpace.rotateImage();
				}
			
		});  
		homePanel.add(sunButton);
	}
	
    protected JPanel makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	

	public void makeNewTurtleButton(JPanel homePanel){
		Button newTurtButton = new Button(myResources.getString("NewTurtle"));
		JPanel newPanel=new JPanel();
		newPanel.setLayout(new BoxLayout(newPanel,BoxLayout.Y_AXIS));
		JPanel labelPanel=new JPanel();
		JLabel idToAdd=new JLabel("Id of turtle");
		final JTextField turtId=new JTextField(2);
		turtId.setText("0");
		labelPanel.add(idToAdd);
		labelPanel.add(turtId);
		newTurtButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				updateInfo();
				try{
				TurtleSpace.addnewTurtle(Integer.parseInt(turtId.getText()));
				}catch(Exception e1){
					showError(myResources.getString("MustBeInt"));
				}
			}
		});
		newPanel.add(newTurtButton);
		newPanel.add(labelPanel);
		homePanel.add(newPanel);
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
					showError(myResources.getString("IllegalCommand"));
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
		final JTextArea savedText=new JTextArea(28,10);
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
		drawingPanel.setSize(300,300);
		drawingPanel.setMinimumSize(new Dimension(300,300));
		TurtleSpace.setMinimumSize(new Dimension(300,300));
		TurtleSpace.setSize(200,200);
		drawingPanel.add(TurtleSpace);
		return scroller;
	}
	public Button makeHelpButton(){
		Button helpButton=new Button(myResources.getString("HelpButton"));
		//final HelpPage helpPage=new HelpPage();
		boolean page=true;
		if(helpPage!=null)
			page=helpPage.isVisible();
		final boolean finalPage=page;
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{     
				helpPage=new HelpPage();
				helpPage.setVisible(finalPage);

			}
		});   
		return helpButton;
	}
	public Button makeRefreshButton(){
		Button refresh=new Button(myResources.getString("Refresh"));
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{             
				//need to reset Turtle and clear lines
				TurtleSpace.refresh();
				textInput.setText("");
				for(JTextArea text:savedBoxes)
					text.setText("");
				TurtleSpace.refresh();
			}
		});   
		return refresh;
	}
	private void makeImageChooserButton(JPanel homePanel){
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		final JTextField imageChooser = new JTextField(10);
		Button selectImage= new Button(myResources.getString("SelectImage"));
		selectImage.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String imageFile = imageChooser.getText();
				try{
					TurtleSpace.newTurtle(imageFile);
				}
				catch(Exception e1){
					showError("File was not found");
				}
			}
		});
		panel.add(selectImage);
		panel.add(imageChooser);
		homePanel.add(panel);
	}
	public JPanel makeOptionsPanel(){
		JPanel optionsPanel=new JPanel();
		makeScroller(optionsPanel);
		optionsPanel.add(makeHelpButton());
		optionsPanel.setBackground(new java.awt.Color(100,100,100));
		makeImageChooserButton(optionsPanel);
		optionsPanel.add(makeRefreshButton());
		makeRotatePanel(optionsPanel);
		makeForwardPanel(optionsPanel);
		makeFilterPanel(optionsPanel);
		makeSunButton(optionsPanel);
		makeNewTurtleButton(optionsPanel);
		return optionsPanel;
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
	public JPanel makeTab(){
		JPanel mainPanel= new JPanel();
		mainPanel.add(makeOptionsPanel());
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		rightPanel.add(makeSavedTextBoxes());
		rightPanel.add(makeInputPanel());
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(makeDrawingPanel(),BorderLayout.CENTER);
		mainPanel.add(makeOptionsPanel(),BorderLayout.NORTH);
		//mainPanel.add(makeInputPanel(),BorderLayout.SOUTH);
		mainPanel.add(rightPanel,BorderLayout.EAST);
		setResizable(false);
		pack();
		return mainPanel;
	}
	public void createMainPanel(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400,600));
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
		mainPanel.add(makeOptionsPanel(),BorderLayout.NORTH);
		//mainPanel.add(makeInputPanel(),BorderLayout.SOUTH);
		mainPanel.add(rightPanel,BorderLayout.EAST);
		setResizable(false);
		pack();
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
