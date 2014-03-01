package commands;

public class ShowTurtle extends TurtleCommand{
	
	public static final String WHITE_IMAGE_FILEPATH = "resources.whiteImage.jpeg";
	
	public ShowTurtle(){}

	@Override
	public double execute() {
		myTurtle.setImage(WHITE_IMAGE_FILEPATH);
		return 0;
	}

	@Override
	public String getCommandType() {
		return "PU";
	}	
}
