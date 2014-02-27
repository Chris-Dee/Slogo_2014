package commands;

public class SetHeading extends TurtleCommand{
	
	private double myDegree;
	
	public SetHeading(){}

	@Override
	public double execute() {
		myTurtle.setRotation(myDegree);
		return 0; // need revision
	}

	@Override
	public String getCommandType() {
		return "SETH";
	}
	
	public void setMagnitude(double magnitude){
		if(magnitude >= 0 && magnitude <= 360){ // need check
			myDegree = magnitude;
		}
	}

}
