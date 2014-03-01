package commands;

public class PenDownP extends TurtleCommand{

	public PenDownP() { }

	@Override
	public double execute() {
		if(myTurtle.getStats().penBool()) { return 1; }
		return 0;
	}

	@Override
	public String getCommandType() {
		return "PENDOWNP";
	}
}