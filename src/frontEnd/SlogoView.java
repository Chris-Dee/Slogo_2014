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

import jgame.JGColor;

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
	private String[] backGroundPossib=new String[]{"0 0 255","255 255 255","0 255 0","255 0 0","0 0 0","255 0 255","0 255 255"};
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
	public static void showError(JPanel p,String s){
		JOptionPane.showMessageDialog(p,s);
	}
	public static StatsPanel viewStats(){
		return statPage;
	}
	private JPanel makeTab(){
		JPanel mainPanel= new JPanel();
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BorderLayout());

		TurtleSpace=new TurtleDrawer(manager,getBackgroundColor());
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
	
	private JGColor getBackgroundColor(){
		String s = (String)JOptionPane.showInputDialog(
                this,
                myResources.getString("BackgroundChooser"),
                myResources.getString("BGTitle"),
                JOptionPane.PLAIN_MESSAGE,
                null,
                backGroundPossib,
                myResources.getString("DefaultColor"));
		String[] str=s.split(" ");
		return new JGColor(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2]));
	}
	
	

	private void  initiate() {
		this.getContentPane().add(makeTab());
		setVisible(true);
		// TODO Auto-generated method stub

	}
	public void setLanguageManager(LanguageManager myLanguageManager) {
		language=myLanguageManager;
	}
}
