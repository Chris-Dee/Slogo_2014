package frontEnd;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import backEnd.SlogoModel;
import backEnd.Managers.LanguageManager;
import backEnd.Managers.UserCommandManager;
import backEnd.Managers.VariableManager;

import textPanel.InputPanel;
import DrawingPanel.VariableDrawingPanel;
import OptionsPanel.OptionsPanel;
import PreferenceManagers.ColorManager;
import PreferenceManagers.ImageManager;
import StatsPanel.StatsPanel;
import TurtleStuff.TurtleDrawer;
import TurtleStuff.TurtleManager;


public class makeTab extends JPanel{
	private ResourceBundle myResources;
	private ResourceBundle commResources;
	public int backNumber=0;
	//private JPanel mainPanel;
	private SlogoModel model;
	private static JTextField results;
	//private static TurtleDrawer TurtleSpace;
	private static final int NUM_BOXES=3;
	private List<JTextArea> savedBoxes=new ArrayList<JTextArea>();
	private static HelpPage helpPage;
	//private static StatsPanel statPage;
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
	public JPanel makeTab(){
		JPanel mainPanel= new JPanel();
		JPanel rightPanel=new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		mainPanel.setLayout(new BorderLayout());
		TurtleDrawer TurtleSpace=new TurtleDrawer(manager);
		mainPanel.add(new VariableDrawingPanel(myResources,variables,TurtleSpace,manager,commandManager),BorderLayout.CENTER);
		rightPanel.add(new InputPanel(model, myResources, manager, language,commandManager));
		StatsPanel statPage=new StatsPanel(myResources, TurtleSpace);
		mainPanel.add(statPage,BorderLayout.EAST);
		mainPanel.add(rightPanel,BorderLayout.WEST);
		mainPanel.add(new OptionsPanel(myResources, TurtleSpace, model, backNumber, textInput, 
				savedBoxes, manager, myColors, images),BorderLayout.NORTH);
		setSize(1000,400);
		//setMinimumSize(new Dimension(1000,500));
		//setExtendedState(Frame.MAXIMIZED_BOTH);
		//pack();
		return mainPanel;
	}
}
