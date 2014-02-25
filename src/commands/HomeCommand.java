package commands;

import java.awt.Point;
import java.util.List;

import frontEnd.Turtle;

public class HomeCommand extends AbstractCommand {
	
	public static final int HOME_X = 0;
	public static final int HOME_Y = 0;
	
	public HomeCommand(String string, List<Turtle> turtles){
		super(string, turtles);
	}

	@Override
	public double execute() {
		double distance = 0;
		for(Turtle current: myTurtles){
			// need revision
			current.setTarget(new Point(HOME_X, HOME_Y));
		}
		return distance;
	}
}
