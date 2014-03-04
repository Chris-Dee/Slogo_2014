package exception;

public class IllegalCommandException extends MyException{
	private String myExceptionType = "IllegalCommand";
	
	public IllegalCommandException(){
		super();
	}
	
	@Override
	public String getMessage(){
		return myErrorMessages.getString(myExceptionType);
	}
}