
import PreferenceManagers.ColorManager;
import PreferenceManagers.ImageManager;
import TurtleStuff.TurtleManager;
import backEnd.SlogoModel;
import backEnd.Managers.LanguageManager;
import backEnd.Managers.UserCommandManager;
import backEnd.Managers.VariableManager;
import frontEnd.SlogoView;
/*
 * @Author Slogo_team16
 * 
 */
public 
class MainSlogo {

	public static void main(String[] args){
		newWindow();
	}
	
	public static void newWindow(){
		SlogoModel model = new SlogoModel();
		TurtleManager myTurtleManager=new TurtleManager();
		ColorManager myColorManager=new ColorManager();
		ImageManager myImages=new ImageManager();
		VariableManager myVariableManager = new VariableManager();
		UserCommandManager myUserCommandManager = new UserCommandManager();
		LanguageManager myLanguageManager = new LanguageManager();
		SlogoView display = new SlogoView(model, myTurtleManager,myColorManager, myImages, myVariableManager, myLanguageManager, myUserCommandManager);
	    display.setLanguageManager(my);
		model.setViewer(display);
	    model.setVariableManager(myVariableManager);
	    model.setUserCommandManager(myUserCommandManager);
	    model.setTurtleManager(myTurtleManager);
	    model.setLanguageManager(myLanguageManager);
	}	
}
	
