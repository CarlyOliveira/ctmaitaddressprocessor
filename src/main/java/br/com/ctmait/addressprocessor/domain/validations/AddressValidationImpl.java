package br.com.ctmait.addressprocessor.domain.validations;

import br.com.ctmait.addressprocessor.abstraction.validations.AddressCreateValidation;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Validation
public class AddressValidationImpl implements AddressCreateValidation {

    private static final Logger log = LoggerFactory.getLogger(AddressValidationImpl.class);

    @Override
    public void execute(Address address) {

        log.info("AVI-E-00 Validando Address {}", address);
        // TODO Implementar validação
        log.info("AVI-E-01 Address {} validado", address);
    }
}
