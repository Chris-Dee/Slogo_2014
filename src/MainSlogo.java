
import PreferenceManagers.ColorManager;
import PreferenceManagers.ImageManager;
import PreferenceManagers.VariableManager;
import TurtleStuff.TurtleManager;
import backEnd.SlogoModel;
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
		VariableManager varManager=new VariableManager();
		SlogoView display = new SlogoView(model, myTurtleManager,myColorManager, myImages, varManager);
	    model.setViewer(display);
	    model.setTurtleManager(myTurtleManager);
	}	
}
	
