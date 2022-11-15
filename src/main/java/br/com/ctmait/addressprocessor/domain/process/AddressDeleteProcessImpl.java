package br.com.ctmait.addressprocessor.domain.process;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressDeleteAction;
import br.com.ctmait.addressprocessor.abstraction.actions.AddressPublisherAction;
import br.com.ctmait.addressprocessor.abstraction.process.AddressDeleteProcess;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressDeleteValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Process
public class AddressDeleteProcessImpl implements AddressDeleteProcess {

    private static final Logger log = LoggerFactory.getLogger(AddressDeleteProcessImpl.class);

    private final AddressDeleteAction addressDeleteAction;

    private final AddressPublisherAction addressPublisherAction;

    public AddressDeleteProcessImpl(AddressDeleteAction addressDeleteAction, AddressPublisherAction addressPublisherAction) {
        this.addressDeleteAction = addressDeleteAction;
        this.addressPublisherAction = addressPublisherAction;
    }

    @Override
    public void execute(Address address) throws AddressValidationException, AddressDeleteValidationException, AddressException{

        log.info("ADPI-E-00 start delete process address {} ", address);
        try {
            Objects.requireNonNull(address, "address cannot null");
            address.visit(addressDeleteAction::execute);
            address.visit(addressPublisherAction::execute);
            log.info("ADPI-E-01 end delete process address {} with sucess", address);
        }catch (NullPointerException nullPointerException){
            log.error("ADPI-E-02 error {} delete process address {} ", nullPointerException, address);
            throw new AddressDeleteValidationException(nullPointerException);
        }catch (AddressDeleteValidationException addressDeleteValidationException){
            log.error("ADPI-E-03 error {} delete process address {} ", addressDeleteValidationException, address);
            throw addressDeleteValidationException;
        }catch (AddressValidationException addressValidationException){
            log.error("ADPI-E-04 error {} delete process address {} ", addressValidationException, address);
            throw addressValidationException;
        }catch (Exception exception){
            log.error("ADPI-E-05 error {} delete process address {} ", exception, address);
            throw new AddressException(exception);
        }
    }
}
