package commands;

import backEnd.CommandFactory;

public class IfElse extends ControlCommand{
	
	private CommandFactory myFactory;
	private double myElseStatement;
	
	public IfElse() {
		super();
		myFactory = new CommandFactory();
	}
	
	public void setDoubleMagnitude(double ifCase, double elseCase) {
		myMagnitude = ifCase;
		myElseStatement = elseCase;
	}

	@Override
	public String getCommandType() {
		// TODO Auto-generated method stub
		return "IFELSE";
	}

	@Override
	public double execute() {
		double answer = 0;
		return 0;
	}

}
