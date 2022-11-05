package br.com.ctmait.addressprocessor.domain.process;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressPublisherAction;
import br.com.ctmait.addressprocessor.abstraction.actions.AddressCreateAction;
import br.com.ctmait.addressprocessor.abstraction.process.AddressCreateProcess;
import br.com.ctmait.addressprocessor.abstraction.validations.AddressCreateValidation;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressCreateValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Process;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Process
public class AddressCreateProcessImpl implements AddressCreateProcess {

    private static final Logger log = LoggerFactory.getLogger(AddressCreateProcessImpl.class);

    private final AddressCreateValidation addressCreateValidation;

    private final AddressCreateAction addressCreateAction;

    private final AddressPublisherAction addressPublisherAction;

    public AddressCreateProcessImpl(AddressCreateValidation addressCreateValidation, AddressCreateAction addressCreateAction, AddressPublisherAction addressPublisherAction) {
        this.addressCreateValidation = addressCreateValidation;
        this.addressCreateAction = addressCreateAction;
        this.addressPublisherAction = addressPublisherAction;
    }

    @Override
    public void execute(Address address) {

        log.info("ACPI-E-00 Iniciando AddressCreateProcessImpl com Address {}", address);

        try {

            address.visit(addressCreateValidation::execute);

            address.visit(addressCreateAction::execute);

            address.visit(addressPublisherAction::execute);

            log.info("ACPI-E-02 finalizando AddressCreateProcessImpl com Address", address);
        }catch (NullPointerException nullPointerException){
            log.error("ACPI-E-03 Erro {} AddressCreateProcessImpl com Address {}", nullPointerException, address);
            throw new AddressCreateValidationException(nullPointerException);
        }catch (AddressCreateValidationException addressCreateValidationException){
            log.error("ACPI-E-03 Erro {} AddressCreateProcessImpl com Address {}", addressCreateValidationException, address);
            throw addressCreateValidationException;
        }catch (AddressValidationException addressValidationException){
            log.error("ACPI-E-03 Erro {} AddressCreateProcessImpl com Address {}", addressValidationException, address);
            throw addressValidationException;
        }catch (Exception exception){
            log.error("ACPI-E-03 rro {} AddressCreateProcessImpl com Address {}", exception, address);
            throw new AddressException(exception);
        }
    }
}
