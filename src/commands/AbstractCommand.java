package commands;

import exception.IllegalCommandException;
import exception.IllegalParameterException;

public interface AbstractCommand {
	public abstract double execute() throws IllegalCommandException, IllegalParameterException;
	public abstract String getCommandType();
}
