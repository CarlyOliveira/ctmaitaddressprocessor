package br.com.ctmait.addressprocessor.domain.exceptions;

public class AddressUpdateValidationException extends RuntimeException {

    public AddressUpdateValidationException(String error){
        super(error);
    }
    public AddressUpdateValidationException(RuntimeException exception){
        super(exception.getMessage(), exception);
    }

    public AddressUpdateValidationException(String error, RuntimeException exception){
        super(error, exception);
    }
}
