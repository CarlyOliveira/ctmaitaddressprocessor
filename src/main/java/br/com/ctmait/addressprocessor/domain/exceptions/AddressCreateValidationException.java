package br.com.ctmait.addressprocessor.domain.exceptions;

public class AddressCreateValidationException extends RuntimeException {

    public AddressCreateValidationException(String error){
        super(error);
    }
    public AddressCreateValidationException(RuntimeException exception){
        super(exception.getMessage(), exception);
    }

    public AddressCreateValidationException(String error, RuntimeException exception){
        super(error, exception);
    }
}
