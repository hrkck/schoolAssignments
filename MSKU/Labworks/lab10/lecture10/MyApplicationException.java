package lecture10;

public class MyApplicationException extends RuntimeException {

	public MyApplicationException(String message, Throwable t){
		super (message,t);
	}
}
