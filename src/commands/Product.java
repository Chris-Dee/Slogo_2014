package commands;

public class Product extends TwoParameterOperationCommand {
	
	public Product() {}
	
	@Override
	public double execute() {
		return myExpression1 * myExpression2;
	}
}
