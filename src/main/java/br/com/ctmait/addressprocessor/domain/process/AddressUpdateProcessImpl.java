package br.com.ctmait.addressprocessor.domain.process;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressPublisherAction;
import br.com.ctmait.addressprocessor.abstraction.actions.AddressUpdateAction;
import br.com.ctmait.addressprocessor.abstraction.process.AddressUpdateProcess;
import br.com.ctmait.addressprocessor.abstraction.validations.AddressUpdateValidation;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressUpdateValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Process
public class AddressUpdateProcessImpl implements AddressUpdateProcess {

    private static final Logger log = LoggerFactory.getLogger(AddressUpdateProcessImpl.class);

    private final AddressUpdateValidation addressUpdateValidation;

    private final AddressUpdateAction addressUpdateAction;

    private final AddressPublisherAction addressPublisherAction;

    public AddressUpdateProcessImpl(AddressUpdateValidation addressUpdateValidation, AddressUpdateAction addressUpdateAction, AddressPublisherAction addressPublisherAction) {
        this.addressUpdateValidation = addressUpdateValidation;
        this.addressUpdateAction = addressUpdateAction;
        this.addressPublisherAction = addressPublisherAction;
    }

    @Override
    public void execute(Address address) throws AddressValidationException, AddressUpdateValidationException, AddressException{

        log.info("AUPI-E-00 start update process address {} ", address);
        try {
            Objects.requireNonNull(address, "address cannot null");
            address.visit(addressUpdateValidation::execute);
            address.visit(addressUpdateAction::execute);
            address.visit(addressPublisherAction::execute);
            log.info("AUPI-E-01 end update process address {} with sucess", address);
        }catch (NullPointerException nullPointerException){
            log.error("AUPI-E-02 error {} update process address {} ", nullPointerException, address);
            throw new AddressUpdateValidationException(nullPointerException);
        }catch (AddressUpdateValidationException addressUpdateValidationException){
            log.error("AUPI-E-03 error {} update process address {} ", addressUpdateValidationException, address);
            throw addressUpdateValidationException;
        }catch (AddressValidationException addressValidationException){
            log.error("AUPI-E-04 error {} update process address {} ", addressValidationException, address);
            throw addressValidationException;
        }catch (Exception exception){
            log.error("AUPI-E-05 error {} update process address {} ", exception, address);
            throw new AddressException(exception);
        }
    }
}
