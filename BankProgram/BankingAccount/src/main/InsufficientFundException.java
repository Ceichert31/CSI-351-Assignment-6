package main;

/// Used to signal invalid financial operations
public class InsufficientFundException extends Exception{
    public InsufficientFundException(){
        super();
    }

    public InsufficientFundException(String message){
        super(message);
    }

    public InsufficientFundException(String message, Throwable cause){
        super(message, cause);
    }
}
