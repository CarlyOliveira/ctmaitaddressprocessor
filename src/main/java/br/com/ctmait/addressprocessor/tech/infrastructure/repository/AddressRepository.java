package br.com.ctmait.addressprocessor.tech.infrastructure.repository;

import br.com.ctmait.addressprocessor.domain.exceptions.AddressException;
import br.com.ctmait.addressprocessor.domain.exceptions.AddressValidationException;
import br.com.ctmait.addressprocessor.domain.models.Address;

public interface AddressRepository {
    void insert(Address address) throws AddressValidationException, AddressException;
    void update(Address address) throws AddressValidationException, AddressException;
    void delete(Address address) throws AddressValidationException, AddressException;
    Address getById(String addressId) throws AddressValidationException, AddressException;
}
