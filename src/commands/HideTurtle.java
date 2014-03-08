package commands;

import PreferenceManagers.ImageManager;
import TurtleStuff.Turtle;

public class HideTurtle extends NonParameterTurtleCommand{
	ImageManager images = new ImageManager();
	public static final int INVISIBLE_INDEX = 0;
	
	public HideTurtle(){} 
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.setImage(null);
		turtle.setImageID(INVISIBLE_INDEX);
		return 0;
	}
}
