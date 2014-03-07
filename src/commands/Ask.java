package commands;

import java.util.List;
import TurtleStuff.Turtle;
import parser.tree.StringNode;
import exception.IllegalCommandException;
import exception.IllegalParameterException;

public class Ask extends TurtleControlCommand {

	public Ask(){ super(); }

	@Override
	public double execute() throws IllegalCommandException, IllegalParameterException {
		myFactory.setVariableManager(myVariableManager);
		List<StringNode> expr = myParser.parse(myExpression);
		List<Turtle> turtles = myTurtleManager.getFilteredTurtles();
		List<Integer> lastIDs = getIDsOfTurtles(turtles);
		myTurtleManager.setFilterList(processExprNodes(expr));
		List<StringNode> commands = myParser.parse(myCommands);
		double answer = myFactory.runCommands(commands, myTurtleManager.getFilteredTurtles());
		myTurtleManager.setFilterList(lastIDs);
		return answer;
	}
}
