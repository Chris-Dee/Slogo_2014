package commands;

public interface AbstractCommand {
	public abstract double execute();
	public abstract String getCommandType();
}
