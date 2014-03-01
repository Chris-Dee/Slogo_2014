package commands;

public class SetHeading extends TurtleCommand{
	
	private double myDegree;
	
	public SetHeading(){}

	@Override
	public double execute() {
		return myTurtle.setRotation(myDegree);
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
