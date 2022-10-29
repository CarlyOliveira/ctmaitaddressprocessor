package br.com.ctmait.addressprocessor.domain.exceptions;

public class AddressValidationException extends RuntimeException {

    public AddressValidationException(String error){
        super(error);
    }
    public AddressValidationException(String error, RuntimeException exception){
        super(error, exception);
    }
}
