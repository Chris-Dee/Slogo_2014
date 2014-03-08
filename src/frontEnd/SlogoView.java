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

import textPanel.CommandPanel;
import textPanel.InputPanel;
import backEnd.Managers.*;
import frontEnd.HelpPage;
//to-do
//need to put in different preferences
//need to put in panel of previous scripts
//need to add pen


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
		language=langManager;
		
		initiate();

	}
	//Refactor code--especially text boxes with buttons on top. Can make one method that passes in Strings and action Listener objects
	public static void showError(JPanel p,String s){
		JOptionPane.showMessageDialog(p,s);
	}
	protected JPanel makeTextPanel(String text) {
		JPanel panel = new JPanel(false);
		JLabel filler = new JLabel(text);
		filler.setHorizontalAlignment(JLabel.CENTER);
		panel.setLayout(new GridLayout(1, 1));
		panel.add(filler);
		return panel;
	}
	public static StatsPanel viewStats(){
		return statPage;
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
		JTabbedPane tabbedPane = new JTabbedPane();
		setMinimumSize(new Dimension(1000,800));
		
		ImageIcon icon = createImageIcon("turtle.gif");

		JPanel panel1 = makeTab();
		tabbedPane.addTab("Tab 1", icon, panel1,
				"Tab 1");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JPanel panel2 = makeTextPanel("Panel #2");
		//JPanel panel2 = makeTab();
		tabbedPane.addTab("Tab 2", icon, panel2,
				"Tab 2");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

		JPanel panel3 = makeTextPanel("Panel #3");
		tabbedPane.addTab("Tab 3", icon, panel3,
				"Tab 3");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

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
		mainPanel.add(new VariableDrawingPanel(myResources,variables,TurtleSpace,manager,commandManager),BorderLayout.CENTER);

		rightPanel.add(new InputPanel(model, myResources, manager, language,commandManager));
		rightPanel.add(new workspacepanel(myResources,manager,TurtleSpace));
		statPage=new StatsPanel(myResources, TurtleSpace);
		mainPanel.add(statPage,BorderLayout.EAST);
		mainPanel.add(rightPanel,BorderLayout.WEST);
		mainPanel.add(new OptionsPanel(myResources, TurtleSpace, model, backNumber, textInput, 
				savedBoxes, manager, myColors, images),BorderLayout.NORTH);
		setSize(1000,400);
		//setMinimumSize(new Dimension(1000,500));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		pack();
		return mainPanel;
	} 
	
	
	
	

	public void  initiate() {
		CreateTabs();
		setVisible(true);
		// TODO Auto-generated method stub

	}
	public void setLanguageManager(LanguageManager myLanguageManager) {
		language=myLanguageManager;
	}
}
