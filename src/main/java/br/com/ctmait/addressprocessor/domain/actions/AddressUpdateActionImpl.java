package br.com.ctmait.addressprocessor.domain.actions;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressUpdateAction;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.domain.models.State;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Action;
import br.com.ctmait.addressprocessor.tech.infrastructure.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;


@Action
public class AddressUpdateActionImpl implements AddressUpdateAction {

    private static final Logger log = LoggerFactory.getLogger(AddressUpdateActionImpl.class);

    private final AddressRepository addressRepository;

    public AddressUpdateActionImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void execute(Address address) throws AddressValidationException, AddressException{

        try {

            Objects.requireNonNull(address, "address cannot null");

            log.info("AUAI-E-00 updating address {}", address);

            address.visit(this::setState);
            address.visit(addressRepository::update);

            log.info("AUAI-E-01 updated address {}", address);
        }catch (NullPointerException nullPointerException){
            log.error("AUAI-S-02 error {} updating address {}", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (AddressValidationException addressValidationException){
            log.error("AUAI-S-03 error {} updating address {}", addressValidationException, address);
            throw addressValidationException;
        }catch (AddressException addressException){
            log.error("AUAI-S-04 Error {} updating address {}", addressException, address);
            throw addressException;
        }catch (Exception exception){
            log.error("AUAI-S-05 error {} updating Address {}", exception, address);
            throw new AddressException(exception);
        }
    }

    private void setState(Address address){
        log.info("AUAI-SS-00 set state UPDATED in address {}", address);
        address.setState(State.UPDATED);
        log.info("AUAI-SS-01 seted state UPDATED in address {}", address);
    }
}
