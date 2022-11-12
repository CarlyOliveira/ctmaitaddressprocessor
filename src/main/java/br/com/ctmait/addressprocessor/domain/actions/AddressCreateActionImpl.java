package br.com.ctmait.addressprocessor.domain.actions;

import br.com.ctmait.addressprocessor.abstraction.actions.AddressCreateAction;
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
public class AddressCreateActionImpl implements AddressCreateAction {

    private static final Logger log = LoggerFactory.getLogger(AddressCreateActionImpl.class);

    private final AddressRepository addressRepository;

    public AddressCreateActionImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void execute(Address address) throws AddressValidationException, AddressException{

        try {

            Objects.requireNonNull(address, "address cannot null");

            log.info("ACAI-E-00 creating address {}", address);

            address.visit(this::setState);
            address.visit(addressRepository::insert);

            log.info("ACAI-E-01 created address {}", address);
        }catch (NullPointerException nullPointerException){
            log.error("ACAI-S-02 error {} creating address {}", nullPointerException, address);
            throw new AddressValidationException(nullPointerException);
        }catch (AddressValidationException addressValidationException){
            log.error("ACAI-S-03 error {} creating address {}", addressValidationException, address);
            throw addressValidationException;
        }catch (AddressException addressException){
            log.error("ACAI-S-04 Error {} creating address {}", addressException, address);
            throw addressException;
        }catch (Exception exception){
            log.error("ACAI-S-05 error {} creating Address {}", exception, address);
            throw new AddressException(exception);
        }
    }

    private void setState(Address address){
        log.info("ACAI-SS-00 set state READY in address {}", address);
        address.setState(State.INCLUDED);
        log.info("ACAI-SS-01 seted state READY in address {}", address);
    }
}
