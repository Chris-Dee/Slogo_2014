package frontEnd;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import backEnd.Managers.*;
import frontEnd.HelpPage;
//to-do
//need to put in different preferences
//need to put in panel of previous scripts
//need to add pen


import CommandPanel.CommandPanel;
import DrawingPanel.VariableDrawingPanel;
import OptionsPanel.OptionsPanel;
import PreferenceManagers.ColorManager;
import PreferenceManagers.ImageManager;
import StatsPanel.StatsPanel;
import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;
import backEnd.SlogoModel;

@SuppressWarnings("serial")
public class SlogoView extends JFrame{
	private ResourceBundle myResources;
	private ResourceBundle commResources;
	public int backNumber=0;
	private JPanel mainPanel;
	private SlogoModel model;
	private static JTextField results;
	private static TurtleDrawer TurtleSpace;
	private static final int NUM_BOXES=3;
	private List<JTextArea> savedBoxes=new ArrayList<JTextArea>();
	private static HelpPage helpPage;
	private static StatsPanel statPage;
	private JTextArea textInput;
	public static final String DEFAULT_RESOURCE_PATH="frontEnd/";
	public static final String DEFAULT_BUTTON_TEXT="Buttons";
	public static final String DEFAULT_COMM_PATH="backEnd/";
	public static final String DEFAULT_COMM_TEXT="CommandPath";
	private TurtleManager manager;
	private ColorManager myColors;
	private VariableManager variables;
	private ImageManager images;
	private LanguageManager language;
	private UserCommandManager commandManager;
	public SlogoView(){
		super();
		initiate();
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH+DEFAULT_BUTTON_TEXT);
		commResources=ResourceBundle.getBundle(DEFAULT_COMM_PATH+DEFAULT_COMM_TEXT);
		
	}
	public SlogoView(SlogoModel modelSlog, TurtleManager manage, ColorManager colors, ImageManager image, 
			VariableManager myVariableManager,LanguageManager langManager,UserCommandManager commManager){
		super();
		model=modelSlog;
		images=image;
		manager=manage;
		commandManager=commManager;
		myColors=colors;
		myResources=ResourceBundle.getBundle(DEFAULT_RESOURCE_PATH+DEFAULT_BUTTON_TEXT);
		variables=myVariableManager;
		initiate();
		
	}
	//Refactor code--especially text boxes with buttons on top. Can make one method that passes in Strings and action Listener objects
	public static void showError(JPanel p,String s){
		JOptionPane.showMessageDialog(p,s);
	}
	private void makeDataPanel(JPanel homePanel){
		JPanel dataPanel=new JPanel();
		statPage=new StatsPanel(myResources,TurtleSpace);
		dataPanel.add(statPage);
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
	private void makeLangList(JPanel homePanel){
		final JComboBox languagesList=new JComboBox((removeFileNames(new File("src/resources").list())));
		homePanel.add(languagesList);
		languagesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{       
				model.setLanguage((String) languagesList.getSelectedItem());  
				System.out.println(language.getLanguage());
			}
		});  
	}
	private Object[] removeFileNames(String[] s){
		List<String> langList=new ArrayList<String>();
		for(int i=0;i<s.length;i++){
			if(s[i].contains(".properties")){
			String[] str=s[i].split(".properties");
			System.out.println(str[0]);
			langList.add(str[0]);
			}
		}
		return langList.toArray();
	}
	public JPanel makeInputPanel(){
		JPanel textPanel=new JPanel();
		textPanel.setBackground(new java.awt.Color(100,100,100));
		JPanel inputTextPanel=new JPanel();
		makeDataPanel(textPanel);
		inputTextPanel.add(new CommandPanel(myResources,model,manager,language));
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
				results.setText(model.receiveTextInput(savedBoxes.get(i).getText())+"");
				statPage.updateInfo();    
			}
		});   
		oneBox.add(loader);
		oneBox.add(scrollSave);
	}
	public static StatsPanel viewStats(){
		return statPage;
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
		
		mainPanel.setLayout(new BorderLayout());
		TurtleSpace=new TurtleDrawer(manager);
		mainPanel.add(new VariableDrawingPanel(myResources,variables,TurtleSpace,manager),BorderLayout.CENTER);
		rightPanel.add(makeSavedTextBoxes());
		rightPanel.add(makeInputPanel());
		mainPanel.add(rightPanel,BorderLayout.EAST);
		mainPanel.add(new OptionsPanel(myResources, TurtleSpace, model, backNumber, textInput, savedBoxes, manager, myColors, images),BorderLayout.NORTH);
		setSize(1000,400);
		setMinimumSize(new Dimension(1000,500));
		//setSize(1000,400);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		pack();
		return mainPanel;
	}
	public void  initiate() {
		//createMainPanel();
		CreateTabs();
		setVisible(true);
		// TODO Auto-generated method stub

	}
	public void setLanguageManager(LanguageManager myLanguageManager) {
		language=myLanguageManager;
	}
}
