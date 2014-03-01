package commands;

public class ShowTurtle extends TurtleCommand{
	
	//public static final int WHITE_IMAGE_NAME = "whiteImage";
	
	public ShowTurtle(){}

	@Override
	public double execute() {
		myTurtle.setImage(imgname);
		return 0;
	}

	@Override
	public String getCommandType() {
		return "PU";
	}	
}
