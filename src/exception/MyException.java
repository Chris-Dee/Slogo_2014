package exception;

import java.util.ResourceBundle;
import frontEnd.SlogoView;

public abstract class MyException extends Exception{
	
    protected ResourceBundle myErrorMessages;
    
    public MyException (){
		myErrorMessages = ResourceBundle.getBundle(SlogoView.DEFAULT_RESOURCE_PATH + SlogoView.DEFAULT_BUTTON_TEXT);
    }

}
