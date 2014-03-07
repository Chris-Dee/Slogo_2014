package commands;

import PreferenceManagers.ImageManager;
import TurtleStuff.Turtle;

public class SetShape extends OneParameterTurtleCommand{

	ImageManager images = new ImageManager();
	
	@Override
	protected double executeTurtle(Turtle turtle) {
		// TODO Auto-generated method stub
		turtle.setImage(images.getPathByIndex((int) myMagnitude));
		turtle.setImageID((int) myMagnitude);
		return myMagnitude;
	}

}
