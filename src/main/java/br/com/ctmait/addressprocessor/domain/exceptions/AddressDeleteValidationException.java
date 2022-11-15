package br.com.ctmait.addressprocessor.domain.exceptions;

public class AddressDeleteValidationException extends RuntimeException {

    public AddressDeleteValidationException(String error){
        super(error);
    }
    public AddressDeleteValidationException(RuntimeException exception){
        super(exception.getMessage(), exception);
    }

    public AddressDeleteValidationException(String error, RuntimeException exception){
        super(error, exception);
    }
}
