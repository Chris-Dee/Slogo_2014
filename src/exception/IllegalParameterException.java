package exception;

public class IllegalParameterException extends MyException{
	private String myExceptionType = "IllegalParameter";
	
	public IllegalParameterException(){
		super();
	}
	
	@Override
	public String getMessage(){
		return myErrorMessages.getString(myExceptionType);
	}
}
