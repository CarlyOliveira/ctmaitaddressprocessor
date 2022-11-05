package br.com.ctmait.addressprocessor.domain.validations;

import br.com.ctmait.addressprocessor.abstraction.validations.AddressCreateValidation;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressCreateValidationException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.models.Address;
import br.com.ctmait.addressprocessor.tech.infrastructure.annotations.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.UUID;

@Validation
public class AddressCreateValidationImpl implements AddressCreateValidation {

    private static final Logger log = LoggerFactory.getLogger(AddressCreateValidationImpl.class);

    @Override
    public void execute(Address address) throws AddressCreateValidationException, AddressException{

        log.info("ACVI-E-00 Validate Address {} in create process", address);
        HashMap<String, String> errors = new HashMap<>();
        try{
            validateAddress(address, errors);
            validateId(address, errors);
            validateCep(address, errors);
            validateLogradouro(address, errors);
            validateComplemento(address, errors);
            validateBairro(address, errors);
            validateCidade(address, errors);
            validateUf(address, errors);
            validateNumero(address, errors);
            validateProvider(address, errors);
            hasErrorsThrowRabbitValidationException(errors);
        }catch (AddressCreateValidationException addressCreateValidationException){
            log.error("ACVI-E-01 Validate Address {} with erros in create process", address, addressCreateValidationException);
            throw addressCreateValidationException;
        }catch (Exception exception){
            log.error("ACVI-E-01 Validate Address {} with erros in create process", address, exception);
            throw new AddressException(exception);
        }
        log.info("ACVI-E-03 Address {} validated in create process", address);
    }

    private void validateAddress(Address address, HashMap<String, String> errors){
        try {
            notNullOrEmpty(address, "rabbit cannot null");
        }catch (Exception e){
            errors.put("Address", e.getMessage());
        }
    }

    private void validateId(Address address, HashMap<String, String> errors){
        try {
            var id = UUID.fromString(address.getId());
            notNullOrEmpty(id, "id cannot null");
        }catch (Exception e){
            errors.put("id", e.getMessage());
        }
    }

    private void validateCep(Address address, HashMap<String, String> errors){
        try {
            var cep = address.getCep();
            notNullOrEmpty(cep, "cep cannot null");
            validateFormatCep(cep);
        }catch (Exception e){
            errors.put("cep", e.getMessage());
        }
    }

    private void validateFormatCep(String cep){
        if (cep == null || !cep.matches("[0-9]{8}")){
            throw new IllegalArgumentException("cep format is invalid");
        }
    }


    private void validateLogradouro(Address address, HashMap<String, String> errors){
        try {
            var logradouro = address.getLogradouro();
            notNullOrEmpty(logradouro, "logradouro cannot null");
        }catch (Exception e){
            errors.put("logradouro", e.getMessage());
        }
    }

    private void validateComplemento(Address address, HashMap<String, String> errors){
        try {
            var complemento = address.getComplemento();
            notNullOrEmpty(complemento, "complemento cannot null");
        }catch (Exception e){
            errors.put("complemento", e.getMessage());
        }
    }

    private void validateBairro(Address address, HashMap<String, String> errors){
        try {
            var bairro = address.getBairro();
            notNullOrEmpty(bairro, "bairro cannot null");
        }catch (Exception e){
            errors.put("bairro", e.getMessage());
        }
    }

    private void validateCidade(Address address, HashMap<String, String> errors){
        try {
            var cidade = address.getCidade();
            notNullOrEmpty(cidade, "cidade cannot null");
        }catch (Exception e){
            errors.put("cidade", e.getMessage());
        }
    }

    private void validateUf(Address address, HashMap<String, String> errors){
        try {
            var uf = address.getUf();
            notNullOrEmpty(uf, "uf cannot null");
        }catch (Exception e){
            errors.put("uf", e.getMessage());
        }
    }

    private void validateNumero(Address address, HashMap<String, String> errors){
        try {
            var numero = address.getNumero();
            notNullOrEmpty(numero, "numero cannot null");
        }catch (Exception e){
            errors.put("numero", e.getMessage());
        }
    }

    private void validateProvider(Address address, HashMap<String, String> errors){
        try {
            var provider = address.getProvider();
            notNullOrEmpty(provider, "provider cannot null");
        }catch (Exception e){
            errors.put("provider", e.getMessage());
        }
    }


    private void hasErrorsThrowRabbitValidationException(HashMap<String, String> errors){
        if (errors.size() > 0){
            throw new AddressCreateValidationException(errors.toString());
        }
    }

    private void notNullOrEmpty(Object object, String message){
        if (ObjectUtils.isEmpty(object)) {
            throw new IllegalArgumentException(message);
        }
    }
    private void notNullOrEmpty(String text, String message){
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }
}
