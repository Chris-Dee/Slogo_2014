
import PreferenceManagers.ColorManager;
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
		SlogoView display = new SlogoView(model, myTurtleManager,myColorManager);
	    model.setViewer(display);
	    model.setTurtleManager(myTurtleManager);
	}	
}
	
