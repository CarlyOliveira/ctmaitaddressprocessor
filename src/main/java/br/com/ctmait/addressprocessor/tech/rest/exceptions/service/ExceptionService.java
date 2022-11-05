package br.com.ctmait.addressprocessor.tech.rest.exceptions.service;

import br.com.ctmait.addressprocessor.tech.rest.exceptions.payload.ExceptionPayload;

public interface ExceptionService {

    ExceptionPayload generatePayload(Exception ex);
}
