package br.com.ctmait.addressprocessor.domain.exceptions;

public class AddressException extends RuntimeException {

    public AddressException(String error){
        super(error);
    }
    public AddressException(String error, RuntimeException exception){
        super(error, exception);
    }
}
