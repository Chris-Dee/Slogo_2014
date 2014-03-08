package commands;

import PreferenceManagers.ImageManager;
import TurtleStuff.Turtle;

public class HideTurtle extends NonParameterTurtleCommand{
	ImageManager images = new ImageManager();
	
	public HideTurtle(){} 
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.hideTurtle();
		return 0;
	}
}
