package br.com.ctmait.addressprocessor.deprecated.repository;

import br.com.ctmait.addressprocessor.deprecated.domain.models.Address;

public interface AddressRepository {
    public void save(Address address);
}
