package commands;

public class ShowTurtle extends TurtleCommand{
	
	public static final String TURTLE_IMAGE_FILEPATH = "resources.cartoon_turtle.gif";
	
	public ShowTurtle(){}

	@Override
	public double execute() {
		myTurtle.setImage(TURTLE_IMAGE_FILEPATH);
		return 1;
	}

	@Override
	public String getCommandType() {
		return "ST";
	}	
}
