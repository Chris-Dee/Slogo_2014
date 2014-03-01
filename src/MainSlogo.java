
import backEnd.SlogoModel;
import frontEnd.SlogoView;
/*
 * @Author Slogo_team16
 * 
 */
public class MainSlogo {

	public static void main(String[] args){
    SlogoModel model = new SlogoModel();
    SlogoView display = new SlogoView(model);
    model.setViewer(display);
	}
	
}
