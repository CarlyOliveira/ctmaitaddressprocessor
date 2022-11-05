package br.com.ctmait.addressprocessor.domain.exceptions;

public class AddressException extends RuntimeException {

    public AddressException(String error){
        super(error);
    }
    public AddressException(String error, Exception exception){
        super(error, exception);
    }
    public AddressException(Exception exception){
        super(exception.getMessage(), exception);
    }

}
