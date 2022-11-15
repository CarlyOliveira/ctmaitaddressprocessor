package br.com.ctmait.addressprocessor.tech.rest.exceptions.handlers;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressCreateValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressDeleteValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressUpdateValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.tech.rest.exceptions.payload.ExceptionPayload;
import br.com.ctmait.addressprocessor.tech.rest.exceptions.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
@RequiredArgsConstructor
public class AddressResourcesHandler {

    private final ExceptionService exceptionService;

    @ExceptionHandler(HttpClientErrorException.class)
    @ResponseBody
    public ResponseEntity<ExceptionPayload> handleException(HttpClientErrorException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                ex.getStatusCode());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public ResponseEntity<ExceptionPayload> handleException(HttpServerErrorException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                ex.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionPayload> handleException(Exception ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionPayload> handleException(MethodArgumentNotValidException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AddressCreateValidationException.class)
    public ResponseEntity<ExceptionPayload> handleException(AddressCreateValidationException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AddressUpdateValidationException.class)
    public ResponseEntity<ExceptionPayload> handleException(AddressUpdateValidationException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AddressDeleteValidationException.class)
    public ResponseEntity<ExceptionPayload> handleException(AddressDeleteValidationException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(AddressValidationException.class)
    public ResponseEntity<ExceptionPayload> handleException(AddressValidationException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionPayload> handleException(IllegalArgumentException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionPayload> handleException(NullPointerException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ExceptionPayload> handleException(MissingRequestHeaderException ex) {
        return new ResponseEntity<ExceptionPayload>(
                exceptionService.generatePayload(ex),
                HttpStatus.BAD_REQUEST);
    }

}
