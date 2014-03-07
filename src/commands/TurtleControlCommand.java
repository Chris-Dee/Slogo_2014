package commands;

import TurtleStuff.TurtleManager;

public abstract class TurtleControlCommand extends ControlCommand{
	
	public TurtleControlCommand(){
		super();
	}
	
	protected TurtleManager myTurtleManager;
	
	public void setTurtleManager(TurtleManager manager){
		myTurtleManager = manager;
	}

}
