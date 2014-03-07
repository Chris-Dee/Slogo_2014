package commands;

import java.util.List;

import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;

public class Tell extends TurtleControlCommand{
	
	public Tell(){ super(); }
	
	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		myTurtleManager.setFilterList(getIDsOfExpression(expr));
		List<StringNode> last = myParser.parse(expr.get(expr.size()-1).getCommandString());
		return myFactory.runCommands(last, myTurtleManager.getAllTurtles());
	}
}
