package br.com.ctmait.addressprocessor.domain.validations;

import br.com.ctmait.addressprocessor.abstraction.validations.AddressValidation;
import br.com.ctmait.addressprocessor.domain.models.Address;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class AddressValidationImpl implements AddressValidation {

    private static final Logger log = LoggerFactory.getLogger(AddressValidationImpl.class);

    @Override
    public void execute(Address address) {
        log.info("ASVI-E-00", "Validando Address", address);
    }
}
