package commands;

import exception.IllegalCommandException;
import exception.IllegalParameterException;
import exception.UndefinedVariableException;

public interface AbstractCommand {
	public abstract double execute() throws IllegalCommandException, IllegalParameterException, UndefinedVariableException;
}
