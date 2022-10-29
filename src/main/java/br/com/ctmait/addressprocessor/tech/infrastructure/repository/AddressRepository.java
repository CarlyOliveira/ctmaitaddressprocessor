package br.com.ctmait.addressprocessor.tech.infrastructure.repository;

import br.com.ctmait.addressprocessor.domain.models.Address;

public interface AddressRepository {
    public void save(Address address);
}
