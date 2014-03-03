package commands;

public class HideTurtle extends TurtleCommand{
	
	public static final String WHITE_IMAGE_FILEPATH = "resources.whiteImage.jpeg";
	
	public HideTurtle(){}

	@Override
	public double execute() {
		myTurtle.setImage(null);
		return 0;
	}

	@Override
	public String getCommandType() {
		return "HT";
	}	
}
