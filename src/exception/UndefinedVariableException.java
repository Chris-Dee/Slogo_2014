package exception;

public class UndefinedVariableException extends MyException{
	private String myExceptionType = "UndefinedVariable";
	
	public UndefinedVariableException(){
		super();
	}
	
	@Override
	public String getMessage(){
		return myErrorMessages.getString(myExceptionType);
	}
}
