package br.com.ctmait.addressprocessor.tech.rest.exceptions.service.impl;

import br.com.ctmait.addressprocessor.tech.rest.exceptions.payload.ExceptionPayload;
import br.com.ctmait.addressprocessor.tech.rest.exceptions.service.ExceptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionServiceImpl.class);

	@Override
	public ExceptionPayload generatePayload(Exception ex) {
		logger.error("ESI-MRHE-00 Exception {}, erro {}", ex.getClass(), ex.getMessage());
		return ExceptionPayload.builder()
				.error(ex.getClass().getSimpleName())
				.error_description(ex.getMessage())
				.build();
	}
}
