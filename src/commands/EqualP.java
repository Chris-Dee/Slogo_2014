package commands;

public class EqualP extends TwoParameterOperationCommand{
	
	public EqualP() {}
	
	@Override
	public double execute() {
		System.out.println("EqualP execute called: "+myExpression1+" "+myExpression2);
		if (myExpression1 == myExpression2) return 1;
		return 0;
	}
}