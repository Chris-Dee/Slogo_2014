package commands;

import PreferenceManagers.ColorManager;
import TurtleStuff.Turtle;

public class SetPenColor extends OneParameterTurtleCommand{
	private ColorManager colors = new ColorManager();

	@Override
	protected double executeTurtle(Turtle turtle) {
		turtle.setPen((int) myMagnitude, colors.getColorByIndex((int) myMagnitude));
		return myMagnitude;
	}

}
