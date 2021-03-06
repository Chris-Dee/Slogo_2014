package commands;

import PreferenceManagers.ImageManager;
import TurtleStuff.Turtle;
import TurtleStuff.TurtleDrawer;

public class SetShape extends OneParameterTurtleCommand{
	ImageManager images = new ImageManager();
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.setImage(images.getPathByIndex((int) myMagnitude));
		turtle.setImageID((int) myMagnitude);
		return myMagnitude;
	}

}
