
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
		TurtleManager slogoManager=new TurtleManager();
		SlogoView display = new SlogoView(model,slogoManager);
	    model.setParameters(display, slogoManager);
	    
	}	
}
	
