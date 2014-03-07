package commands;

import PreferenceManagers.ColorManager;
import TurtleStuff.Turtle;

public class SetPalette extends FourParameterTurtleCommand {
	private ColorManager colors = new ColorManager();

	@Override
	protected double executeTurtle(Turtle turtle) {
		colors.addNewColor((int) myExpression1,(int)  myExpression2,(int)  myExpression3, (int) myExpression4);
		return myExpression1;
	}

}
