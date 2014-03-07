
import PreferenceManagers.ColorManager;
import PreferenceManagers.ImageManager;
import TurtleStuff.TurtleManager;
import backEnd.SlogoModel;
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
		SlogoView display = new SlogoView(model, myTurtleManager,myColorManager, myImages, myVariableManager, myUserCommandManager);
	    model.setViewer(display);
	    model.setVariableManager(myVariableManager);
	    model.setUserCommandManager(myUserCommandManager);
	    model.setTurtleManager(myTurtleManager);
	}	
}
	
